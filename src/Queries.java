public class Queries {

    public static String getDriverUserNameFromHash(String str) {
        return "select * from Driver where Driver.DHash =" + "\'" + str + "\'";
    }

    //TODO rider username from hash

    //TODO create ride(as driver)


    //TODO get all rides given (as driver)

    //TODO get all rides recieved (as rider)

    //TODO get total rides(as rider)



}
