package views;

import javax.swing.*;
import java.awt.*;

import models.*;

public class HorizontalDeckView extends JPanel {
    public HorizontalDeckView(Deck deck) {
        this.setOpaque(false);
        this.update(deck);
    }

    public Image toImage(Card card) {
        String path = "./images/card-face/" + card.toString() + ".png";
        ImageIcon originalIcon = new ImageIcon(path);
        Image image = originalIcon.getImage().getScaledInstance(75, 105, Image.SCALE_SMOOTH);
        return image;
    }

    public void update(Deck deck) {
        this.removeAll();
        for (Card card : deck) {
            Image image = toImage(card);
            ImageIcon imageIcon = new ImageIcon(image);
            JLabel cardImg = new JLabel(imageIcon);
            this.add(cardImg);
        }
        this.revalidate();
        this.repaint();
    }
}
