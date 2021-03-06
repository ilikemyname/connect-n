/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.components;

import ConnectN.client.Client;
import ConnectN.client.model.ClientModel;
import ConnectN.component.Disk;
import ConnectN.component.Player;
import ConnectN.component.Session;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author Dang Hong Thanh 
 */
public class RoomPanel extends JPanel {

    private RoomInfoPanel roomInfoPanel;
    private GamePanel gamePanel;
    private Session room;
    private ClientModel model;
    private String joinPlayer;

    public RoomPanel(Session room, ClientModel model) {
        this.room = room;
        this.model = model;
        this.joinPlayer = "";
        init();
    }

    public RoomPanel(Session room, ClientModel model, String joinPlayer) {
        this.room = room;
        this.model = model;
        this.joinPlayer = joinPlayer;
        init();
    }

    private void init() {        
        roomInfoPanel = new RoomInfoPanel(room, joinPlayer);
        gamePanel = new GamePanel(model, room);

        initLayout();
    }

    private void initLayout() {
        // Prepares the layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.BOTH;
        cons.anchor = GridBagConstraints.FIRST_LINE_START;

        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(5, 74, 5, 10);
        panel.add(roomInfoPanel, cons);

        cons.gridx++;
        cons.gridheight = 2;
        cons.weightx = 1.0;
        cons.weighty = 1.0;
        cons.insets = new Insets(5, 10, 15, 10);
        JScrollPane scrollPane = new JScrollPane(gamePanel);
        // Gets the vertical scrollbar of this scrollpane
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        // Sets the vertical scrollbar to position at bottom
        vertical.setValue(vertical.getMaximum());
        // Increases the scrolling speed
        vertical.setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, cons);

//        setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        setLayout(new BorderLayout());
        add(panel);
    }

    public void updateRoomInfo(Player p) {
        roomInfoPanel.updateRoomInfo(p);
    }

    public void updateGamePanel(Disk disk, int diskColor) {
        gamePanel.update(disk.getX(), diskColor);
    }
}
