package com.example.myapplication;

import java.io.Serializable;

public class ShowModel implements Serializable {

    private String showName;
    private String showLanguage;
    private String showPremiered;
    private String showSummary;

    public ShowModel(String showName, String showLanguage, String showPremiered, String showSummary) {
        this.showName = showName;
        this.showLanguage = showLanguage;
        this.showPremiered = showPremiered;
        this.showSummary = showSummary;
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
}
