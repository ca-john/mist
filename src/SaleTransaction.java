package src;

public class SaleTransaction extends Transaction {

    public SaleTransaction(String[] source, String filePath) {
        super(source, filePath);
    }

    @Override
    public String doTransaction() throws TransactionConstraintException {
        User seller = BackEndService.getInstance().getUserByUsername(source[2]);

//		if (!buyer.canBuy()) {
//			throw new TransactionConstraintException(String.format("sale fail.Buyer%s No authority.", buyer.toString()));
//		}
        if ("".equals(source[2])) {
            Game g = BackEndService.getInstance().getGame(source[1]);
            if (g == null) {
                throw new TransactionConstraintException("sale fail.Can't find the game Info.");
            }
            for (User u : BackEndService.getInstance().getUsers().values()) {
                g = BackEndService.getInstance().getUserGame(u.getUsername(), source[1]);
                if (g != null)
                    try {
                        g.discount = Double.parseDouble(source[3]);
                    } catch (Exception e) {
                        throw new TransactionConstraintException(
                                String.format("sale fail. Discount should 1-100 ,but is %s", source[3]));
                    }
            }
            return String.format("sale success  game:%s",source[1]);
        }
        if (seller == null) {
            throw new TransactionConstraintException("sale fail.Can't find the user Info.");
        }
        if (!seller.canSell()) {
            throw new TransactionConstraintException(
                    String.format("sale fail.saleer%s No authority.", seller.toString()));
        }
//		if (seller.getGames().contains(new Game(source[1]))) {
//			throw new TransactionConstraintException(
//					String.format("sale fail.saleer%s have already the game[%s] for sale.", seller.toString(), source[1]));
//		}
//		Game g = BackEndService.getInstance().getGame(source[1]);
        Game g = BackEndService.getInstance().getUserGame(seller.getUsername(), source[1]);
        if (g == null) {
            throw new TransactionConstraintException(
                    String.format("sale fail. the game[%s] is not exists or the seller have not the game.", source[1]));

        }
//		if (buyer.balance < g.getPrice()) {
//			throw new TransactionConstraintException(
//					String.format("Buy fail.Buyer%s  have not enough available funds to purchase the game[%s,%.2f].",
//							buyer.toString(), g.getName(), g.getPrice()));
//		}
//		try {
//			saleer.addCredit(g.getPrice());
//			buyer.addCredit(-g.getPrice());
//		} catch (CreditException e) {
//			throw new TransactionConstraintException(String.format("Buy fail.%s.", e.toString()));
//		}
//		buyer.getGames().add(g);
//		if(!seller.getGames().contains(g))
//			seller.getGames().add(g);
        try {
            g.discount = Double.parseDouble(source[3]);
        } catch (Exception e) {
            throw new TransactionConstraintException(
                    String.format("sale fail. Discount should 1-100 ,but is %s", source[3]));
        }
        return String.format("sale success saleer%s have already to sale the  game:%s", seller.toString(),
                g.toString());
    }

}