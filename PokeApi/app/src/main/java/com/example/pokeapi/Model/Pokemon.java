package com.example.pokeapi.Model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    //implementation 'com.squareup.picasso:picasso:2.71828'

    private int number;
    private String name;
    private String url;
    private String height;
    //private List<type> type=null;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlParts = url.split("/");
        return Integer.parseInt(urlParts[urlParts.length-1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
