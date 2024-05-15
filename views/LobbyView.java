package views;

import javax.swing.*;
import java.awt.*;

import models.*;

public class LobbyView extends JPanel {
    public LobbyView() {
        this.setBackground(new Color(0, 100, 0));

        Card card = new Card(Card.Suit.DIAMOND, 1);
        CardView cv = new CardView(card);

        this.add(cv);

        // TODO: 實作主選單
    }
}
