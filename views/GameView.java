package views;


import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.websocket.*;

import models.*;

@ClientEndpoint
public class GameView extends JPanel {
    private HorizontalDeckView communityCardsView;
    private PlayerInfoView PlayerInfoView;
    private JLabel currentBetLabel;
    private JLabel currentPlayerLabel;
    
    private Session session;
    public String roomID = "";
    public boolean started = false;
    public int playerNumber;

    public GameView(MainView mainView, Deck communityCardsDeck, Player[] players) {
        Color backGroundColor = new Color(34, 139, 34);
        this.setBackground(backGroundColor);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
    
        // 最上層新增一個panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(backGroundColor);
    
        JLabel currentRoomLabel = new JLabel("Room NO.1");
        currentRoomLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        currentRoomLabel.setForeground(Color.WHITE);
        currentRoomLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(currentRoomLabel, BorderLayout.CENTER);
    
        JButton quitButton = new Button("Quit", "logout");
        topPanel.add(quitButton, BorderLayout.EAST);
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.gridheight = 1;
        gbc.weighty = 0.1;
        add(topPanel, gbc);
    
        // 上方放置四位玩家
        JPanel topPlayersPanel = new JPanel(new GridLayout(1, 4));
        topPlayersPanel.setBackground(backGroundColor);
        for (int i = 0; i < 4; i++) {
            topPlayersPanel.add(new PlayerView(players[i]));
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.weighty = 0.1;
        add(topPlayersPanel, gbc);
    
        // 公共牌區域視圖
        JPanel communityPanel = new JPanel(new FlowLayout());
        communityPanel.setBackground(backGroundColor);
        communityCardsView = new HorizontalDeckView(communityCardsDeck);
        communityPanel.add(communityCardsView);
        
        JPanel communityInfo = new JPanel(new GridLayout(2,1));
        communityInfo.setBackground(backGroundColor);
        
        // 當前玩家
        currentPlayerLabel = new JLabel("Current Player: ");
        currentPlayerLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14)); // 設置字體
        currentPlayerLabel.setForeground(new Color(255, 215, 0)); // 設置文字顏色
        currentPlayerLabel.setBackground(backGroundColor);
        communityInfo.add(currentPlayerLabel);
        
        // 當前下注金額
        currentBetLabel = new JLabel("Current Bet: ");
        currentBetLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14)); // 設置字體
        currentBetLabel.setForeground(new Color(255, 165, 0)); // 設置文字顏色
        currentBetLabel.setBackground(backGroundColor);
        communityInfo.add(currentBetLabel);
    
        
    
        communityPanel.add(communityInfo);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 5;
        gbc.gridheight = 2;
        gbc.weighty = 0.2;
        add(communityPanel, gbc);
    
        // 下方放置四位玩家
        JPanel bottomPlayersPanel = new JPanel(new GridLayout(1, 4));
        bottomPlayersPanel.setBackground(backGroundColor);
        for (int i = 4; i < players.length; i++) {
            bottomPlayersPanel.add(new PlayerView(players[i]));
        }
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.weighty = 0.1;
        add(bottomPlayersPanel, gbc);
    
        // 放置控制區域視圖
        PlayerInfoView = new PlayerInfoView(players[0]);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.weighty = 0.1;
        add(PlayerInfoView, gbc);
    }

    


    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Connected to the server");
    }

    @OnMessage
    public void onMessage(String message) {
        String[] tokens = message.split(":");
        switch (tokens[0]) {
            case "num": {
                playerNumber = Integer.parseInt(tokens[1]);
                break;
            }

            case "action": {
                String action = tokens[1];
                System.out.println("Player: " + action);
            }
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Session closed: " + closeReason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    void send(String text) {
        try {
            session.getBasicRemote().sendText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRoom(String id) {
        roomID = id;
        String message = String.format("{\"id\": \"%s\", \"type\":\"join\"}", id);
        send(message);
    }
}


class PlayerView extends JPanel {
    private Player player;
    private JLabel playerNameLabel;
    private JLabel betLabel;
    private JLabel chipsLabel;
    private JLabel card1Label;
    private JLabel card2Label;

    public PlayerView(Player player) {
        this.setBackground(new Color(34, 139, 34));
        this.player = player;
        setLayout(new BorderLayout());

        playerNameLabel = new JLabel(player.getName());
        JPanel playerInfoPanel = new JPanel(new GridLayout(2, 1)); // 使用 GridLayout 排列玩家資訊
        playerInfoPanel.setBackground(new Color(34, 139, 34));
        playerInfoPanel.add(playerNameLabel);
        playerNameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14)); // 設置字體
        playerNameLabel.setForeground(new Color(255, 215, 0)); 
        // 顯示玩家下注金額和剩餘籌碼
        betLabel = new JLabel("Bet: " + player.getCurrentBet());
        betLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14)); // 設置字體
        betLabel.setForeground(new Color(255, 165, 0)); // 設置文字顏色為紅色
        chipsLabel = new JLabel("Chips: " + player.getChips());
        chipsLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14)); // 設置字體
        chipsLabel.setForeground(Color.LIGHT_GRAY); 

        JPanel betChipsPanel = new JPanel(new GridLayout(1, 2)); // 使用 GridLayout 排列下注金額和籌碼
        betChipsPanel.setBackground(new Color(34, 139, 34));
        betChipsPanel.add(betLabel);
        betChipsPanel.add(chipsLabel);
        playerInfoPanel.add(betChipsPanel);

        add(playerInfoPanel, BorderLayout.NORTH);

        // 顯示玩家的卡片
        JPanel cardsPanel = new JPanel(new FlowLayout());
        cardsPanel.setBackground(new Color(34, 139, 34));
        card1Label = new JLabel();
        card2Label = new JLabel();
        cardsPanel.add(card1Label);
        cardsPanel.add(card2Label);
        updateCards();
        add(cardsPanel, BorderLayout.CENTER);
    }

    public void updateCards() {
        List<Card> hand = player.getHand();
        card1Label.setIcon(new ImageIcon(toImage(hand.get(0))));
        card2Label.setIcon(new ImageIcon(toImage(hand.get(1))));
    }

    private Image toImage(Card card) {
        String path = "./images/card-face/" + card.toString() + ".png";
        ImageIcon originalIcon = new ImageIcon(path);
        Image image = originalIcon.getImage().getScaledInstance(75, 105, Image.SCALE_SMOOTH);
        return image;
    }
}

