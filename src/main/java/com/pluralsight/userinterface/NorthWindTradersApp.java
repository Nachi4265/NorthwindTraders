package com.pluralsight.userinterface;

import com.pluralsight.Persistance.DataManager;

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
}
