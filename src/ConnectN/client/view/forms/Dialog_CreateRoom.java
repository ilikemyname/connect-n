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
import ConnectN.component.Session;
import ConnectN.utilities.SegoeFont;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The dialog for the user to create a new room/session.
 *
 * @author Dang Hong Thanh
 */
public class Dialog_CreateRoom extends JDialog implements ActionListener {

    private HintTextField txtRoomName;
    private JSpinner txtN, txtColumn, txtRow, txtTimeOut;
    private JButton btnSave, btnCancel;
    private ErrorTooltip tipNameRequire;
    private boolean validTxtName;
    private ClientModel model;
    private ConnectNView mainView;

    // Controller goes here
    public Dialog_CreateRoom(ConnectNView mainView) {
        validTxtName = true;
        this.mainView = mainView;

        init();
        initListener();
    }

    /**
     * Initializes the dialog.
     */
    private void init() {
        // Initializes the frame
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Create room");
        setResizable(false);
        setVisible(true);

        // Sets up components
        txtRoomName = new HintTextField("Your room's name", 20);
        txtN = new JSpinner(new SpinnerNumberModel(3, 3, 6, 1));
        ((JSpinner.DefaultEditor) txtN.getEditor()).getTextField().setColumns(7);
        txtColumn = new JSpinner(new SpinnerNumberModel(3, 3, 99, 1));
        ((JSpinner.DefaultEditor) txtColumn.getEditor()).getTextField().
                setColumns(7);
        txtRow = new JSpinner(new SpinnerNumberModel(3, 3, 99, 1));
        ((JSpinner.DefaultEditor) txtRow.getEditor()).getTextField().setColumns(
                7);
        txtTimeOut = new JSpinner(new SpinnerNumberModel(0, 0, 3600, 1));
        ((JSpinner.DefaultEditor) txtTimeOut.getEditor()).getTextField().
                setColumns(7);
        tipNameRequire = new ErrorTooltip("You have to fill here :)");

        btnSave = new JButton("Create this room!");
        btnSave.setActionCommand("save");
        btnSave.setFont(SegoeFont.getSegoeUIFont("normal", 14.0f));
        btnCancel = new JButton("or Cancel?");
        btnCancel.setActionCommand("cancel");
        btnCancel.setFont(SegoeFont.getSegoeUIFont("normal", 14.0f));
//        getRootPane().setDefaultButton(btnSave);

        initLayout();

        txtRoomName.requestFocus();

        pack();
//        setMinimumSize(new Dimension(400, 500));
        setLocationRelativeTo(null);
    }

