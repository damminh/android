package com.example.minhdv.model;

/**
 * Created by minhdv on 8/27/2016.
 */
public class Contract {
    private String name;
    private String city;
    private String country;

    public Contract() {
    }

    public Contract(String name, String city, String country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.city+"\n" + this.country;
    }
}
