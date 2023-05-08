package com.example.movieapp;

public class CastModel {
    private String castName;
    private String castImageUrl;

    public CastModel(String castName, String castImageUrl) {
        this.castName = castName;
        this.castImageUrl = castImageUrl;
    }

    public String getCastName() {
        return castName;
    }

    public String getCastImageUrl() {
        return castImageUrl;
    }
}
