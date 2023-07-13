package src;

import java.util.ArrayList;

/**
 * This class represents a standard user with a username, an account balance and the list of games that the user owns.
 */
public class FullStandardUser extends User{

    public double MIN_BALANCE = 0.00;
    public double MAX_PRICE = 999.99;
    public double MAX_DAILY_CREDIT = 1000.00;

    String username;
    double balance;
    ArrayList<Game> games = new ArrayList<Game>();

    public FullStandardUser(String username) {
        super(username);
    }


}
