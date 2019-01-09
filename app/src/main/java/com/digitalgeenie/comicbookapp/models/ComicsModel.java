package com.digitalgeenie.comicbookapp.models;

public class ComicsModel {

    private String title;
    private Integer thumbnail;

    public ComicsModel(String title, Integer thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Integer thumbnail) {
        this.thumbnail = thumbnail;
    }
}
