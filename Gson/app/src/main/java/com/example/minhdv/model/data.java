package com.example.minhdv.model;

/**
 * Created by minhdv on 9/7/2016.
 */
public class data {
    private String typename;
    private String sell;


    public data(String sell, String typename) {
        this.sell = sell;
        this.typename = typename;
    }

    public data() {
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }
}
