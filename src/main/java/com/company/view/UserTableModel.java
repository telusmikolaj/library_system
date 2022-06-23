package com.company.view;

import com.company.model.User;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private final String[] COLUMNS = {"ID", "Name", "Email", "Phone", "Number of borrows"};
    private List<User> users;
    private String[][] data;


    public UserTableModel(List<User> users) {

        this.users = users;

    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> users.get(rowIndex).getId();
            case 1 -> users.get(rowIndex).getName();
            case 2 -> users.get(rowIndex).getEmail();
            case 3 -> users.get(rowIndex).getPhone();
            case 4 -> users.get(rowIndex).getNumberOfBorrows();
            default -> "-";
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getValueAt(0, columnIndex) != null) {
            return getValueAt(0, columnIndex).getClass();
        } else {
            return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) { // custom isCellEditable function
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        switch (columnIndex) {
            case 0 -> users.get(rowIndex).setId((Integer) aValue);
            case 1 -> users.get(rowIndex).setName((String) aValue);
            case 2 -> users.get(rowIndex).setEmail((String) aValue);
            case 3 -> users.get(rowIndex).setPhone((String) aValue);
            case 4 -> users.get(rowIndex).setNumberOfBrrows((Integer) aValue);
        };
    }


}
