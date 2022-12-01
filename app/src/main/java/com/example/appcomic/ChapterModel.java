package com.example.appcomic;

public class ChapterModel {
    int key, keyComic;
    String noiDung;

    public ChapterModel() {
    }

    public ChapterModel(int key, int keyComic, String noiDung) {
        this.key = key;
        this.keyComic = keyComic;
        this.noiDung = noiDung;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKeyComic() {
        return keyComic;
    }

    public void setKeyComic(int keyComic) {
        this.keyComic = keyComic;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
