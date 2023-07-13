package src;

/**
 * A class to represent a selling transaction. Each sell transaction has a game
 * name a seller's username, the discount percentage and the sale price of a
 * game.
 */
public class SellTransaction extends Transaction {

	public SellTransaction(String[] vs, String filePath) {
		super(vs, filePath);
	}

	@Override
	public String doTransaction() throws TransactionConstraintException {
//		if (this.joinUsers.isEmpty()) {
//			throw new TransactionConstraintException("Sell fail.Can't find the user Info.");
//		}

//		User seller = BackEndService.getInstance().getUserByUsername(source[2]);
//		User buyer = BackEndService.getInstance().getLoginUser();
		User buyer = BackEndService.getInstance().getUserByUsername(source[2]);
		User  seller = BackEndService.getInstance().getLoginUser();

//		if (!buyer.canBuy()) {
//			throw new TransactionConstraintException(
//					String.format("Sell fail.Buyer%s No authority.", buyer.toString()));
//		}
		if (seller == null) {
			throw new TransactionConstraintException("Sell fail.Can't find the user Info.");
		}
//		if (!seller.canSell()) {
//			throw new TransactionConstraintException(
//					String.format("Sell fail.Seller%s No authority.", seller.toString()));
//		}
//		if (!seller.getGames().contains(new Game(source[1]))) {
//			throw new TransactionConstraintException(
//					String.format("Sell fail.Seller%s have not  the game[%s] for sell.", seller.toString(), source[1]));
//		}
		Game g = BackEndService.getInstance().getGame(source[1]);
		if (g == null) {
			throw new TransactionConstraintException(
					String.format("Sell fail. the game[%s] is not exists.", source[1]));

		}
		
		if(seller.isAdmin()) {
			g=BackEndService.getInstance().getUserGame(source[2], source[1]);
			
		}else {
			g=BackEndService.getInstance().getUserGame(seller.username, source[1]);
		}
		if (g == null) {
			throw new TransactionConstraintException(
					String.format("Sell fail. the game[%s] is not exists.", source[1]));
		}
		try {
			g.discount = Double.parseDouble(source[3]);
		} catch (Exception e) {
			throw new TransactionConstraintException(
					String.format("sale fail. Discount should 1-100 ,but is %s", source[3]));
		}
//		double d = 0;
//		try {
//			d = Double.parseDouble(source[3]);
//			d /= 100;
//		} catch (Exception e) {
//			throw new TransactionConstraintException(
//					String.format("Sell fail.Discount must a 0-100 number,please check"));
//		}
//
//		if (d <= 0 || d > 100)
//			throw new TransactionConstraintException(
//					String.format("Sell fail.Discount must a 0-100 number,please check"));
//
//		if (buyer.balance < g.getPrice() * d) {
//			throw new TransactionConstraintException(
//					String.format("Sell fail.Buyer%s  have not enough available funds to purchase the game[%s,%.2f].",
//							buyer.toString(), g.getName(), g.getPrice()));
//		}
//		try {
//			seller.addCredit(g.getPrice() * d);
//			buyer.addCredit(-g.getPrice() * d);
//		} catch (CreditException e) {
//			throw new TransactionConstraintException(String.format("Sell fail.%s.", e.toString()));
//		}
//		buyer.getGames().add(g);
//		seller.getGames().remove(g);
		return String.format("Sell success seller%s have already to sell the  game:%s", seller.toString(),
				g.toString());
	}

}
