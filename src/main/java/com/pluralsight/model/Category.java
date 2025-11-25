package com.pluralsight.model;

public class Category {

    private int categoryID;
    private String categoryName;
    private String description;

    public Category(int categoryID, String categoryName, String description) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("------------------------\n ID: %-5s \n Name: %-40s \n Description: %-15s \n" ,categoryID ,categoryName ,description);
    }
}
