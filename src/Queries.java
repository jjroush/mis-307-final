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

    public static String createRide(String tDate, double distance, double amount, String startPoint, String endPoint, String riderID, String driverID) {
        return ("INSERT INTO Transaction (tDate, distance, amount, startPoint, endPoint, R_username, D_username) VALUES ("+  "\'" + tDate +  "\'" + "," + distance + "," + amount + "," + "\'" + startPoint +  "\'" + "," +  "\'" + endPoint +  "\'" + "," +  "\'" + riderID + "\'" + "," +  "\'" + driverID + "\'" + ");");
    }

    public static String getCountofDriverRides(String dID) {
        return "select COUNT(D_Username) from Transaction where D_Username =" + "\'" + dID + "\'";
    }

    public static String getCountofRiderRides(String rID) {
        return "select COUNT(R_Username) from Transaction where R_Username =" + "\'" + rID + "\'";
    }

    public static String allRiderRides(String r_Username) {
        return "select * from Transaction where R_Username =" + "\'" + r_Username + "\'";
    }

    public static String allDriverDrives(String D_Username) {
        return "select * from Transaction where D_Username =" + "\'" + D_Username + "\'";
    }


}
