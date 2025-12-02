package com.pluralsight.userinterface;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class InputCollector {

    //This is what will collect all our input
    private static final Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt){
        System.out.print(prompt + ": ");
        String input = scanner.nextLine().trim();
        System.out.println();
        return input;
    }

    public static char promptForChar(String prompt){
        System.out.print(prompt + ": ");
        char result = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine();
        return result;
    }

    public static float promptForFloat(String prompt){

        boolean isFloat = false;
        float result = 0;

        do{
            try{
                System.out.print(prompt + ": ");
                result = scanner.nextFloat();
                scanner.nextLine();
                isFloat = true;

            }catch(Exception e){
                scanner.nextLine();
                System.out.println("please enter a number with decimal");
            }
        }while (!isFloat);

        return result;

    }

    public static int promptForInt(String prompt) {

        boolean Num = false;
        int result = 0;
        do{
            try{
                System.out.print(prompt + ": ");
                result = scanner.nextInt();
                scanner.nextLine();
                Num = true;
            }
            catch(Exception ex){
                scanner.nextLine();
                System.out.println("Invalid Entry, please enter a whole number");
            }
        } while (!Num);

        return result;
    }

    public static long promptForLong(String prompt){
        System.out.print(prompt + ": ");
        long result = scanner.nextLong();
        scanner.nextLine();
        return result;
    }

    public static LocalDate promptForDate(String prompt){
        while(true){
            try{
                System.out.print(prompt + ": ");
                String stringDate = scanner.nextLine();
                return LocalDate.parse(stringDate);
            }
            catch(Exception e){
                System.out.println("Invalid entry! Please enter date (YYYY-MM-DD)");
            }

        }

    }

    public static double promptForDouble(String prompt){

        boolean isDouble = false;
        double result = 0;

        do{
            try{
                System.out.print(prompt + ": ");
                result = scanner.nextDouble();
                scanner.nextLine();
                isDouble = true;

            }catch(Exception e){
                scanner.nextLine();
                System.out.print("please enter a number with decimal ");
            }
        }while (!isDouble);

        return result;

    }

    public static void displayList(List<?> values){
        int totalNumber = values.size();
        int numberPerRow = 5;
        int indexOfNext = 0;

        while(indexOfNext < totalNumber ){

            for(int i = 1 ; i <= numberPerRow ; i++){
                if(indexOfNext >= totalNumber){
                    System.out.println();
                    return;
                }
                System.out.printf("%15s", values.get(indexOfNext++).toString());
            }
            System.out.println();
        }

    }
}
