package src;

/**
 * A class to represent a specific type of transaction, a refund. Each refund has a buyer and seller
 * associated with it, along with the amount of credit refunded to the user.
 */
public class RefundTransaction extends Transaction{

    public String buyer;
    public String seller;
    public double refundCredit;

    public RefundTransaction(String[] vs, String filePath) {
        super(vs, filePath);
    }

	@Override
	public String doTransaction() throws TransactionConstraintException {
		
		User buyer = BackEndService.getInstance().getUserByUsername(source[1]);
		User seller = BackEndService.getInstance().getUserByUsername(source[2]);
		if(buyer == null || seller==null) throw new TransactionConstraintException(String.format("Refund fail.Can not find the user,seller=%,buyer=",source[1],source[2]));
		
		Game g=BackEndService.getInstance().getGame(source[4]);
		if(!buyer.getGames().contains(g)) {
			 throw new TransactionConstraintException(String.format("Refund fail.Buyer%s not the game-%s",buyer.toString(),g.getName()));
		}
		
		double rd=0;
		try {
			rd=Double.parseDouble(source[3]);
			
			if(!seller.getGames().contains(g)) {
				seller.getGames().add(g);
			}
			buyer.games.remove(g);
			seller.addCredit(-rd);
			buyer.addCredit(rd);
//			seller.addCredit(-g.getPrice());
//			buyer.addCredit(g.getPrice());
			
			
			
		} catch (CreditException e) {
			throw new TransactionConstraintException(String.format("Refund fail.%s.", e.toString()));
		}
		return String.format("Refund success buyer%s seller%s and refund:%.2f", buyer.toString(), seller.toString(),rd);

	}



}
