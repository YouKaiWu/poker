package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// 設置 button 外觀效果
public class Button extends JButton{ 
    public Button(String name, String type){
        this.setText(name);
        setButtonAppearance(type);
    }
    // 設置按鈕的外觀
    private void setButtonAppearance(String type){
        switch(type){
            case "normal":
            setNormal();
            break;
            case "logout":
            setLogout();
            break;
        }
        
    }
    // 一般按鈕外觀
    private void setNormal(){
        Color buttonForegroundColor = new Color(0, 102, 204);
        Color buttonBackgroundColor = new Color(255, 215, 0);
        Color hoverBackgroundColor = new Color(255, 165, 0);
        setAppearance(buttonForegroundColor, buttonBackgroundColor, hoverBackgroundColor);
    }

    // 登出按鈕外觀
    private void setLogout(){
        Color buttonForegroundColor = Color.DARK_GRAY;
        Color buttonBackgroundColor = new Color(255, 0, 0);
        Color hoverBackgroundColor = new Color(255, 140, 0);
        setAppearance(buttonForegroundColor, buttonBackgroundColor, hoverBackgroundColor);
    }

    private void setAppearance(Color buttonForegroundColor, Color buttonBackgroundColor, Color hoverBackgroundColor){
        Font buttonFont = new Font("Comic Sans MS", Font.BOLD, 16);
        this.setFont(buttonFont);
        this.setForeground(buttonForegroundColor);
        this.setBackground(buttonBackgroundColor);
        addHoverEffect(this, buttonBackgroundColor, hoverBackgroundColor);
    }

    // 添加按钮hover效果的方法
    public void addHoverEffect(JButton button, Color originalColor, Color hoverColor) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }
}
