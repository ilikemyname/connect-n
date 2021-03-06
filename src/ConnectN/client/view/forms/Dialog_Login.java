/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.forms;

import ConnectN.client.model.ClientModel;
import ConnectN.client.view.ConnectNView;
import ConnectN.client.view.components.ErrorTooltip;
import ConnectN.client.view.components.HintTextField;
import ConnectN.utilities.SegoeFont;
import ConnectN.utilities.Util;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The dialog asks the user to input their display name and connect with server.
 *
 * @author Dang Hong Thanh
 */
public class Dialog_Login extends JDialog implements ActionListener,
        FocusListener {

    private HintTextField txtName;
    private JButton btnStart;
    private ErrorTooltip tipNameRequire;
    private CardLayout cardLayout = new CardLayout() {

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            for (Component component : getComponents()) {
                if (component.isVisible()) {
                    return component.getPreferredSize();
                }
            }
            return super.preferredLayoutSize(parent);
        }
    };
    private boolean validTxtName;
    private ClientModel model;

    public Dialog_Login() {
        this.validTxtName = true;

        init();

    }

    private void init() {
        // Initializes the frame
        setTitle(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setVisible(true);
        setResizable(false);

        // Sets up components
        cardLayout = new CardLayout(0, 0);
        txtName = new HintTextField("Your display name", 40);
        btnStart = Util.makeImgButton("next", "login", "Start", "Start");
        tipNameRequire = new ErrorTooltip("You have to fill this :)");

        initLayout();
        initListener();
        txtName.requestFocus();

        pack();
        setLocationRelativeTo(null);
    }

    private void initLayout() {
        // Prepares the components
        Font title = SegoeFont.getSegoeUIFont("light", 55.0f);
        Font header = SegoeFont.getSegoeUIFont("normal", 16.0f);

        JLabel label;
        JPanel panel;

        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.HORIZONTAL;

        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 1;
        cons.insets = new Insets(0, 15, 5, 0);
        cons.anchor = GridBagConstraints.LAST_LINE_START;
        label = new JLabel(";)");
        label.setFont(SegoeFont.getSegoeUIFont("light", 80.0f));
        label.setForeground(Color.white);
        add(label, cons);

        cons.gridx++;
        cons.gridwidth = 2;
        cons.insets = new Insets(0, 5, 0, 15);
        label = new JLabel("Connect-N: Hi!");
        label.setFont(title);
        label.setForeground(Color.white);
        add(label, cons);

        cons.gridy++;
        cons.insets = new Insets(10, 5, 3, 15);
        label = new JLabel("Could we know your display name before starting?");
        label.setFont(header);
        add(label, cons);

        cons.gridy++;
        cons.gridwidth = 1;
        cons.insets = new Insets(0, 5, 30, 4);
        add(txtName, cons);

        cons.gridx++;
        cons.insets = new Insets(0, 4, 30, 15);
        add(btnStart, cons);

        cons.gridx++;
        cons.insets = new Insets(0, 4, 28, 15);
        add(tipNameRequire, cons);
        tipNameRequire.setVisible(false);

//        JPanel btnStartPanel = new JPanel();
//        btnStartPanel.add(btnStart);
//        JPanel tooltipPanel = new JPanel();
//        tooltipPanel.add(tipNameRequire);
//        
//        panel = new JPanel(cardLayout);
//        panel.add(btnStart, "start");
//        panel.add(tipNameRequire, "tip");
//        cardLayout.show(panel, "start");

//        cons.gridx++;
//        cons.insets = new Insets(0, 4, 30, 15);
//        add(panel, cons);
    }

    private void initListener() {
        txtName.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (Dialog_Login.this.isDisplayable()) {
                    validateTxtName();
                }
            }
        });
        txtName.addKeyListener(Util.makeEscapeKeyAdapter(this));
        txtName.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnStart.doClick();
            }
        });

        btnStart.addActionListener(this);
    }

    private boolean isValidInput() {
        return !txtName.getText().isEmpty();
    }

    private void validateTxtName() {
        validTxtName = isValidInput();
        tipNameRequire.setVisible(!validTxtName);
        btnStart.setVisible(validTxtName);
        this.pack();
        if (!validTxtName) {
            txtName.requestFocus();
        }
    }

    private void startGame() throws IOException {
        System.out.println("Start game");
        // TODO starts the game here
        //Start to connect to server        
        model = new ClientModel(txtName.getText());
        model.start();     
        model.setView(new ConnectNView(model, model.getPlayer()));
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            validateTxtName();
            if (validTxtName) {
                startGame();
            }
        } catch (IOException ex) {
            Logger.getLogger(Dialog_Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
//        validateTxtName();
    }
}
