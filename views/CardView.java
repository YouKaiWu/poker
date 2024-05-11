package views;

import javax.swing.*;
import java.awt.*;

import models.*;

public class CardView extends JPanel {
    private ImageIcon imageIcon;
    private JLabel cardImg;
    private Card card;

    public CardView(Card card) {
        this.setCard(card);
        this.setOpaque(false);
    }

    public void setCard(Card card) {
        this.card = card;

        String path = "./images/card-face/" + card.toString() + ".png";
        this.setImage(path);
    }

    private void setImage(String path) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image image = originalIcon.getImage().getScaledInstance(80, -1, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(image);
        cardImg = new JLabel(imageIcon);
        this.removeAll();
        this.add(cardImg);
    }
}
