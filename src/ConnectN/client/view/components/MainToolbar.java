/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.components;

import ConnectN.utilities.SegoeFont;
import ConnectN.utilities.Util;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dang Hong Thanh 
 */
public class MainToolbar extends JPanel {

    private JButton btnBack, btnCreateRoom, btnRandomJoin;
    private JButton btnBack2;
    private JLabel lblWelcom;
    private ActionListener exitListener;
    private boolean isJoined;
//    private CardLayout cardLayout;
//    private Dimension maxCardSize;
//    private static final String CARD_ROOM_LIST = "Room list";
//    private static final String CARD_GAME_VIEW = "Game view";
    private JPanel buttonPanel, welcomePanel;

    public MainToolbar() {
        init();
    }

    public MainToolbar(ActionListener exitListener) {
        this.exitListener = exitListener;

        init();
        initListener();
    }

    private void init() {
        this.isJoined = false;

        // Prepares components
//        cardLayout = new CardLayout(0, 0);

        Font textBtn = SegoeFont.getSegoeUIFont("normal", 25.0f);
        btnBack = Util.makeImgButton("back", "back",
                "Go back", "Back");
        btnBack2 = Util.makeImgButton("back", "back",
                "Go back", "Back");
        btnCreateRoom = new JTextButton("create room", textBtn);
        btnCreateRoom.setActionCommand("create");
        btnRandomJoin = new JTextButton("randomly join", textBtn);
        btnRandomJoin.setActionCommand("random_join");
        lblWelcom = new JLabel("Welcome to the game!");
        lblWelcom.setFont(textBtn);

        setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

        buttonPanel = makeButtonPanel();
        welcomePanel = makeWelcomeTxtPanel();

        add(buttonPanel);
    }

    private void initListener() {
        btnBack.addActionListener(exitListener);
        btnBack2.addActionListener(exitListener);
        btnCreateRoom.addActionListener(exitListener);
        btnRandomJoin.addActionListener(exitListener);
    }

    private JPanel makeButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(5, 25, 5, 3);
        panel.add(btnBack, cons);

        cons.gridx++;
        cons.insets = new Insets(5, 14, 5, 7);
        panel.add(btnCreateRoom, cons);

//        cons.gridx++;
//        cons.insets = new Insets(5, 7, 5, 0);
//        panel.add(btnRandomJoin, cons);

//        maxCardSize = panel.getPreferredSize();

        return panel;
    }

    private JPanel makeWelcomeTxtPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(5, 25, 5, 3);
        panel.add(btnBack2, cons);

        cons.gridx++;
        cons.insets = new Insets(5, 14, 5, 7);
        panel.add(lblWelcom, cons);

//        panel.setPreferredSize(maxCardSize);

        return panel;
    }

    public void change(boolean isJoined) {
        FlowLayout flowLayout = (FlowLayout) this.getLayout();
        if (isJoined) {
            System.out.println("Quit the game");
//            flowLayout.removeLayoutComponent(welcomePanel);
            removeAll();
            add(buttonPanel);
        } else {
            System.out.println("Enter the game");
            removeAll();
            add(welcomePanel);
        }

        validate();
        repaint();
    }
}
