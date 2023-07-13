package src;

import java.util.ArrayList;

/**
 * A class to represent and admin user, with the ability to perform privileged transactions.
 */
public class AdminUser extends User{

    public double MIN_BALANCE = 0.00;
    public double MAX_PRICE = 999.99;
    public double MAX_DAILY_CREDIT = 1000.00;

    private String username;
    private double balance;
    private ArrayList<Game> games = new ArrayList<Game>();

    public AdminUser(String username) {
        super(username);
    }

}
