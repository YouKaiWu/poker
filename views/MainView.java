package views;

import java.awt.CardLayout;
import javax.swing.*;

import utility.*;

public class MainView extends JPanel {
    public Client client;
    public JFrame frame;

    public MainView(JFrame frame, Client client) {
        this.client = client;
        this.frame = frame;
        this.setLayout(new CardLayout());

        JPanel login = new LoginView(this);
        this.add(login, "login");

        JPanel lobby = new LobbyView(this);
        this.add(lobby, "lobby");

        JPanel register = new RegisterView(this);
        this.add(register, "register");

        JPanel game = new GameView(this);
        this.add(game, "game");
    }

    public void switchPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) this.getLayout();
        cardLayout.show(this, panelName);
    }
}
