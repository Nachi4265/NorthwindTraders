package com.pluralsight.userinterface;

import com.pluralsight.model.Category;
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
                    3) Display all categories\n
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
                case 3:
                    displayAllCategories();
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

    private void displayAllCategories()  {
        try{
            List<Category> myCategories = getCategory();
            for(Category c : myCategories){
                System.out.println(c.toString());
            }

        }catch(Exception e)
        {
            System.out.println("There was an error: " + e.getMessage());
            e.printStackTrace();
        }


        try {
            System.out.println("Select a category to display products from");
            String categoryID = InputCollector.promptForString("Select ID ");
            List<Product> myProductsByID = getProductsByID(categoryID);

            System.out.println("Displaying All Products with CategoryID: "+ categoryID);
            System.out.println("-----------------------------------------------------");

            for(Product p : myProductsByID){
                System.out.println(p.toString());
            }

        } catch (Exception e) {
            System.out.println("There was an error: " + e.getMessage());
            e.printStackTrace();
        }
    }



    //GET METHODS
    private List<Category> getCategory() throws ClassNotFoundException, SQLException {
        ArrayList<Category>categories = new ArrayList<>();

        //Load the MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        String productsQuery = "SELECT categoryID,CategoryName,Description FROM categories ORDER BY CategoryID;";


        // create the connection and prepared statement in a
        // try-with-resources block
        try(Connection connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(productsQuery);
        )
        {
            //process the results
            while (result.next()){

                int categoryID = result.getInt("CategoryID");
                String categoryName = result.getString("CategoryName");
                String description = result.getString("Description");

                //making the product
                Category category = new Category(categoryID,categoryName,description);
                categories.add(category);

            }
            return categories;
        }
    }

    private ArrayList<Product> getProducts() throws ClassNotFoundException, SQLException {

        ArrayList<Product>products = new ArrayList<>();

        //Load the MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        String productsQuery = "SELECT ProductID,ProductName,UnitPrice,UnitsInStock FROM products;";


        // create the connection and prepared statement in a
        // try-with-resources block
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

    private ArrayList<Product> getProductsByID(String categoryID) throws ClassNotFoundException, SQLException {

        ArrayList<Product> productByID = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        // create the connection and prepared statement in a
        // try-with-resources block
        try(
                Connection connection = DriverManager.getConnection(URL, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT productID , productName, Unitprice, UnitsInStock FROM products WHERE CategoryID = ?");
        ) {

            preparedStatement.setString(1,categoryID);

            try(ResultSet result = preparedStatement.executeQuery()){
                while (result.next()) {
                    String productName = result.getString("ProductName");
                    int productID = result.getInt("ProductID");
                    double unitPrice = result.getDouble("Unitprice");
                    int stock = result.getInt("UnitsInStock");

                    //making the product
                    Product product = new Product(productID,productName,unitPrice,stock);
                    productByID.add(product);
                }

            }
        } return productByID;
    }

    private List<Customer> getCustomer() throws ClassNotFoundException, SQLException {

        ArrayList<Customer> customers = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        String customerQuery = "SELECT ContactName, CompanyName, City, Country, Phone FROM customers;";


        // create the connection and prepared statement in a
        // try-with-resources block
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
