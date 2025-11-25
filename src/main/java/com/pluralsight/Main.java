package com.pluralsight;

import java.sql.*;



public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String username = "root";
        String password = "yearup";
        String dataBase = "northwind";
        String URL = "jdbc:mysql://localhost:3306/" + dataBase;

        //Load the MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Open a connection to the database
        Connection connection;
        connection = DriverManager.getConnection(
                URL,
                username,
                password);

        //Create a statement for our query
        Statement statement = connection.createStatement();

        //The Connection = The post office. You found the post office and walked inside.
        // The Statement = An envelope! You're asking the post office worker: "Can I have an envelope please?"
        // When you do connection.createStatement(), you're literally just asking for an envelope so you can write your message and send it.
        //Execute a query to select all products.
        String productsQuery = "SELECT * FROM products";

        ResultSet result = statement.executeQuery(productsQuery);

        //process the results
        while (result.next()){
            String productName = result.getString("ProductName");
            String productID = result.getString("ProductID");
            String unitPrice = result.getString("UnitPrice");
            String stock = result.getString("UnitsInStock");
            System.out.println("--------------------------------------------------");
            System.out.printf(" ID: %-5s \n Name: %-40s \n Price: %-5s \n Stock:%-15s \n" ,productID ,productName ,unitPrice ,stock);
        }

        result.close();
        statement.close();
        connection.close();


    }
}