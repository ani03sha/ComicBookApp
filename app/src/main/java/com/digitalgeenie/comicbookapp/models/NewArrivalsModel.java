package com.digitalgeenie.comicbookapp.models;

public class NewArrivalsModel {

    private String title;
    private String price;
    private String thumbnailUrl;

    public NewArrivalsModel(String title, String price, String thumbnailUrl) {
        this.title = title;
        this.price = price;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
