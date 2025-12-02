package com.pluralsight.userinterface;

import com.pluralsight.Persistance.DataManager;
import com.pluralsight.model.Category;
import com.pluralsight.model.Customer;
import com.pluralsight.model.Product;

import java.sql.SQLException;
import java.util.List;

public class NorthWindTradersApp {
    private DataManager dataManager;

    public  NorthWindTradersApp(DataManager dataManager){
       this.dataManager = dataManager;
    }

    public void start(){
        while (true) {
            String homeScreen = """
                    ===================| NORTHWIND TRADERS |======================
                    What do you want to do?\n 
                    1) Display all products\n
                    2) Display all customers\n
                    3) Display all categories\n
                    4) Display Products By ID \n
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
                case 4:
                    displayAllProductsByID();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }

    private void displayAllCategories() {
        try{

            List<Category> categories = dataManager.getAllCategories();
            InputCollector.displayList(categories);

        }catch(SQLException e){
            System.out.println("Error: " +  e.getMessage());
        }

    }

    private void displayAllCustomers() {

        try{

            List<Customer> customers = dataManager.getAllCustomers();
            InputCollector.displayList(customers);

        }catch(SQLException e){
            System.out.println("Error: " +  e.getMessage());
        }

    }

    private void displayAllProducts() {

        try{

            List<Product> products = dataManager.getAllProducts();
            InputCollector.displayList(products);

        }catch(SQLException e){
            System.out.println("Error: " +  e.getMessage());
        }
    }

    private void displayAllProductsByID() {

        String categoryID = InputCollector.promptForString("What is the Product category ID you want to search");

        try{

            List<Product> products = dataManager.getProductsByID(categoryID);
            InputCollector.displayList(products);

        }catch(SQLException e){
            System.out.println("Error: " +  e.getMessage());
        }
    }
}
