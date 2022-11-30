package com.example.appcomic;

public class ComicModel {
    String image, name, tacGia, tomTat;

    public ComicModel() {
    }

    public ComicModel(String image, String name, String tacGia, String tomTat) {
        this.image = image;
        this.name = name;
        this.tacGia = tacGia;
        this.tomTat = tomTat;
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
