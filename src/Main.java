import java.sql.*;
//import java.sql.Statement;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean driver = false;
        boolean UserTypeLoop = true;
        String user;
        String pass;
        String returnedUser = "";
        Connection dbConnection = helpers.getRemoteConnection();


        while (UserTypeLoop) {
            System.out.println("Press key for user");
            System.out.println("Driver (D)");
            System.out.println("Rider (R)");
            System.out.println("Close Program (Q)");
            String userType = input.next();
            if (userType.toUpperCase().equals("D")) {
                driver = true;
                UserTypeLoop = false;
            } else if (userType.toUpperCase().equals("R")) {
                driver = false;
                UserTypeLoop = false;
            } else if (userType.toUpperCase().equals("Q")) {
                System.exit(0);
            } else {
                System.out.println("Invalid Key");
            }
        }
        while (true) {
            if (driver) {
                System.out.print("Enter Driver User: ");
                user = input.next();
                System.out.print("Enter Driver Password: ");
                pass = input.next();
            } else {
                System.out.print("Enter Rider User: ");
                user = input.next();
                System.out.print("Enter Rider Password: ");
                pass = input.next();
            }

            try {
                Statement stmt = dbConnection.createStatement();
                ResultSet rs = stmt.executeQuery(Queries.getDriverUserNameFromHash(driver, helpers.getHash(pass)));

                while (rs.next()) {
                    returnedUser = rs.getString(1);
                }

            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (returnedUser.equals(user)) {
                if (driver) {
                    System.out.println("driver user");
                    DriverView.main(input,dbConnection,user);
                } else {
                    System.out.println("rider user");
                    RiderView.main(input,dbConnection,user);
                }
//                input.close();
            }
            System.out.println("Incorrect Credentials");

        }
    }


}