    private void initLayout() {
        // Prepares the components
        Font title = SegoeFont.getSegoeUIFont("light", 30.0f);
        Font header = SegoeFont.getSegoeUIFont("normal", 15.0f);
        Font subHeader = SegoeFont.getSegoeUIFont("italic");

        JLabel label;
        JPanel panel;

        Insets headerInsets = new Insets(3, 3, 3, 3);
        Insets subHeaderInsets = new Insets(-1, 3, 3, 3);
        Insets fieldInsets = new Insets(3, 3, 9, 3);

        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.HORIZONTAL;

        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 4;
        cons.insets = new Insets(3, 3, 5, 8);
        label = new JLabel("Yay! Wanna create a room?");
        label.setFont(title);
        label.setForeground(Color.white);
        add(label, cons);

        cons.gridy++;
        cons.insets = headerInsets;
        label = new JLabel("What's the name of your room?*");
        label.setFont(header);
        add(label, cons);

        cons.gridy++;
        cons.gridwidth = 3;
        cons.insets = fieldInsets;
        add(txtRoomName, cons);

        cons.gridx += 3;
        cons.gridwidth = 6;
        add(tipNameRequire, cons);
        tipNameRequire.setVisible(false);

        cons.gridx = 0;

        cons.gridy++;
        cons.gridx = 0;
        cons.gridwidth = 4;
        cons.insets = headerInsets;
        label = new JLabel("Your desired Connect-N?");
        label.setFont(header);
        add(label, cons);

        cons.gridy++;
        cons.insets = subHeaderInsets;
        label = new JLabel("between 3 and 6...");
        label.setFont(subHeader);
        add(label, cons);

        cons.gridy++;
        cons.gridwidth = 1;
        cons.insets = fieldInsets;
        add(txtN, cons);

        cons.gridy++;
        cons.gridwidth = 4;
        cons.insets = headerInsets;
        label = new JLabel("And the size?");
        label.setFont(header);
        add(label, cons);

        cons.gridy++;
        cons.insets = subHeaderInsets;
        label = new JLabel("columns x rows, and shouldn't less than N");
        label.setFont(subHeader);
        add(label, cons);

        cons.gridy++;
        cons.gridwidth = 1;
        cons.insets = fieldInsets;
        add(txtColumn, cons);

        cons.gridx++;
        cons.insets = new Insets(3, 1, 8, 5);
        add(new JLabel("  x "), cons);

        cons.gridx++;
        cons.insets = fieldInsets;
        add(txtRow, cons);

        cons.gridy++;
        cons.gridx = 0;
        cons.gridwidth = 4;
        cons.insets = headerInsets;
        label = new JLabel("How about the timeout?");
        label.setFont(header);
        add(label, cons);

        cons.gridy++;
        cons.gridwidth = 1;
        cons.insets = fieldInsets;
        add(txtTimeOut, cons);

        cons.gridx++;
        cons.gridwidth = 3;
        label = new JLabel("in seconds; and 0 is unlimited time!");
        label.setFont(subHeader);
        add(label, cons);

        cons.gridx = 0;
        cons.gridy++;
        cons.gridwidth = 4;
        cons.insets = new Insets(6, 3, 6, 0);
        panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        panel.add(btnSave);
        panel.add(btnCancel);
        add(panel, cons);
    }

    private void initListener() {
        // Creates listener for textfields
        ActionListener txtAl = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                btnSave.doClick();
            }
        };
        FocusAdapter txtFa = new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                if (Dialog_CreateRoom.this.isDisplayable()) {
                    validateTxtName();
                }
            }
        };
        KeyAdapter txtKa = new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                validateTxtName();
            }
        };

        txtRoomName.addActionListener(txtAl);
        txtRoomName.addFocusListener(txtFa);
        txtRoomName.addKeyListener(txtKa);

        txtN.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                updateColumnAndRow();
            }
        });

        // Adds listener for the buttons
        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);
    }

    private boolean isValidInput() {
        return !txtRoomName.getText().isEmpty();
    }

    private void validateTxtName() {
        validTxtName = isValidInput();
        tipNameRequire.setVisible(!validTxtName);
        this.pack();
        if (!validTxtName) {
            txtRoomName.requestFocus();
        }
    }

    private void updateColumnAndRow() {
        int n = (int) txtN.getValue();
        txtColumn.setModel(new SpinnerNumberModel(n, n, 99, 1));
        txtRow.setModel(new SpinnerNumberModel(n, n, 99, 1));
    }

    private void saveRoom() {
        String gameDetails = txtRoomName.getText() + ":" + (int) (txtN.getValue()) + ":" + (int) (txtRow.getValue()) + ":" + (int) (txtColumn.getValue()) + ":" + (int) (txtTimeOut.getValue());
        model.createGame(gameDetails);
        mainView.goToRoom(
                new Session(
                    txtRoomName.getText(), 
                    (int)txtRow.getValue(), 
                    (int)txtColumn.getValue(), 
                    (int)txtN.getValue(), 
                    (int)txtTimeOut.getValue(), 
                    model.getPlayer()), 
                true);
        mainView.setIsCreating(false);
        this.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equalsIgnoreCase("save")) {
            // TODO creates room logic here
            validateTxtName();
            if (validTxtName) {
                saveRoom();
            }
        } else if (cmd.equalsIgnoreCase("cancel")) {
            this.dispose();
        }
    }

    public void setModel(ClientModel model) {
        this.model = model;
    }
}
