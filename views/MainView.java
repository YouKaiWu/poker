package views;

import java.awt.CardLayout;

import javax.swing.*;


public class MainView extends JPanel{

    public MainView(){
        this.setLayout(new CardLayout());

        JPanel login = new LoginView(this);
        this.add(login, "login");

        JPanel lobby = new LobbyView(this);
        this.add(lobby, "lobby");

        JPanel register = new RegisterView(this);
        this.add(register, "register");
    }
    
    public void switchPanel(String panelName){
        CardLayout cardLayout = (CardLayout) this.getLayout();
        cardLayout.show(this, panelName);
    }
}
