package com.digitalgeenie.comicbookapp.models;

import java.io.Serializable;

public class TriviaModel implements Serializable {

    private String title;
    private String shortDescription;
    private String thumbnailUrl;
    private String details;

    public TriviaModel(String title, String shortDescription, String thumbnailUrl, String details) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.thumbnailUrl = thumbnailUrl;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
