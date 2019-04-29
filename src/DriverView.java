import java.sql.*;
import java.util.Scanner;

public class DriverView {
    public static void main(Scanner input, Connection dbConnection, String Username){
        String user = Username;
        while(true){
            System.out.println("Select Operation");
            System.out.println("Create Ride (C)");
            System.out.println("Get total rides given (T)");
            System.out.println("Close Program (Q)");
            String Operation = input.next();
            switch(Operation.toUpperCase()){
                case "C": createRide(dbConnection, input, Username);
                        break;
                case "T": getCountofDriverRides(dbConnection, Username);
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
        String start = input.next();
        System.out.println("Enter Ending point:");
        String end = input.next();

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
