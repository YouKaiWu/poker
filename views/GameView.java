package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.websocket.*;

import models.*;

@ClientEndpoint
public class GameView extends JPanel {
    private HorizontalDeckView playerHandCenter;
    private HorizontalDeckView playerHandLeft;
    private HorizontalDeckView playerHandRight;
    private HorizontalDeckView communityCards;
    private JLabel playerNameCenter;
    private JLabel playerNameLeft;
    private JLabel playerNameRight;
    private JLabel playerChipsCenter;
    private JLabel playerChipsLeft;
    private JLabel playerChipsRight;
    private JLabel potLabel;
    private JButton raiseButton;
    private JButton checkButton;
    private JButton foldButton;
    private JButton dealButton;
    private int pot;

    private Session session;
    public String roomID = "";
    public boolean started = false;
    public int playerNumber;

    public GameView(MainView mainView) {
        this.setLayout(new BorderLayout());

        // 設置桌面背景
        JLabel background = new JLabel(new ImageIcon("./images/Table.png"));
        this.add(background);
        background.setLayout(new BorderLayout());

        // 初始化撲克牌展示區域
        JPanel cardPanel = new JPanel(new GridBagLayout());
        cardPanel.setOpaque(false);
        background.add(cardPanel, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        playerHandCenter = new HorizontalDeckView(new Deck());
        gbc.gridx = 1;
        gbc.gridy = 2;
        cardPanel.add(playerHandCenter, gbc);

        playerHandLeft = new HorizontalDeckView(new Deck());
        gbc.gridx = 0;
        gbc.gridy = 1;
        cardPanel.add(playerHandLeft, gbc);

        playerHandRight = new HorizontalDeckView(new Deck());
        gbc.gridx = 2;
        gbc.gridy = 1;
        cardPanel.add(playerHandRight, gbc);

        communityCards = new HorizontalDeckView(new Deck());
        gbc.gridx = 1;
        gbc.gridy = 1;
        cardPanel.add(communityCards, gbc);

        // 添加玩家信息
        playerNameCenter = new JLabel("Player Center");
        gbc.gridx = 1;
        gbc.gridy = 3;
        cardPanel.add(playerNameCenter, gbc);

        playerNameLeft = new JLabel("Player Left");
        gbc.gridx = 0;
        gbc.gridy = 2;
        cardPanel.add(playerNameLeft, gbc);

        playerNameRight = new JLabel("Player Right");
        gbc.gridx = 2;
        gbc.gridy = 2;
        cardPanel.add(playerNameRight, gbc);

        playerChipsCenter = new JLabel("Chips: 1000");
        gbc.gridx = 1;
        gbc.gridy = 4;
        cardPanel.add(playerChipsCenter, gbc);

        playerChipsLeft = new JLabel("Chips: 1000");
        gbc.gridx = 0;
        gbc.gridy = 3;
        cardPanel.add(playerChipsLeft, gbc);

        playerChipsRight = new JLabel("Chips: 1000");
        gbc.gridx = 2;
        gbc.gridy = 3;
        cardPanel.add(playerChipsRight, gbc);

        pot = 0;
        potLabel = new JLabel("Pot: " + pot);
        gbc.gridx = 1;
        gbc.gridy = 0;
        cardPanel.add(potLabel, gbc);

        // 按鈕設置
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        background.add(buttonPanel, BorderLayout.EAST);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        raiseButton = new Button("Raise", "normal");
        checkButton = new Button("Check", "normal");
        foldButton = new Button("Fold", "normal");

        buttonPanel.add(raiseButton);
        buttonPanel.add(checkButton);
        buttonPanel.add(foldButton);

        // 發牌按鈕
        dealButton = new Button("Deal", "normal");
        buttonPanel.add(dealButton);

        // 添加按鈕事件
        dealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dealCards();
            }
        });

        raiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRaise();
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCheck();
            }
        });

        foldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFold();
            }
        });

        String uri = "ws://localhost:8080";
        try {
            ContainerProvider.getWebSocketContainer().connectToServer(this, new URI(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dealCards() {
        // 模擬發牌
        Deck playerDeckCenter = createPlayerDeck();
        Deck playerDeckLeft = createPlayerDeck();
        Deck playerDeckRight = createPlayerDeck();
        Deck communityDeck = createSampleCommunityDeck();

        updatePlayerHandCenter(playerDeckCenter);
        updatePlayerHandLeft(playerDeckLeft);
        updatePlayerHandRight(playerDeckRight);
        updateCommunityCards(communityDeck);
    }

    private Deck createPlayerDeck() {
        // 創建樣例玩家撲克牌（兩張牌）
        Deck deck = new Deck(new Card[] {
                new Card(Card.Suit.SPADE, 2),
                new Card(Card.Suit.HEART, 3)
        });
        return deck;
    }

    private Deck createSampleCommunityDeck() {
        // 創建樣例公共撲克牌
        Deck deck = new Deck(new Card[] {
                new Card(Card.Suit.SPADE, 6),
                new Card(Card.Suit.HEART, 7),
                new Card(Card.Suit.DIAMOND, 8),
                new Card(Card.Suit.CLUB, 9),
                new Card(Card.Suit.SPADE, 10)
        });
        return deck;
    }

    public void updatePlayerHandCenter(Deck deck) {
        playerHandCenter.update(deck);
        playerHandCenter.revalidate();
        playerHandCenter.repaint();
    }

    public void updatePlayerHandLeft(Deck deck) {
        playerHandLeft.update(deck);
        playerHandLeft.revalidate();
        playerHandLeft.repaint();
    }

    public void updatePlayerHandRight(Deck deck) {
        playerHandRight.update(deck);
        playerHandRight.revalidate();
        playerHandRight.repaint();
    }

    public void updateCommunityCards(Deck deck) {
        communityCards.update(deck);
        communityCards.revalidate();
        communityCards.repaint();
    }

    private void handleRaise() {
        String format = "{\"id\": \"%s\", \"type\":\"action\", \"action\": \"%s\"}";
        String message = String.format(format, roomID, "raise");
        send(message);

        // 處理加注邏輯
        JOptionPane.showMessageDialog(this, "Raise button clicked!");
    }

    private void handleCheck() {
        String format = "{\"id\": \"%s\", \"type\":\"action\", \"action\": \"%s\"}";
        String message = String.format(format, roomID, "check");
        send(message);

        // 處理過牌邏輯
        JOptionPane.showMessageDialog(this, "Check button clicked!");
    }

    private void handleFold() {
        String format = "{\"id\": \"%s\", \"type\":\"action\", \"action\": \"%s\"}";
        String message = String.format(format, roomID, "fold");
        send(message);

        // 處理棄牌邏輯
        JOptionPane.showMessageDialog(this, "Fold button clicked!");
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
