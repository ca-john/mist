package src;

import java.util.ArrayList;

/**
 * A class to represent a buy transaction. Each buy has a seller, a buyer and a game associated with it.
 */
public class BuyTransaction extends Transaction {

	public String buyer;
	public String seller;
	public String game;

	public BuyTransaction(String[] vs, String filePath) {
		super(vs, filePath);
	}

	@Override
	/**
	 * This method executes the transaction, updates all the necessary information regarding the backend service and throws an
	 * exception whenever the transaction cannot be executed due to invalid conditions regarding the transaction.
	 */
	public String doTransaction() throws TransactionConstraintException {
		User buyer = BackEndService.getInstance().getUserByUsername(this.source[3]);
		User seller = BackEndService.getInstance().getUserByUsername(this.source[2]);
		
		if(buyer==null || seller==null) {
			throw new TransactionConstraintException(String.format("Buy fail.Can not find the buyer or seller."));
		}
		
		if (!buyer.canBuy()) {
			throw new TransactionConstraintException(String.format("Buy fail.Buyer%s No authority.", buyer.toString()));
		}
		if (!seller.canSell()) {
			throw new TransactionConstraintException(String.format("Buy fail.Seller%s No authority.", buyer.toString()));
		}

		Game g = BackEndService.getInstance().getUserGame(seller.getUsername(), source[1]);
		if(g==null)
			throw new TransactionConstraintException(String.format("Buy fail.Can not find the game:%s.", source[1]));
		if(!seller.getGames().contains(g)) {
			throw new TransactionConstraintException(String.format("Buy fail.Seller%s have not the game:%s.", seller.toString(),source[1]));
		}
		if(buyer.getGames().contains(g)) {
			throw new TransactionConstraintException(String.format("Buy fail.Buyer%s have already the game:%s.", buyer.toString(),source[1]));
		}
		
		if (buyer.balance < g.getDiscountPrice()) {
			throw new TransactionConstraintException(
					String.format("Buy fail.Buyer%s  have not enough available funds to purchase the game[%s,%.2f].",
							buyer.toString(), g.getName(), g.getDiscountPrice()));
		}
		try {
			seller.addCredit(g.getDiscountPrice());
			buyer.addCredit(-g.getDiscountPrice());
		} catch (CreditException e) {
			throw new TransactionConstraintException(String.format("Sell fail.%s.", e.toString()));
		}
		seller.getGames().remove(g);
		buyer.getGames().add(g);
		
		//		ArrayList<Game> games = seller.getGames();
//		Game[] games2 = new Game[games.size()];
//		games.toArray(games2);
//		for (int i = 0; i < games2.length; i++) {
//			if (games2[i].getName().equals(source[3])) {
//				Game g = games2[i];
//				if (buyer.balance < g.getPrice()) {
//					throw new TransactionConstraintException(String.format("Buy fail.Buyer%s  have not enough available funds to purchase the game[%s,%.2f].", buyer.toString(), g.getName(), g.getPrice()));
//				}
//				try {
//					seller.addCredit(g.getPrice());
//					buyer.addCredit(-g.getPrice());
//				} catch (CreditException e) {
//					throw new TransactionConstraintException(String.format("Buy fail.%s.", e.toString()));
//				}
//				buyer.getGames().add(g);
//				seller.games.remove(g);
//				return String.format("Buy success buyer%s seller%s and game:%s", buyer.toString(), seller.toString(), g.toString());
//			}
//		}
		return String.format("Buy success buyer%s seller%s and game:%s", buyer.toString(), seller.toString(), g.toString());
	}
}
