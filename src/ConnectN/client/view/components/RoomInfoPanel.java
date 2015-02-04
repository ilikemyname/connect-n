/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.components;

import ConnectN.component.Player;
import ConnectN.component.Session;
import ConnectN.utilities.SegoeFont;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dang Hong Thanh 
 */
public class RoomInfoPanel extends JPanel {

    private Session room;
    /**
     * Player who hosts the room.
     */
    private String player1;
    /**
     * Player who joined the room.
     */
    private String player2;
    private JLabel guestLbl;

    public RoomInfoPanel() {
        init();
    }

    public RoomInfoPanel(Session room, String player2) {
        this.room = room;
        
        this.player1 = room.getCreator().getName();
        this.player2 = player2;
        
        init();
    }

    private void init() {
        // Prepares the components
        Font title = SegoeFont.getSegoeUIFont("light", 25.0f);
        Font head = SegoeFont.getSegoeUIFont("normal", 18.0f);
        Font text = SegoeFont.getSegoeUIFont("normal", 14.0f);

        // Prepares the layout
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 0;
        cons.gridy = -1;

        // Room no. 4
        addLabel("Room no. " + room.getId() + ":", cons, text, new Insets(1, 0,
                1, 0));
        // Room's name: Testers wanna test!
        addLabel(room.getName(), cons, head);
        // Connect-4 game - Board 4 x 6
        addLabel("Connect-" + room.getN() + " game - Board " 
                + room.getCols() + " x " + room.
                getRows(), cons, text);
        // 60 seconds timeout!
        addLabel(room.displayTimeOut(), cons, text);
        // Host
        addLabel("Host: " + player1, cons, text);
        // Guest
        cons.gridy++;
        guestLbl = new JLabel("Guest: " + player2);
        guestLbl.setFont(text);
        add(guestLbl, cons);
    }

    private void addLabel(String text, GridBagConstraints cons, Font font) {
        cons.gridy++;
        JLabel label = new JLabel(text);
        label.setFont(font);
        add(label, cons);
    }

    private void addLabel(String text, GridBagConstraints cons, Font font,
            Insets insets) {
        cons.insets = insets;
        addLabel(text, cons, font);
    }
    
    public void updateRoomInfo(Player joinPlayer) {
        this.player2 = joinPlayer.getName();
        this.guestLbl.setText("Guest: " + player2);
        this.repaint();
    }
}
