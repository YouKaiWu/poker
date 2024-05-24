import javax.swing.*;

import views.*;
import utility.*;

public class Main {
    private static String token = "";

    private static void login(String account, String password) {
        String format = "{\"account\": \"%s\",\"password\": \"%s\"}";
        String body = String.format(format, account, password);

        Response res = Server.fetch("/api/login", "POST", body);
        if (res.getStatus() != 200) {
            System.err.println("[Error] Login failed");
            return;
        }

        token = res.get("token");
    }

    public static void main(String[] args) {
        // init window
        JFrame frame = new JFrame("Porker");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel main = new MainView();
        frame.add(main);
        // start
        frame.setVisible(true);
    }
}
