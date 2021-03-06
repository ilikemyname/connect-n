/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.forms;

import ConnectN.utilities.SegoeFont;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Dang Hong Thanh
 */
public class Dialog_About extends JDialog implements ActionListener {

    public Dialog_About() {
        init();
    }

    private void init() {
        // Initializes the frame
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
//        setAutoRequestFocus(true);
        setAlwaysOnTop(true);

        initLayout();

        pack();
        setLocationRelativeTo(null);
    }

    private void initLayout() {
        // Creates components
        Font title = SegoeFont.getSegoeUIFont("light", 35.0f);
        Font head = SegoeFont.getSegoeUIFont("semibold", 18.0f);
        Font body = SegoeFont.getSegoeUIFont("normal", 14.0f);

        JLabel label;
        JPanel panel;
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(this);
        btnOk.setPreferredSize(new Dimension(100, 36));
        btnOk.setFont(body);

        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        cons.anchor = GridBagConstraints.FIRST_LINE_START;
        cons.fill = GridBagConstraints.HORIZONTAL;

        // Adds components
        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(0, 5, 10, 5);
        label = new JLabel("about");
        label.setFont(title);
        add(label, cons);

        addText(cons, new Insets(0, 5, 3, 5), head, "Connect-N game client");
        addText(cons, new Insets(0, 5, 2, 5), body, "version 0.1.1");
        addText(cons, new Insets(5, 5, 5, 5), body, "");
        addText(cons, new Insets(1, 5, 1, 5), head, "developers");
        addText(cons, new Insets(1, 5, 1, 5), body, "Huy Le");
        addText(cons, new Insets(1, 5, 1, 5), body, "Dang Hong Thanh");
        addText(cons, new Insets(5, 5, 5, 5), body, "");
        addText(cons, new Insets(1, 5, 1, 5), body,
                "This application is an assignment for Software Architecture course,");
        addText(cons, new Insets(1, 5, 1, 5), body,
                "RMIT International University, Vietnam,");
        addText(cons, new Insets(1, 5, 1, 5), body, "Bachelor of IT program.");

        panel = new JPanel(new FlowLayout());
        panel.add(btnOk);

        cons.gridy++;
        cons.insets = new Insets(5, 5, 5, 5);
        add(panel, cons);
    }

    private void addText(GridBagConstraints cons, Font font, String text) {
        cons.gridy++;
        JLabel label = new JLabel(text);
        label.setFont(font);
        add(label, cons);
    }

    private void addText(GridBagConstraints cons, Insets insets, Font font,
            String text) {
        cons.insets = insets;
        addText(cons, font, text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equalsIgnoreCase("ok")) {
            this.dispose();
        }
    }
}
