import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class helpers {

    public static String getHash(Scanner input) {
        try {
            String password = input.next();
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

}
