package src;

/**
 * A class to describe a transaction of a user gifting a game to another user
 */
public class GiftTransaction extends Transaction{

	public GiftTransaction(String[] source, String filePath) {
		super(source, filePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * This method executed the transaction, which involves communicating with the backend service and updating all the info and
	 * throwing an exception when the game cannot be gifted because of various limitations.
	 */
	public String doTransaction() throws TransactionConstraintException {
		
//		if(this.joinUsers.isEmpty() || this.joinUsers.size()<2) {
////			u=BackEndService.getInstance().addUser(source[1], UserType.valueOf(source[2]), Double.parseDouble(source[3]));
//			return " gift fail.Can't find the User : "+String.join(" ", source);
//		}
		Game g = BackEndService.getInstance().getGame(source[1]);
		if(g==null) {
			return String.format(" gift fail.Cant't found the game:%s.",source[1]);
		}
		User owner = BackEndService.getInstance().getLoginUser();
		User recevier = BackEndService.getInstance().getUserByUsername(source[2]);
		if(!owner.getGames().contains(g)) {
			return String.format(" gift fail.The owner%s have not the game:%s.",owner.toString(),source[1]);
		}
		if(recevier.getGames().contains(g)) {
			return String.format(" gift fail.The recevier%s have already had the game:%s.",recevier.toString(),source[1]);
		}
		recevier.games.add(g);
		owner.games.remove(g);
		return  String.format(" gift success.The owner give the game[%s] to recevier%s for the gitf",owner.toString(),source[1],recevier.toString());
	}


}
