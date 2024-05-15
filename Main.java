import javax.swing.*;

import views.*;

public class Main {
    public static void main(String[] args) {
        // init window
        JFrame frame = new JFrame("Porker");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel lobby = new LobbyView();
        frame.add(lobby);

        // start
        frame.setVisible(true);
    }
}
