package com.pluralsight.Persistance;

import com.pluralsight.model.Category;
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

    private List<Category> getAllCategories() throws ClassNotFoundException, SQLException {
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


}
