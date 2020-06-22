package com.example.fashion.model;

public class ItemRecycleView {
    String image;
    String name;
    String price;
    String decribe;

    public ItemRecycleView() {}

    public ItemRecycleView(String image, String name, String price, String decribe) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.decribe = decribe;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDecribe() {
        return decribe;
    }
}
