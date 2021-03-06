/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.view.components;

import ConnectN.client.model.ClientModel;
import ConnectN.component.Session;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Dang Hong Thanh 
 */
public class RoomsTable extends JPanel {

    private MouseListener tableListener;
    private ClientModel model;

    public RoomsTable() {
        init();
    }

    public RoomsTable(MouseListener tableListener, ClientModel model) {
        this.model = model;
        this.tableListener = tableListener;

        init();
    }

    public RoomsTable(MouseListener tableListener) {
        this.tableListener = tableListener;

        init();
    }

    private void init() {
        initLayout();
    }

    private void initLayout() {
        // Prepares the layout
        setLayout(new GridBagLayout());
//        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();

        cons.fill = GridBagConstraints.BOTH;

        cons.gridx = 0;
        cons.gridy = 0;
        cons.weightx = 1.0;
        cons.insets = new Insets(5, 74, 5, 10);
        add(makeTablePane(), cons);

        setBorder(null);
    }

    private JScrollPane makeTablePane() {
        RoomsTableModel tableModel = new RoomsTableModel(model.getSessionList());
        String[] columnNames = {"No", "Room name", "Connect-?", "Size (cxr)",
            "Timeout (secs)"};
//        JTable table = new JTable(generateTestData(), columnNames);
        JTable table = new JTable(tableModel);

        // Changes the width of columns
        TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(
                    i == 1 ? 200
                    : i == 2 ? 80
                    : i == 3 ? 80
                    : i == 4 ? 100 : 20);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setCellEditor(null);

        scrollPane.setBorder(null);
        table.setBorder(null);

        table.addMouseListener(tableListener);

        return scrollPane;
    }

    private Object[][] generateTestData() {
//        Object[][] objs = new Object[200][5];
//        Random ran = new Random();
//        for (int i = 0; i < 200; i++) {
//            for (int j = 0; j < 5; j++) {
//                if (j == 1) {
//                    objs[i][j] = Dictionary.getRandomProperName();
//                } else if (j == 2) {
//                    objs[i][j] = ran.nextInt(4) + 3;
//                } else if (j == 3) {
//                    int c = ran.nextInt(10000) + (int) objs[i][j - 1];
//                    int r = ran.nextInt(10000) + (int) objs[i][j - 1];
//                    objs[i][j] = c + " x " + r;
//                } else {
//                    objs[i][j] = i * j;
//                }
//            }
//        }
//
//        return objs;
        List<Session> objectArray = model.getSessionList();
        if (objectArray == null || objectArray.isEmpty()) {
            return new Object[][]{{"No session"}};
        } else {
            Object[][] objs = new Object[objectArray.size()][5];
            for (int i = 0; i < objectArray.size(); i++) {
                for (int j = 0; j < 5; j++) {
                    Session session = objectArray.get(i);
                    switch (j) {
                        case 0:
                            objs[i][j] = session.getId();
                            break;
                        case 1:
                            objs[i][j] = session.getName();
                            break;
                        case 2:
                            objs[i][j] = session.getN();
                            break;
                        case 3:
                            objs[i][j] = session.getRows() + " x " + session.getCols();
                            break;
                        case 4:
                            objs[i][j] = session.getTimeout();
                            break;
                        default:
                            break;
                    }
                }

            }
            return objs;
        }
    }
}
