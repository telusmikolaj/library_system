package com.company.view;

import com.company.model.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel {
    private final String[] COLUMNS = {"ID", "Title", "Author","Genre", "Pages", "Publisher", "Availability", "Number of borrows"};
    private List<Book> bookList;


    public BookTableModel(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public int getRowCount() {
        return bookList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> bookList.get(rowIndex).getId();
            case 1 -> bookList.get(rowIndex).getTitle();
            case 2 -> bookList.get(rowIndex).getAuthor();
            case 3 -> bookList.get(rowIndex).getGenre();
            case 4 -> bookList.get(rowIndex).getPublisher();
            case 5 -> bookList.get(rowIndex).getPages();
            case 6 -> bookList.get(rowIndex).getAvailbility();
            case 7 -> bookList.get(rowIndex).getNumOfBorrows();
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
            case 0 -> bookList.get(rowIndex).setId((Integer) aValue);
            case 1 -> bookList.get(rowIndex).setTitle((String) aValue);
            case 2 -> bookList.get(rowIndex).setAuthor((String) aValue);
            case 3 -> bookList.get(rowIndex).setGenre((String) aValue);
            case 4 -> bookList.get(rowIndex).setPublisher((String) aValue);
            case 5 -> bookList.get(rowIndex).setPages((String) aValue);
            case 6 -> bookList.get(rowIndex).setAvailbility((Integer) aValue);
            case 7 -> bookList.get(rowIndex).setNumOfBorrows((Integer) aValue);
        };
    }

}
