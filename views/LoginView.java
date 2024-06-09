package views;

import javax.swing.*;
import java.awt.*;

import models.Button;
import utility.*;

public class LoginView extends JPanel {
    MainView context;
    JFrame frame;

    public LoginView(MainView mainView, JFrame frame) {
        // 設置布局
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 設置背景顏色和邊框
        setBackground(new Color(34, 139, 34));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // 標題
        JLabel titleLabel = new JLabel("Poker Login");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 30, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // 帳號標籤
        JLabel usernameLabel = new JLabel("Account:");
        usernameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        add(usernameLabel, gbc);

        // 帳號輸入框
        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        // 密碼標籤
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);

        // 密碼輸入框
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        JLabel buttonContainer = new JLabel();
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0)); // 使用 FlowLayout 让按钮在中心对齐
        buttonContainer.setPreferredSize(new Dimension(300, 50));

        // 登入按鈕
        JButton loginButton = new Button("Login", "normal");
        loginButton.addActionListener(e -> {
            String acc = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            if (login(acc, pass)) {
                mainView.switchPanel("lobby");
            } else {
                String msg = "Login failed. Please check your username and password.";
                JOptionPane.showMessageDialog(frame, msg, "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonContainer.add(loginButton);

        // 註冊按鈕
        JButton registerButton = new Button("Register", "normal");
        registerButton.addActionListener(e -> {
            mainView.switchPanel("register");
        });
        buttonContainer.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 30, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonContainer, gbc);

        context = mainView;
    }

    boolean login(String account, String password) {
        return context.client.login(account, password);
    }
}