package com.pluralsight.userinterface;

import com.pluralsight.model.Customer;
import com.pluralsight.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {

    public static String username = "root";
    public static String password = "yearup";
    public static String dataBase = "northwind";
    public static String URL = "jdbc:mysql://localhost:3306/" + dataBase;

    //HOME SCREEN
    public void homeScreen() {
        

        while (true) {
            String homeScreen = """
                    ===================| NORTHWIND TRADERS |======================
                    What do you want to do?\n 
                    1) Display all products\n
                    2) Display all customers\n
                    0) Exit 
                    """;

            System.out.println(homeScreen);

            int command = InputCollector.promptForInt("Enter a number command");

            switch (command) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    displayAllCustomers();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }



    //DISPLAY METHODS
    private void displayAllCustomers() {

        try{
            List<Customer> myCustomer = getCustomer();
            for(Customer e : myCustomer){
                System.out.println(e.toString());
            }

        }catch(Exception e)
        {
            System.out.println("There was an error: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void displayAllProducts() {

        try{
            List<Product> myProducts = getProducts();
            for(Product p : myProducts){
                System.out.println(p.toString());
            }

        }catch(Exception e)
        {
            System.out.println("There was an error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private ArrayList<Product> getProducts() throws ClassNotFoundException, SQLException {

        ArrayList<Product>products = new ArrayList<>();

        //Load the MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        String productsQuery = "SELECT ProductID,ProductName,UnitPrice,UnitsInStock FROM products;";

        try(Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(productsQuery);
            )
        {
            //process the results
            while (result.next()){

                String productName = result.getString("ProductName");
                int productID = result.getInt("ProductID");
                double unitPrice = result.getDouble("UnitPrice");
                int stock = result.getInt("UnitsInStock");

                //making the product
                Product product = new Product(productID,productName,unitPrice,stock);
                products.add(product);

            }
            return products;
        }
    }

    private List<Customer> getCustomer() throws ClassNotFoundException, SQLException {

        ArrayList<Customer> customers = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        String customerQuery = "SELECT ContactName, CompanyName, City, Country, Phone FROM customers;";

        try(Connection connection = DriverManager.getConnection(URL,username,password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(customerQuery);
            )
        {
            while(result.next()){
                String contactName = result.getString("ContactName");
                String companyName = result.getString("CompanyName");
                String city = result.getString("City");
                String country = result.getString("Country");
                String phoneNum = result.getString("Phone");
                Customer customer = new Customer(contactName,companyName,city,country,phoneNum);

                customers.add(customer);
            }
            return customers;
        }
    }

}
