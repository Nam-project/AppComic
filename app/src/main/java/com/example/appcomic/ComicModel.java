package com.example.appcomic;

public class ComicModel {
    String image, name, tacGia, tomTat, theLoai;
    int key;

    public ComicModel() {
    }

    public ComicModel(String image, String name, String tacGia, String tomTat, String theLoai, int key) {
        this.image = image;
        this.name = name;
        this.tacGia = tacGia;
        this.tomTat = tomTat;
        this.theLoai = theLoai;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
}
