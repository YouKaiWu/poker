package views;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;

import utility.*;
import models.*;

public class RoomSelectionDialog extends JDialog {
    private JComboBox<String> roomComboBox;
    private JButton joinButton;
    private String selectedRoom;

    public RoomSelectionDialog(JFrame parent, Client client) {
        super(parent, "Select room", true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        try {
            List<Room> rooms = client.getRooms();
            String[] roomList = new String[rooms.size()];
            for (int i = 0; i < roomList.length; i++) {
                Room room = rooms.get(i);
                roomList[i] = String.format("%s (%d/6)", room.id, room.numPlayers);
            }

            roomComboBox = new JComboBox<>(roomList);
            add(roomComboBox);

            joinButton = new JButton("Join!");
            add(joinButton);

            joinButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedRoom = (String) roomComboBox.getSelectedItem();
                    dispose();
                }
            });
        } catch (Exception e) {
            String[] roomList = { "No room here!" };
            roomComboBox = new JComboBox<>(roomList);
            add(roomComboBox);

            joinButton = new JButton("OK!");
            add(joinButton);

            joinButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }

        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    public String getSelectedRoom() {
        return selectedRoom;
    }
}
