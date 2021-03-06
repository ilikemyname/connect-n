/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.components;

import ConnectN.client.view.forms.Dialog_About;
import ConnectN.utilities.SegoeFont;
import ConnectN.utilities.Util;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * The ToolBar of home-screen.
 *
 * @author Dang Hong Thanh 
 */
public class MainMenuBar extends JMenuBar implements ActionListener {

    private JButton btnSettings;
    private String currentUser;
    private ActionListener exitListener;

    public MainMenuBar(String currentUser, ActionListener exitListener) {
        this.currentUser = currentUser;
        this.exitListener = exitListener;
        
        init();
    }

    public MainMenuBar() {
        this.currentUser = "tklarryonline";
        
        init();
    }

    private void init() {
        this.add(makeName());
        this.add(Box.createHorizontalGlue());
        this.add(makeUsernamePanel());
        this.add(makeUserMenu());
    }

    private JPanel makeName() {
        JPanel inPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        cons.anchor = GridBagConstraints.LINE_START;

        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(0, 72, 0, 0);
        JLabel label = Util.makeLabel("Connect-N", "logo", JLabel.CENTER,
                JLabel.LEFT);
        label.setFont(SegoeFont.getSegoeUIFont("normal", 28.0f));
        label.setForeground(Color.white);
        inPanel.add(label, cons);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        panel.add(inPanel);

        return panel;
    }

    private JMenu makeUserMenu() {
        // Index of the item
        int index = 0;

        JMenu menu = new JMenu();

        menu.setIcon(Util.makeIcon("setting"));

        // Adds 'Help' menu item
        menu.add(makeMenuItem("get some help", KeyEvent.VK_H, KeyEvent.VK_F1, 0,
                "help"));
        index++;

        // Adds 'about this small piece of app...' menu item
        JMenuItem about = makeMenuItem("about this small piece of app...",
                KeyEvent.VK_A,
                0, 0, "about");
        about.addActionListener(this);
        menu.add(about);

        return menu;
    }

    /**
     *
     */
    private JPanel makeUsernamePanel() {
        // Prepares the components
        JPanel gridBagPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        Font user = SegoeFont.getSegoeUIFont("semibold", 12.0f);
        Font btn = SegoeFont.getSegoeUIFont("normal", 11.0f);

        cons.anchor = GridBagConstraints.LINE_END;
        cons.insets = new Insets(0, 0, 0, 0);

        cons.gridx = 0;
        cons.gridy = 0;
        JLabel label = new JLabel(currentUser, SwingConstants.TRAILING);
        label.setFont(user);
        label.setForeground(Color.white);
        gridBagPanel.add(label, cons);

        cons.gridy++;
        JTextButton button = new JTextButton("LOG OUT", btn);
        button.setActionCommand("logout");
        button.addActionListener(exitListener);
        gridBagPanel.add(button, cons);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 0, 0));
        panel.add(gridBagPanel);

        return panel;
    }

    /**
     * Creates an Iconized MenuItem based on the input info. Use the other
     * method to create a standard one.
     *
     * @param name The name of the menu item.
     * @param key The Mnemonic KeyEvent for this menu item.
     * @param icon The icon name of this menu item. Cannot be null and must be
     * the same with the icon file name.
     * @param ac The Accelerator KeyEvent for this menu item.
     * @param mask The Mask (Ctrl, Shift or Alt) for this menu item.
     * @param cmd The ActionCommand for this menu item. Cannot be null.
     * @return The created MenuItem
     */
    private JMenuItem makeMenuItem(String name, int key, String icon, int ac,
            int mask, String cmd) {
        JMenuItem m = makeMenuItem(name, key, ac, mask, cmd);

        ImageIcon ic = Util.makeIcon(icon);
        m.setIcon(ic);

        return m;
    }

    /**
     * Creates a non Iconized MenuItem based on the input info.
     *
     * @param name The name of the menu item.
     * @param key The Mnemonic KeyEvent for this menu item.
     * @param ac The Accelerator KeyEvent for this menu item.
     * @param mask The Mask (Ctrl, Shift or Alt) for this menu item.
     * @param cmd The ActionCommand for this menu item. Cannot be null.
     * @return The created MenuItem
     */
    private JMenuItem makeMenuItem(String name, int key, int ac,
            int mask, String cmd) {
        JMenuItem m = new JMenuItem(name, key);

        // Specifies a standard menu item.
        if (ac != 0) {
            m.setAccelerator(KeyStroke.getKeyStroke(ac, mask));
        }
        m.setActionCommand(cmd);
//        m.addActionListener(controller);

        return m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equalsIgnoreCase("about")) {
            new Dialog_About();
        }
    }
}
