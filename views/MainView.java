package views;

import java.awt.CardLayout;

import javax.swing.*;

import utility.*;

public class MainView extends JPanel {
    public Client client;

    public MainView(JFrame frame, Client client) {
        this.client = client;
        this.setLayout(new CardLayout());

        JPanel login = new LoginView(this, frame);
        this.add(login, "login");

        JPanel lobby = new LobbyView(this);
        this.add(lobby, "lobby");

        JPanel register = new RegisterView(this, frame);
        this.add(register, "register");
    }

    public void switchPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) this.getLayout();
        cardLayout.show(this, panelName);
    }
}
