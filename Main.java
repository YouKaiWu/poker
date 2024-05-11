import javax.swing.*;
import java.awt.*;

import views.*;
import models.*;

public class Main {
    public static void main(String[] args) {
        // init window
        JFrame frame = new JFrame("Porker");
        frame.setSize(1000, 600);
        frame.getContentPane().setBackground(new Color(0, 100, 0));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a card
        Card card = new Card(Card.Suit.CLUB, 1);
        CardView cardView = new CardView(card);
        frame.add(cardView);

        cardView.setCard(new Card(Card.Suit.CLUB, 2));

        // start
        frame.setVisible(true);
    }
}
