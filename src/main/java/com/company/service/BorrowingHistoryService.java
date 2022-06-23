package com.company.service;

import com.company.model.BorrowingHistory;
import com.company.model.User;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class BorrowingHistoryService {

    public static List<BorrowingHistory> loadBorrowingHistory() throws SQLException {
        List<BorrowingHistory> borrowingHistoryList = new ArrayList<>();

        String selectAllRecordsFromBorrowingHistory = "SELECT * FROM borrowing_history";

        try (Connection conn = DBService.open();
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(selectAllRecordsFromBorrowingHistory)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                int book_id = resultSet.getInt("book_id");
                String returnDate = resultSet.getString("return_date");
                String borrowDate = resultSet.getString("borrow_date");

                BorrowingHistory borrowingHistoryRecord = new BorrowingHistory(id, user_id, book_id, borrowDate, returnDate);
                borrowingHistoryList.add(borrowingHistoryRecord);
            }
        }
        return borrowingHistoryList;
    }

    public static int addRecordToBorrowingHistory(BorrowingHistory borrowingHistoryRecord) throws SQLException {

        String addURecrodToBorrowHistoryQuery = "INSERT INTO borrowing_history (user_id, book_id, return_date, borrow_date) VALUES(?,?,?,?)";
        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(addURecrodToBorrowHistoryQuery)) {
            ps.setInt(1, borrowingHistoryRecord.getUserId());
            ps.setInt(2, borrowingHistoryRecord.getBookId());
            ps.setString(3, borrowingHistoryRecord.getReturnDate());
            ps.setString(4, borrowingHistoryRecord.getBorrowDate());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Cannot add new record to history.");
            return 1;
        }
    }

    public static int updateBorrowingHistory(BorrowingHistory borrowingHistory) throws SQLException {
        String updateBorrowingHistoryQuery = "UPDATE borrowing_history set user_id = ?, " +
                "book_id = ?, " +
                "borrow_date = ?, " +
                "return_date = ? " +
                "where id = ?;";


        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(updateBorrowingHistoryQuery)) {
            ps.setInt(1, borrowingHistory.getUserId());
            ps.setInt(2, borrowingHistory.getBookId());
            ps.setString(3, borrowingHistory.getBorrowDate());
            ps.setString(4, borrowingHistory.getReturnDate());
            ps.setInt(5, borrowingHistory.getId());

            return ps.executeUpdate();
        }
    }


}
