package com.company.view;

import com.company.model.BorrowingHistory;
import com.company.model.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BorrowingHistoryTableModel extends AbstractTableModel {

    private final String[] COLUMNS = {"id", "user_id", "book_id", "borrow_date", "return_date"};
    private List<BorrowingHistory> borrowingHistoryList;
    private String[][] data;


    public BorrowingHistoryTableModel(List<BorrowingHistory> borrowingHistoryList) {

        this.borrowingHistoryList = borrowingHistoryList;
    }

    @Override
    public int getRowCount() {
        return borrowingHistoryList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> borrowingHistoryList.get(rowIndex).getId();
            case 1 -> borrowingHistoryList.get(rowIndex).getUserId();
            case 2 -> borrowingHistoryList.get(rowIndex).getBookId();
            case 3 -> borrowingHistoryList.get(rowIndex).getBorrowDate();
            case 4 -> borrowingHistoryList.get(rowIndex).getReturnDate();
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

//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
//    {
//        switch (columnIndex) {
//            case 0 -> users.get(rowIndex).setId((Integer) aValue);
//            case 1 -> users.get(rowIndex).setName((String) aValue);
//            case 2 -> users.get(rowIndex).setEmail((String) aValue);
//            case 3 -> users.get(rowIndex).setPhone((String) aValue);
//        };
//    }
}
