package views;

import javax.swing.*;
import java.awt.*;

import models.*;

public class LobbyView extends JPanel {
    public LobbyView(MainView mainView) {
        this.setBackground(new Color(34, 139, 34));
        this.setLayout(new BorderLayout()); // 使用BorderLayout作为主布局

        // 创建顶部面板用于放置登出按钮
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // 使顶部面板透明
        this.add(topPanel, BorderLayout.NORTH);

        // 登出按钮
        JButton logoutButton = new Button("logout", "logout");
        topPanel.add(logoutButton, BorderLayout.EAST); // 将登出按钮放在右上角

        logoutButton.addActionListener(e -> {
            mainView.switchPanel("login");
        });
        // 添加hover效果

        // 中心面板用于放置其余组件
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // 使中心面板透明
        this.add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();

        // 顯示卡片的示例
        Card card = new Card(Card.Suit.DIAMOND, 1);
        CardView cv = new CardView(card);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0); // 设置上下间距
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(cv, gbc);

        // 主選單按鈕
        JButton joinRoomButton = new Button("Join Room", "normal");
        joinRoomButton.addActionListener(e -> {
            RoomSelectionDialog dialog = new RoomSelectionDialog(mainView.frame, mainView.client);
            dialog.setVisible(true);
            String selectedRoom = dialog.getSelectedRoom();
            if (selectedRoom != null) {
                mainView.client.roomID = selectedRoom;
                mainView.client.gameStart = false;
                mainView.switchPanel("game");
            }
        });

        JButton createRoomButton = new Button("Create Room", "normal");
        createRoomButton.addActionListener(e -> {
            String roomID = mainView.client.createRoom();
            mainView.client.roomID = roomID;
            mainView.client.gameStart = false;
            mainView.switchPanel("game");
        });

        JButton gameInstructionsButton = new Button("Game Instructions", "normal");

        // 設置按鈕佈局
        gbc.insets = new Insets(10, 0, 10, 0); // 设置按钮间距
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;

        gbc.gridy = 1;
        centerPanel.add(joinRoomButton, gbc);

        gbc.gridy = 2;
        centerPanel.add(createRoomButton, gbc);

        gbc.gridy = 3;
        centerPanel.add(gameInstructionsButton, gbc);
    }

}
