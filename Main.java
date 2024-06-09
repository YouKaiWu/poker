import javax.swing.*;

import views.*;
import utility.*;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();

        // init window
        JFrame frame = new JFrame("Porker");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel main = new MainView(frame, client);
        frame.add(main);
        // start
        frame.setVisible(true);
    }
}
