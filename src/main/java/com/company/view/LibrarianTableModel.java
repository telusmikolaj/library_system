package com.company.view;

import com.company.model.User;
import com.company.service.Librarian;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LibrarianTableModel extends AbstractTableModel {
    private final String[] COLUMNS = {"id", "login", "password"};
    private List<Librarian> librarians;
    private String[][] data;


    public LibrarianTableModel(List<Librarian> librarians) {

        this.librarians = librarians;

    }

    @Override
    public int getRowCount() {
        return librarians.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> librarians.get(rowIndex).getId();
            case 1 -> librarians.get(rowIndex).getLogin();
            case 2 -> librarians.get(rowIndex).getPassword();
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
            case 0 -> librarians.get(rowIndex).setId((Integer) aValue);
            case 1 -> librarians.get(rowIndex).setLogin((String) aValue);
            case 2 -> librarians.get(rowIndex).setPassword((String) aValue);
        };
    }
}
