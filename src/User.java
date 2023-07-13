package src;

import java.util.ArrayList;

/**
 * A class to represent all types of user (Admin, Buyers, Sellers, etc.). Each user has a username, their password, a user type
 * associated to their account (Admin, etc.), their account balance and a list of games that they own.
 */
public class User {

	public static double MIN_BALANCE = 0.00;
	public static double MAX_PRICE = 999999.99;
	public static double MAX_CREDIT = 999999.99;
	public static double MAX_DAILY_CREDIT = 1000.00;

	protected String username;
	protected double balance;
	protected UserType userType;
	protected String password;

	ArrayList<Game> games = new ArrayList<Game>();

	
	ArrayList<Game> discountGames = new ArrayList<Game>();

	
	/**
	 * Create a user with the given username, user type and the password.
	 * @param username The username of the user
	 * @param userType The user-type of the user
	 * @param password The password of this user
	 */
	public User(String username, UserType userType, String password) {
		this(username, 0, userType, password);
	}

	/**
	 * Creates a user with a username, a user-type, a password, and also a balance that their account will start with
	 * @param username The username of this user
	 * @param balance The balance the account has
	 * @param userType The user-type of this account
	 * @param password The password of this user
	 */
	public User(String username, double balance, UserType userType, String password) {
		super();
		this.username = username;
		this.balance = balance;
		this.userType = userType;
		this.password = password;

	}

	/**
	 * Creates a user only given a username
	 * @param username The username of this user.
	 */
	public User(String username) {
	}

	/**
	 * Creates a user with a username, a balance and a user-type but no password
	 * @param username The username of this user
	 * @param balance The balance to open this account with
	 * @param userType The user-type of this account
	 */
	public User (String username, double balance, UserType userType){
		this.username = username;
		this.balance = balance;
		this.userType = userType;
	}

	/**
	 * This method checks whether this user is allowed to buy any games
	 * @return True if allowed to buy, false otherwise
	 */
	public boolean canBuy() {
		return this.userType == UserType.BS || this.userType == UserType.FS||this.userType == UserType.AA;
	}

	/**
	 * This method checks whether this user is allowed to sell any games
	 * @return True if allowed to sell, false otherwise
	 */
	public boolean canSell() {
		return this.userType == UserType.SS || this.userType == UserType.FS || this.userType == UserType.AA;
	}

	/**
	 * This method checks whether this account is an admin account type.
	 * @return True is account is an admin account, false otherwise
	 */
	public boolean isAdmin() {
		return this.userType == UserType.AA;
	}

	/**
	 * This method provides the account info in a nice format: "Username = xxx, Usertype = xxx, Balance = xxx"
	 * @return The string with the necessary info
	 */
	public String toString() {
		return String.format("[username=%s,usertype=%s and balance=%.2f]", username,userType.toString(),balance);
	}

	/**
	 * This method is similar to toString() but in a a different format
	 * @return The string with the user's information
	 */
	public String userInfo() {
		return String.format("username=%s,usertype=%s and balance=%.2f", username,userType.toString(),balance);
		 
	}

	/**
	 * Returns the username of this suer
	 * @return String of the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * This method returns the list of all games that this user owns
	 * @return List of games
	 */
	public ArrayList<Game> getGames() {
		return this.games;
	}

	/**
	 * This method returns the balance that this account has
	 * @return the balance
	 */
	public double getBalance() {
		return this.balance;
	}

	/**
	 * This method returns the password of this user (not very safe)
	 * @return the string of the password
	 */
	public String getPassword() {return this.password;}

	/**
	 * Adds credit to this user's account
	 * @param credit The amount of credit to be added
	 * @throws CreditException Error for when the balance would exceed the maximum allowed limit
	 */
	public void addCredit(double credit) throws CreditException {
		if (credit <= MAX_DAILY_CREDIT) {
			if (credit + this.balance <= MAX_CREDIT) {
				this.balance += credit;
				System.out.println(credit + " has been successfully added to " + this.getUsername() + "'s account");
				// Record to log
			} else {
				throw new CreditException(
						"ERROR: CREDIT_EXCEPTION - Maximum credit exceeded AT " + this.getUsername() + " addCredit");
				// Record error to log
			}
		} else {
			throw new CreditException("ERROR: CREDIT_EXCEPTION - Maximum daily credit limit exceeded");
			// Record error to log
		}
	}

	/**
	 * Removes the amount amt from the user's account
	 * @param amt The amount to deduct
	 * @throws CreditException Error for when the balance would be negative if the amount was deducted.
	 */
	public void removeAmount(double amt) throws CreditException {
		if (this.balance - amt >= 0.00) {
			this.balance -= amt;
			System.out.println(amt + " has been successfully removed from " + this.getUsername() + "'s account");
			// Record to log
		} else {
			throw new CreditException("ERROR: Negative account balance AT " + this.getUsername() + " removeAccount");
			// Record error to log
		}
	}

	/**
	 * Removes the game from the user's inventory.
	 * @param game The game to be removed.
	 * @throws GameDeleteException Error for when the user does not own the game to be removed.
	 */
	public void removeGame(Game game) throws GameDeleteException {
		if (this.games.contains(game)){
			this.games.remove(game);
			System.out.println(game.getName() + " has been removed from " + this.getUsername() + "'s library");
		}
		else
			throw new GameDeleteException("ERROR: Game not found");
	}

	public static void main(String[]args) {
		String s="XX UUUUUUUUUUUUUUU TT CCCCCCCCC";
		String d=s.replaceAll(" ", s);
		s="name_sd	name_area	name_provice	name_city	CreatePassageNodeCode	CreatePassageNodeName	SystemCode	Name	Mobile	ThisPoints	Points	Name	ActionType	OrderNo	OrderType	ApiUserName	SourceOrderNo	SourcePointsType	SourceComment	CreateOn";
		for(String v:s.split("\t")) {
			System.out.println(" `"+v+"` varchar(255) DEFAULT NULL COMMENT '',");
		}
	}
}
