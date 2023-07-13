package src;

import java.util.ArrayList;

/**
 * This class represents a buyer, which is a form of a user that buys games from other sellers.
 */
public class Buyer extends User{

    public double MIN_BALANCE = 0.00;
    public double MAX_PRICE = 999.99;
    public double MAX_DAILY_CREDIT = 1000.00;

    private String username;
    private double balance;
    private ArrayList<Game> games = new ArrayList<Game>();

    public Buyer(String username) {
        super(username);
    }

}
