package views;

import javax.swing.*;
import java.awt.*;

import models.*;

public class CardView extends JPanel {
    private ImageIcon imageIcon;
    private JLabel cardImg;

    public CardView(Card card) {
        this.update(card);
        this.setOpaque(false);
    }

    public void update(Card card) {
        String path = "./images/card-face/" + card.toString() + ".png";
        ImageIcon originalIcon = new ImageIcon(path);
        Image image = originalIcon.getImage().getScaledInstance(80, -1, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(image);
        cardImg = new JLabel(imageIcon);
        this.removeAll();
        this.add(cardImg);
    }
}
