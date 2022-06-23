package com.company.model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private int id;
   private String name;
    private String phone;
    private String email;
    private int numberOfBrrows;
    private static final AtomicInteger count = new AtomicInteger(0);


    public User(int id, String name, String email, String phone, int numberOfBrrows) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.numberOfBrrows = numberOfBrrows;
    }
    public User(String name, String email, String phone) {
        this.id = count.incrementAndGet();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.numberOfBrrows = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNumberOfBorrows() {
        return numberOfBrrows;
    }

    public void setNumberOfBrrows(int numberOfBrrows) {
        this.numberOfBrrows = numberOfBrrows;
    }

    public void increaseNumberOfBorrows() {
        this.numberOfBrrows ++;
    }

    @Override
    public String toString() {
        return "ID: " + id + ". " + name;
    }
}