class PlayerInfoView extends JPanel {
    private Button foldButton;
    private Button callButton;
    private Button raiseButton;
    private JTextField raiseAmountField;

    public PlayerInfoView(Player player) {
        setBackground(new Color(144, 238, 144));
        setLayout(new GridBagLayout());
        // 添加 EtchedBorder
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        GridBagConstraints gbc = new GridBagConstraints();

        // 设置左边的 PlayerView，占 3 宽度和 3 高度
        PlayerView playerView = new PlayerView(player);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(playerView, gbc);

        // 设置右边的面板，占 2 宽度和 1 高度
        JPanel controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBackground(new Color(34, 139, 34));
        GridBagConstraints rightGbc = new GridBagConstraints();
        
        rightGbc.gridx = 0;
        rightGbc.gridy = 0;
        rightGbc.gridwidth = 2;
        rightGbc.insets = new Insets(10, 10, 10, 10);
        rightGbc.anchor = GridBagConstraints.WEST;
        // 添加 Raise Amount Label
        JLabel raiseAmountLabel = new JLabel("Raise Amount:");
        raiseAmountLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        raiseAmountLabel.setForeground(new Color(255, 215, 0));
        controlPanel.add(raiseAmountLabel, rightGbc);

        // 添加 Raise Amount TextArea
        rightGbc.gridx = 1;
        rightGbc.anchor = GridBagConstraints.CENTER;
        raiseAmountField = new JTextField(10);
        raiseAmountField.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        raiseAmountField.setForeground(new Color(255, 215, 0));
        raiseAmountField.setBackground(new Color(255, 255, 224));
        controlPanel.add(raiseAmountField, rightGbc);

        rightGbc.gridx = 0;
        rightGbc.gridy = 1;
        rightGbc.gridwidth = 2;
        rightGbc.weightx = 1.0;
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonsPanel.setBackground(new Color(34, 139, 34));

        foldButton = new Button("Fold", "normal");
        buttonsPanel.add(foldButton);

        callButton = new Button("Call", "normal");
        buttonsPanel.add(callButton);

        raiseButton = new Button("Raise", "normal");
        buttonsPanel.add(raiseButton);

        controlPanel.add(buttonsPanel, rightGbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(controlPanel, gbc);
    }
}