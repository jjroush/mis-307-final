import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class helpers {

    public static String getHash(String password) {
        try {
            MessageDigest secure = MessageDigest.getInstance("MD5");
            byte[] messageDigest = secure.digest(password.getBytes());
            BigInteger test = new BigInteger(1, messageDigest);
            String code = test.toString(16);
            while (code.length() < 32) {
                code = "0" + code;
            }
            return code;
        } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

    public static Connection getRemoteConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://mis-307-project-db.cde7ebxp3wt0.us-east-1.rds.amazonaws.com:3306";
            System.out.println("Getting remote connection with connection string from environment variables.");
            Connection con = DriverManager.getConnection(jdbcUrl,"master_login","mi$-307-PA$$43");
            System.out.println("Remote connection successful.");
            Statement stat = con.createStatement();
            stat.execute("use `MIS 307 DB`");
//                ResultSet rs = stat.executeQuery(Queries.getDriverUserNameFromHash(code));
//                while(rs.next()) {
//                    System.out.println(rs.getString(2));
//                }
            return con;
        }
        catch (ClassNotFoundException e) { System.out.println(e.toString());}
        catch (SQLException e) { System.out.println(e.toString());}

        return null;
    }

    public static double getRate(double distance) {
        return distance*.90+5;
    }

}
