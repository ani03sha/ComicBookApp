package com.digitalgeenie.comicbookapp.models;

public class HomeModel {

    private String title;
    private Integer thumbnail;

    public HomeModel(String title, Integer thumbnailUrl) {
        this.title = title;
        this.thumbnail = thumbnailUrl;
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
