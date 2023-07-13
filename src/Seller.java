package src;

import java.util.ArrayList;

/**
 * This class represents a seller that sells a game to a buyer, it is a subclass of user because a seller is a type of user.
 */
public class Seller extends User{

    public double MIN_BALANCE = 0.00;
    public double MAX_PRICE = 999.99;
    public double MAX_DAILY_CREDIT = 1000.00;

    String username;
    double balance;
    ArrayList<Game> games = new ArrayList<Game>();

    /**
     * Create a seller with a given username.
     * @param username the username of the seller.
     */
    public Seller(String username) {
        super(username);
    }
}
