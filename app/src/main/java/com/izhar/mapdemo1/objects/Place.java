package com.izhar.mapdemo1.objects;

public class Place {
    String category, name, latitude, longtude;

    public Place() {
    }

    public Place(String category, String name, String latitude, String longtude) {
        this.category = category;
        this.name = name;
        this.latitude = latitude;
        this.longtude = longtude;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtude() {
        return longtude;
    }
}
