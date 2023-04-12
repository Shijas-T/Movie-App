package com.example.myapplication;

import java.io.Serializable;

public class ShowModel implements Serializable {

    private String showName;
    private String showLanguage;
    private String showPremiered;
    private String showSummary;
    private String showImageUrl;
    private String showId;


    public ShowModel(String showName, String showLanguage, String showPremiered, String showSummary, String showImageUrl, String showId) {
        this.showName = showName;
        this.showLanguage = showLanguage;
        this.showPremiered = showPremiered;
        this.showSummary = showSummary;
        this.showImageUrl = showImageUrl;
        this.showId = showId;
    }

    public String getShowName() {
        return showName;
    }

    public String getShowLanguage() {
        return showLanguage;
    }

    public String getShowPremiered() {
        return showPremiered;
    }

    public String getShowSummary() {
        return showSummary;
    }

    public String getShowImageUrl() {
        return showImageUrl;
    }

    public String getShowId() {
        return showId;
    }
}
