import java.sql.*;
import java.util.Scanner;

public class DriverView {
    public static void main(Scanner input, Connection dbConnection, String Username){
        while(true){
            System.out.println("Select Operation");
            System.out.println("Create Ride (C)");
            System.out.println("Get total rides given (T)");
            System.out.println("Show all Drives Given (A)");
            System.out.println("Close Program (Q)");
            String Operation = input.next();
            switch(Operation.toUpperCase()){
                case "C": createRide(dbConnection, input, Username);
                        break;
                case "T": getCountofDriverRides(dbConnection, Username);
                        break;
                case "A": showAllDriverDrives(dbConnection, Username);
                    break;
                case "Q":  System.exit(0);;
                        break;
                default: System.out.println("Unknown Operation");
                        break;
            }
        }


    }

    private static void createRide(Connection dbConnection, Scanner input, String loggedInUser) {
        System.out.println("Enter Rider User:");
        String Rider = input.next();
        System.out.println("Enter distance traveled:");
        Double distance = input.nextDouble();
        Double amount = helpers.getRate(distance);
        System.out.println("Enter Date (YYYY-MM-DD):");
        String date = input.next();
        System.out.println("Enter Starting point:");
        String start = input.nextLine();
        System.out.println("Enter Ending point:");
        String end = input.nextLine();

        try {
            Statement stmt = dbConnection.createStatement();
            stmt.executeUpdate(Queries.createRide(date, distance, amount, start, end, Rider, loggedInUser));
            System.out.println("Success!");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public static void showAllDriverDrives(Connection dbConnection, String Username) {
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.allDriverDrives(Username));
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i <= 8; i++) {
                System.out.printf("%16s",rsmd.getColumnName(i));
            }
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= 8; i++) {
                    String columnValue = rs.getString(i);
                    System.out.printf("%16s", columnValue);
                }
                System.out.println("");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getCountofDriverRides(Connection dbConnection, String Username) {
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.getCountofDriverRides(Username));
            String count = "";
            while (rs.next()) {
                count = rs.getString(1);
            }
            System.out.println(count);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
