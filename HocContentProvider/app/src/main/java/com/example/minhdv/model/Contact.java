package com.example.minhdv.model;

/**
 * Created by minhdv on 8/11/2016.
 */
public class Contact {
    private String name;
    private String soDienThoai;

    public Contact() {
    }

    public Contact(String name, String soDienThoai) {
        this.name = name;
        this.soDienThoai = soDienThoai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String toString() {
        return this.name + "\n" + this.soDienThoai;
    }
}
