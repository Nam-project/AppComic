package com.example.appcomic;

public class TheoDoiModel {
    String email;
    int keyComic;

    public TheoDoiModel() {
    }

    public TheoDoiModel(String email, int keyComic) {
        this.email = email;
        this.keyComic = keyComic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKeyComic() {
        return keyComic;
    }

    public void setKeyComic(int keyComic) {
        this.keyComic = keyComic;
    }
}
