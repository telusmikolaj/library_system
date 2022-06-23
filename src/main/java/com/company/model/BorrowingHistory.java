package com.company.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name="borrowingHistory")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingHistory {

    private int id;

    private int userId;
    private int bookId;
    String borrowDate;
    String returnDate;

    public BorrowingHistory() {

    }

    public BorrowingHistory(int userId, int bookId, String borrowDate, String returnDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowingHistory(int id, int userId, int bookId, String borrowDate, String returnDate ) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute(name="id")
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    @XmlAttribute(name= "user_id")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    @XmlAttribute(name = "book_id")
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    @XmlAttribute(name = "borrow_date")
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    @XmlAttribute(name = "return_date")
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
