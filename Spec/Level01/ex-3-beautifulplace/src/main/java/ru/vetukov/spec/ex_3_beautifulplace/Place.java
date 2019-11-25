package ru.vetukov.spec.ex_3_beautifulplace;

public class Place {
    private String place;
    private String description;
    private String oldPrice;
    private String newPrice;
    private String picture;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Place(String place, String description, String oldPrice, String newPrice, String picture) {
        this.place = place;
        this.description = description;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.picture = picture;
    }


}
