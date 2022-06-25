package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class BorrowingHistoryList {
    private final List<BorrowingHistory> borrowingHistoryList;

    public BorrowingHistoryList() {
        borrowingHistoryList = new ArrayList<>();
    }

    public void add(BorrowingHistory borrowingHistoryRecord) {
        borrowingHistoryList.add(borrowingHistoryRecord);
    }
}
