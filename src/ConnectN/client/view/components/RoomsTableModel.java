/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Dang Hong Thanh, with any partners involved.
 * The use of this code should not be permitted!
 * If you want to use, please do contact me!
 */
package ConnectN.client.view.components;

import ConnectN.component.Session;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dang Hong Thanh 
 */
public class RoomsTableModel extends AbstractTableModel {

    private String[] columnNames = {
        "No", "Room name", "Creator", "Connect-?", "Size (cxr)", "Timeout (secs)"
    };
    private Class[] classes = {
        Integer.class, String.class, String.class, Integer.class, String.class, String.class
    };
    private List<Session> list;

    public RoomsTableModel(List list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classes[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Session session = list.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return session.getId();
            case 1:
                return session.getName();
            case 2:
                return session.getCreator();
            case 3:
                return "Connect-" + session.getN();
            case 4:
                return session.getCols() + " x " + session.getRows();
            case 5:
                return session.displayTimeOut();
            default:
                return null;
        }
    }
    
    public Session getObjectAt(int index) {
        return list.get(index);
    }
}
