package com.example.trucker;

public class Driver implements Comparable<Driver>{

    private static int nextId = 1;

    private int id;
    private String name;
    private String surname;
    private String birth;
    private String address;
    private String phone;
    private String email;
    private String psw;

    public Driver(int id, String name, String surname, String birth, String address, String phone, String email, String psw) {
        this.id = id;
        if (this.id >= nextId) {
            nextId = this.id + 1;
        }
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.psw = psw;
    }
    public Driver(String name, String surname, String birth, String address, String phone, String email, String psw) {
        this.id = nextId++;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.psw = psw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth='" + birth +'\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }
    @Override
    public int compareTo(Driver o) {
        return 0;
    }
}
