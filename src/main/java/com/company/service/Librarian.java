package com.company.service;

public class Librarian {

    private int id;
    private String login;
    private String password;

    public Librarian(int id, String login, String password) {
        this.login = login;
        this.password = password;
        this.id = id;
    }
    public Librarian(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
