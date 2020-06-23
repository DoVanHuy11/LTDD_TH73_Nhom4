package com.example.fashion.model;

public class Cart {
    public String idsp;
    public String tensp;
    public long giasp;
    public String hinhsp;
    //public int hinhsp;
    public int soluong;

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String Size;

    public Cart(String idsp, String tensp, long giasp, String hinhsp, int soluong,String Size) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhsp = hinhsp;
        this.soluong = soluong;
        this.Size = Size;
    }

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
