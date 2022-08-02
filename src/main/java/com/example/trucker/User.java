package com.example.trucker;

public class User implements Comparable<User>{
    private static int nextId = 1;

    private int id;
    private String userName;
    private String password;

    public User(int id,String userName, String password) {
        this.id = id;
        if (this.id >= nextId) {
            nextId = this.id + 1;
        }
        this.userName = userName;
        this.password = password;
    }
    public User(String userName, String password) {
        this.id = nextId++;
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }

    public int getId(){return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("ID: %-4d, Username: %-16s, pasword: %-10s",id,userName,password);
    }
    @Override
    public int compareTo(User o) {
        return 0;
    }
}
