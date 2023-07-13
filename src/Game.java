package src;

import java.text.DecimalFormat;

/**
 * This class represents a game that has a name, a price which it retails at,
 * the effective discount applied to the price, the owner of the game and an
 * indicator to indicate that the game is discounted.
 */
public class Game implements Cloneable {

	private String gname;
	private double orig_price;
	private double price;
	protected double discount=0;
	protected User owner;
	protected boolean discounted;

	/**
	 * Creates a game only with a name and nothing else
	 * 
	 * @param gname The name of the game.
	 */
	public Game(String gname) {
		super();
		this.gname = gname;
		this.discount = 0;
	}

	/**
	 * Create a game with only a name and the price
	 * 
	 * @param gname Name of the game
	 * @param price The price of this game
	 */
	public Game(String gname, double price) {
		super();
		this.gname = gname;
		this.price = price;
	}

	
	public double getDiscountPrice() {
		DecimalFormat f = new DecimalFormat("##.00");
		return Float.valueOf(f.format(Math.round(100*(100-this.discount)/100*this.price)/100));
	}
	/**
	 * This method returns the price of this game
	 * 
	 * @return price of the game
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * This methods sets the price of this game to the desired price
	 * 
	 * @param price the price to replace the current one
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public Game clone() throws CloneNotSupportedException {
		return (Game) super.clone();
	}

	/**
	 * Creates a game with a name and a discount
	 * 
	 * @param name       Name of this game
	 * @param discount   The discount applied
	 * @param discounted
	 */
	public Game(String name, double discount, boolean discounted) {
		super();
		this.gname = name;
		this.discount = discount;
		// Just because the call was similar to the previous constructor
		this.discounted = true;
	}

	/**
	 * Creates a game with a name, a discount and a reference to the user that owns
	 * this game
	 * 
	 * @param name     Name of this game
	 * @param discount The discount applied
	 * @param owner    Oner of the game
	 */
	public Game(String name, double discount, User owner) {
		this.gname = name;
		this.discount = discount;
		this.owner = owner;
	}

	public boolean equals(Object o) {
		if (o instanceof Game) {
			Game ng = (Game) o;
			return gname.equalsIgnoreCase(ng.getName());
		}
		return false;
	}

	public String getName() {
		return gname;
	}

	/**
	 * This method changes the name of this game
	 * 
	 * @param name The name to replace the current one
	 */
	public void setName(String name) {
		this.gname = name;
	}

	/**
	 * This method returns the name of this game
	 * 
	 * @return Name of the game
	 */
	public String toString() {
		return gname;
	}

	/**
	 * This method parses the game name and the price into a string and returns it
	 * 
	 * @return String with the game's name and price
	 */
	public String gameInfo() {
		return String.format("[name=%s,price=%.2f]", gname, price);
	}

	/**
	 * This method returns the discount this game is at
	 * 
	 * @return the discount
	 */
	public double getDiscount() {
		return this.discount;
	}

	/**
	 * This methods returns the owner of this game
	 * 
	 * @return the user that is the owner
	 */
	public User getOwner() {
		return this.owner;
	}

	/**
	 * This method sets the owner of this game to be a new one
	 * 
	 * @param owner the owner that will own this game now
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * This method is similiar to gameInfo() but in a different format
	 * 
	 * @return The game info in a convenient string
	 */
	public String gameDiscInfo() {
		return "Game Name:  " + this.gname + "  Discount:  " + this.discount;
	}


	//Applies discount to game
	public void activateDiscount(){
		this.orig_price = this.price;
		this.price = this.price - (this.price * this.discount);

	}


}
