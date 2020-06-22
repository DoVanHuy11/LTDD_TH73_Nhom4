package com.example.fashion.model;

import java.io.Serializable;

public class ItemRecycleView implements Serializable {
    private String Description;
    private String Image;
    private String Name;
    private String Price;
    private String Id;


    public ItemRecycleView() {
    }

    public ItemRecycleView(String Decription, String Image, String Name, String Price,String Id) {
        this.Description = Decription;
        this.Image = Image;
        this.Name = Name;
        this.Price = Price;
        this.Id=Id;
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

    public String getId() {
        return Id;
    }
}
