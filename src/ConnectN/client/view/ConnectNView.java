/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Luan Nguyen Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view;

import ConnectN.client.model.ClientModel;
import ConnectN.client.view.components.*;
import ConnectN.client.view.forms.Dialog_CreateRoom;
import ConnectN.client.view.forms.Dialog_Login;
import ConnectN.component.Disk;
import ConnectN.component.Player;
import ConnectN.component.Session;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author Dang Hong Thanh
 */
public class ConnectNView extends JFrame implements ActionListener,
        MouseListener {

    private boolean isJoined;
    private Player player;
    private ClientModel model;
    private RoomsTable roomsTable;
    private RoomPanel roomPanel;
    private MainToolbar mainToolbar;
    private boolean isCreating;

    /**
     * Constructs a normal View with no event handlers.
     */
    public ConnectNView() {
        init();
    }

    public ConnectNView(Player player) {
        this.player = player;
        this.isJoined = false;
        init();
    }

    public ConnectNView(ClientModel model, Player player) {
        this.model = model;
        this.player = player;
        this.isJoined = false;
        init();
    }

    private void init() {
        setTitle("Connect-N client v0.1");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE));

        roomsTable = new RoomsTable(this, model);
        mainToolbar = new MainToolbar(this);
        isCreating = false;

        initLayout();

        setMinimumSize(new Dimension(1000, 600));
        setLocationRelativeTo(null);
    }

    private void initLayout() {
        setLayout(new BorderLayout());

        setJMenuBar(new MainMenuBar(player.getName(), this));
        add(mainToolbar, BorderLayout.NORTH);
        add(roomsTable, BorderLayout.CENTER);
    }

    public void setIsCreating(boolean isCreating) {
        this.isCreating = isCreating;
    }

    public void goToRoom(Session room, boolean isCreated) {
        System.out.println("Selected room: " + room);
        isJoined = true;

        remove(roomsTable);
        if (isCreated) {
            roomPanel = new RoomPanel(room, model);
        } else {
            roomPanel = new RoomPanel(room, model, model.getPlayer().getName());
            model.joinGame(room.getId());
        }
        add(roomPanel, BorderLayout.CENTER);
        mainToolbar.change(!isJoined);

        validate();
        repaint();

    }

    public void goBackToList() {
        isJoined = false;
        remove(roomPanel);

        roomsTable = new RoomsTable(this, model);
        add(roomsTable, BorderLayout.CENTER);

        mainToolbar.change(!isJoined);

        validate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equalsIgnoreCase("logout")) {
            model.quitGame();
            model.getServer().close();
            model.stop();
            mainToolbar.change(isJoined);
            new Dialog_Login();
            this.dispose();
        } else if (cmd.equalsIgnoreCase("back")) {
            model.quitGame();
            //mainToolbar.change(isJoined);
            if (!isJoined) { // equals "logout"
                new Dialog_Login();
                this.dispose();
            }
//            else {
//                goBackToList();
//            }
        } else if (cmd.equalsIgnoreCase("create")) {
            if (!isCreating) {
                isCreating = true;
                (new Dialog_CreateRoom(this)).setModel(model);
            }
        } else if (cmd.equalsIgnoreCase("random_join")) {
            // TODO do random join here
        }
    }

    public void updateRoomInfo(Player p) {
        roomPanel.updateRoomInfo(p);
    }

    public void updateGamePanel(Disk disk, int diskColor) {
        roomPanel.updateGamePanel(disk, diskColor);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            try {
                if (e.getSource() instanceof JTable) {
                    JTable table = (JTable) e.getSource();
                    goToRoom(((RoomsTableModel) table.getModel()).getObjectAt(table.getSelectedRow()), false);
                }
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
