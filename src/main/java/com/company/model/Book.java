package com.company.model;

import java.util.List;

public class Book {

    private int id;
    private String title;
    private String publisher;
    private String author;
    private int availbility;
    private String pages;
    private String genre;
    private int numOfBorrows;


    public Book(String title, String author,String genre, String pages, String publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.pages = pages;
        this.availbility = 1;
        this.numOfBorrows = 0;
    }

    public Book(int id, String title, String author,
                String genre, String pages,
                String publisher, int availbility, int numOfBorrows) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.pages = pages;
        this.availbility = availbility;
        this.numOfBorrows = numOfBorrows;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getAvailbility() {
        return availbility;
    }

    public void setAvailbility(int availbility) {
        this.availbility = availbility;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumOfBorrows() {
        return numOfBorrows;
    }

    public void incrementNumOfBorrows() {
        this.numOfBorrows++;
    }

    public void setNumOfBorrows(int numOfBorrows) {
        this.numOfBorrows = numOfBorrows;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                ", availbility=" + availbility +
                ", pages='" + pages + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
