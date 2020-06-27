package com.example.javachipnavigationbar.Model;

import com.google.gson.annotations.SerializedName;

public class Slide {
    @SerializedName("id")
    private int Id;
    @SerializedName("name")
    private String name;
    @SerializedName("rating_bar")
    private Float ratingBar;
    @SerializedName("address")
    private String address;
    @SerializedName("phone")
    private String number;
    @SerializedName("image")
    private String Image;


    public Slide(int id, String name, Float ratingBar, String address, String number, String image) {
        Id = id;
        this.name = name;
        this.ratingBar = ratingBar;
        this.address = address;
        this.number = number;
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(Float ratingBar) {
        this.ratingBar = ratingBar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}