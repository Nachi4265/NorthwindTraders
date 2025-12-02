package com.pluralsight.Persistance;

import com.pluralsight.model.Category;
import com.pluralsight.model.Customer;
import com.pluralsight.model.Product;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    //This class data source
    private BasicDataSource dataSource;



    //METHODS


    public DataManager(BasicDataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Category> getAllCategories() throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();


        String productsQuery = "SELECT categoryID,CategoryName,Description FROM categories ORDER BY CategoryID;";


        // create the connection and prepared statement in a
        // try-with-resources block
        try(Connection connection = dataSource.getConnection();
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

    public List<Customer> getAllCustomers()throws SQLException {

        ArrayList<Customer> customers = new ArrayList<>();

        String customerQuery = "SELECT ContactName, CompanyName, City, Country, Phone FROM customers;";


        // create the connection and prepared statement in a
        // try-with-resources block
        try(Connection connection = dataSource.getConnection();
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

    public List<Product> getAllProducts() throws SQLException{
        ArrayList<Product>products = new ArrayList<>();

        String productsQuery = "SELECT ProductID,ProductName,UnitPrice,UnitsInStock FROM products;";

        // create the connection and prepared statement in a
        // try-with-resources block
        try(Connection connection = dataSource.getConnection();
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

    public ArrayList<Product> getProductsByID(String categoryID) throws SQLException {

        ArrayList<Product> productByID = new ArrayList<>();

         // create the connection and prepared statement in a
        // try-with-resources block
        try(
                Connection connection = dataSource.getConnection();
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



}
