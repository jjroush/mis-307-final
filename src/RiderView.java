import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RiderView {
    public static void main(Scanner input, Connection dbConnection, String Username){
        while(true){
            System.out.println("Select Operation");
            System.out.println("Get total rides given (T)");
            System.out.println("Show All Rider Rides (A)");
            System.out.println("Close Program (Q)");
            String Operation = input.next();
            switch(Operation.toUpperCase()){
                case "T": getCountofRiderRides(dbConnection, Username);
                    break;
                case "A": showAllRiderRides(dbConnection, Username);
                    break;
                case "Q":  System.exit(0);;
                    break;
                default: System.out.println("Unknown Operation");
                    break;
            }
        }
    }

    public static void getCountofRiderRides(Connection dbConnection, String Username) {
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.getCountofRiderRides(Username));
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

    public static void showAllRiderRides(Connection dbConnection, String Username) {
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(Queries.allRiderRides(Username));
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i <= 8; i++) {
                System.out.printf("%15s",rsmd.getColumnName(i));
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
}
