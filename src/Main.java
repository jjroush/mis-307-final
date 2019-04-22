import java.sql.*;
import java.sql.Statement;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean driver = false;
        boolean UserTypeLoop = true;
        System.out.println("Press key for user");
        System.out.println("Driver (D)");
        System.out.println("Rider (R)");
        String userType = input.next();

        while(UserTypeLoop) {
            if (userType.toUpperCase().equals("D")) {
                driver = true;
            } else if (userType.toUpperCase().equals("R")) {
                driver = false;
            } else {
                System.out.println("Invalid Key");
            }
        }

        if (driver) {
            System.out.print("Enter Driver User: ");
            System.out.print("Enter Driver Password: ");
        } else {
            System.out.print("Enter Rider User: ");
            System.out.print("Enter Rider Password: ");
        }

        String code = helpers.getHash(input);
        System.out.println("user hash" + code);
        getRemoteConnection(code);
        input.close();

//        Scanner in = new Scanner(System.in);
//        int Driver_count=0;
//        double Daily_total=0;
//        double Total_miles=0;
//        int riders=0;
//
//        while(true)
//        {
//            System.out.print("Enter pick-up location: ");
//            String pickup = in.nextLine();
//
//            System.out.print("Enter drop-off location: ");
//            String dropoff=in.nextLine();
//
//            System.out.print("Enter number of miles: ");
//            int miles=in.nextInt();
//
//            Total_miles+=miles;
//            double amount=charge(pickup,dropoff,miles);
//
//            System.out.println("Cost: "+amount);
//
//            Daily_total+=amount;
//            Driver_count++;
//            riders++;
//
//            System.out.print("Do you want another ride?(y/n)");
//            char x = in.next().charAt(0);
//            if(x=='n')
//            {
//                break;
//            }
//            in.nextLine();
//            System.out.println();
//
//        }
//        in.close();
////              riderReport(Miles_riden, Number_rides)
////              driverReport(Miles_driven, Number_drives)
//
//    }
//
//
//    private static double charge(String pickup, String dropoff, int miles) {
//        double amount = 0;
//        amount = miles * .50;
//        return amount;
//    }

//private static void riderReport(double miles_riden, number_rides)
//private static void driverReport(double Miles_driven, double number_drives)


    }


    private static Connection getRemoteConnection(String code) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String jdbcUrl = "jdbc:mysql://mis-307-project-db.cde7ebxp3wt0.us-east-1.rds.amazonaws.com:3306";
                System.out.println("Getting remote connection with connection string from environment variables.");
                Connection con = DriverManager.getConnection(jdbcUrl,"master_login","mi$-307-PA$$43");
                System.out.println("Remote connection successful.");
                Statement stat = con.createStatement();
                stat.execute("use `MIS 307 DB`");
                ResultSet rs = stat.executeQuery(Queries.getDriverUserNameFromHash(code));
                while(rs.next()) {
                    System.out.println(rs.getString(2));
                }
                return con;
            }
            catch (ClassNotFoundException e) { System.out.println(e.toString());}
            catch (SQLException e) { System.out.println(e.toString());}

        return null;
    }
}
