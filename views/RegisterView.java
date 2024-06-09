package views;

import javax.swing.*;
import java.awt.*;

import models.Button;

public class RegisterView extends JPanel {

    public RegisterView(MainView mainView, JFrame frame) {
        // 設置布局
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 設置背景顏色和邊框
        setBackground(new Color(34, 139, 34));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // 標題
        JLabel titleLabel = new JLabel("Poker Register");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 30, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // 使用者名稱標籤
        JLabel nameLabel = new JLabel("Username:");
        nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        add(nameLabel, gbc);

        // 使用者名稱輸入框
        JTextField nameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(nameField, gbc);

        // 帳號標籤
        JLabel usernameLabel = new JLabel("Account:");
        usernameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(usernameLabel, gbc);

        // 帳號輸入框
        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(usernameField, gbc);

        // 密碼標籤
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(passwordLabel, gbc);

        // 密碼輸入框
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordField, gbc);

        // 確認密碼標籤
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        confirmPasswordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(confirmPasswordLabel, gbc);

        // 確認密碼輸入框
        JPasswordField confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(confirmPasswordField, gbc);

        JLabel buttonContainer = new JLabel();
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0)); // 使用 FlowLayout 让按钮在中心对齐
        buttonContainer.setPreferredSize(new Dimension(300, 50));

        // 確定註冊按鈕
        JButton confirmButton = new Button("Confirm", "normal");
        confirmButton.addActionListener(e -> {
            String name = nameField.getText();
            String account = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirm = new String(confirmPasswordField.getPassword());

            if (!password.equals(confirm)) {
                String msg = "Please confirm the password.";
                JOptionPane.showMessageDialog(frame, msg, "Confirm the password", JOptionPane.ERROR_MESSAGE);
            } else {
                if (mainView.client.register(account, name, password)) {
                    mainView.switchPanel("login");
                } else {
                    String msg = "Failed to register.";
                    JOptionPane.showMessageDialog(frame, msg, "Register Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonContainer.add(confirmButton);

        // 取消按鈕
        JButton cancelButton = new Button("Cancel", "normal");
        cancelButton.addActionListener(e -> {
            mainView.switchPanel("login");
        });

        buttonContainer.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        add(buttonContainer, gbc);

    }
}