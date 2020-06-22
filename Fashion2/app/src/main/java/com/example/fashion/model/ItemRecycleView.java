package com.example.fashion.model;

import java.io.Serializable;

public class ItemRecycleView implements Serializable {
    private String Description;
    private String Image;
    private String Name;
    private String Price;


    public ItemRecycleView() {
    }

    public ItemRecycleView(String Decription, String Image, String Name, String Price) {
        this.Description = Decription;
        this.Image = Image;
        this.Name = Name;
        this.Price = Price;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }
}
