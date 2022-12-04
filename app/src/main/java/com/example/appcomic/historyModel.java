package com.example.appcomic;

public class historyModel {
    String image, name, tacGia, tomTat, theLoai, email;
    int key;

    public historyModel() {
    }

    public historyModel(String image, String name, String tacGia, String tomTat, String theLoai, String email, int key) {
        this.image = image;
        this.name = name;
        this.tacGia = tacGia;
        this.tomTat = tomTat;
        this.theLoai = theLoai;
        this.email = email;
        this.key = key;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
