package com.pluralsight.model;

public class Product {

    private int productID;
    private String productName;
    private double unitPrice;
    private int unitsInStock;

    public Product(int productID, String productName, double unitPrice, int unitsInStock) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    @Override
    public String toString() {
        return String.format("------------------------\n ID: %-5s \n Name: %-40s \n Price: %-5s \n Stock: %-15s \n" , productID ,productName ,unitPrice , unitsInStock);
    }
}
