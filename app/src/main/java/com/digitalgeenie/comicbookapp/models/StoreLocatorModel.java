package com.digitalgeenie.comicbookapp.models;

public class StoreLocatorModel {

    private String name;
    private String address;
    private String phone;
    private String thumbnail;

    public StoreLocatorModel(String name, String address, String phone, String thumbnail) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
