package com.example.fashion.model;

public class ItemNavigation {
    int image;
    String titLe;
    public ItemNavigation(int image , String titLe){
        this.image = image;
        this.titLe = titLe;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitLe(String titLe) {
        this.titLe = titLe;
    }

    public int getImage() {
        return image;
    }

    public String getTitLe() {
        return titLe;
    }
}
