import java.util.Date;

public class Queries {

    public static String getDriverUserNameFromHash(boolean isDriver, String str) {
        String query;
        if(isDriver) {
            query = "select * from Driver where Driver.DHash =" + "\'" + str + "\'";
        } else {
            query = "select * from Rider where Rider.RHash =" + "\'" + str + "\'";
        }
        return query;
    }

    public static String createRide(String tDate, double distance, double amount, String startPoint, String endPoint, String riderID, String driverID)
    {
        return ("INSERT INTO Transaction (tDate, distance, amount, startPoint, endPoint, R_username, D_username) VALUES ("+  "\'" + tDate +  "\'" + "," + distance + "," + amount + "," + "\'" + startPoint +  "\'" + "," +  "\'" + endPoint +  "\'" + "," +  "\'" + riderID + "\'" + "," +  "\'" + driverID + "\'" + ");");
    }

    public static String getCountofDriverRides(String dID)
    {
        return "select COUNT(DriverID) from Transaction where DriverID =" + "\'" + dID + "\'";
    }

    public static String getRiderRides(int rID)
    {
        return "select COUNT(RiderID) from Transaction where RiderID =" + "\'" + rID + "\'";
    }



}
