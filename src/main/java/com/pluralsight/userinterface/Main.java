package com.pluralsight.userinterface;
import com.pluralsight.Persistance.DataManager;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {

         if(!ensureArgs(args)){return;}

         try{
             //uses IntellJ CLI arguments
             BasicDataSource dataSource = getDataSource(args);

             //
             DataManager dataManager = new DataManager(dataSource);

             NorthWindTradersApp app = new NorthWindTradersApp(dataManager);

             app.start();

         }catch(Exception e){
             System.out.println("There was a SQL exception: "+ e.getMessage());
         }
//        UserInterface ui = new UserInterface();
//        ui.homeScreen();
    }


    //checks if we have at least 3 arguments
    private static boolean ensureArgs(String[] args) {
        if(args.length < 3){
            System.out.println("You need to provide a username, password, and URL when running this command.");
            System.out.println("For example:");
            System.out.println("Main.exe myUsername myPassword myURL");
            return false;
        }
        return true;
    }


    //OVERLOADED METHODS
    //Gets a data source from a username , password and URL
    private static BasicDataSource getDataSource(String userName , String password, String URL){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);
        basicDataSource.setUrl(URL);
        return basicDataSource;
    }

    //Uses Array of strings for a username , password and URL that are passed to our other method to make a connection
    private static BasicDataSource getDataSource(String[]args){
        String username = args[0];
        String password = args[1];
        String URL = args[2];
        return getDataSource(username,password,URL);
    }
}