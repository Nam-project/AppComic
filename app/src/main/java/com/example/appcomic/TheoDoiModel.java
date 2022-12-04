package com.example.appcomic;

public class TheoDoiModel {
    String email, img, name;
    int keyComic;

    public TheoDoiModel() {
    }

    public TheoDoiModel(String email, String img, String name, int keyComic) {
        this.email = email;
        this.img = img;
        this.name = name;
        this.keyComic = keyComic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKeyComic() {
        return keyComic;
    }

    public void setKeyComic(int keyComic) {
        this.keyComic = keyComic;
    }
}
