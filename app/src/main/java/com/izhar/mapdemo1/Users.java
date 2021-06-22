package com.izhar.mapdemo1;

public class Users {
    String lat, lng;

    public Users(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Users() {
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
