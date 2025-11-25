package com.pluralsight.model;

public class Customer {

    private String contactName;
    private String companyName;
    private String city;
    private String country;
    private String phoneNumber;

    public Customer(String contactName, String companyName, String city, String country, String phoneNumber) {
        this.contactName = contactName;
        this.companyName = companyName;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("------------------------\n Contact Name: %-5s \n Company Name: %-40s \n City: %-5s \n Country: %-15s \n Phone Number: %-10s" , contactName , companyName ,city , country, phoneNumber);
    }

}
