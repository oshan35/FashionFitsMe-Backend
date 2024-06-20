package com.example.VirtualFitON.DTO;

public class Filter {
    private String title;
    private String category;

    public Filter(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public Filter() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
