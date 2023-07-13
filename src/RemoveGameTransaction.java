package src;

/**
 * This class represents a transaction of deleting a game from the system.
 */
public class RemoveGameTransaction extends Transaction {

	public RemoveGameTransaction(String[] source, String filePath) {
		super(source, filePath);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method executes the transaction by calling to the backend service and updating the list of game and the users' game list
	 * or the method handles the errors when the game cannot be deleted, the information about the transaction is displayed.
	 * @return The message to display
	 * @throws TransactionConstraintException Error when the game cannot be deleted because it cannot be found.
	 */
	@Override
	public String doTransaction() throws TransactionConstraintException {

		Game g = BackEndService.getInstance().getGame(source[2]);
		if(g==null) {
			System.out.println("reached2");
			return String.format(" RemoveGame fail.Cant't found the game:%s.",source[2]);
		}
		User owner = BackEndService.getInstance().getUserByUsername(source[1]);
		if(!owner.getGames().contains(g)) {

			return String.format(" RemoveGame fail.The owner%s have not the game:%s.",owner.toString(),source[1]);
		}
		for(Game og:owner.getGames()) {
			System.out.println("reached");
			if(og.getName().equalsIgnoreCase(source[2])) {

				owner.games.remove(og);
				break;
			}
		BackEndService.getInstance().save();



		}
		return  String.format(" RemoveGame success.The owner%s remove the game[%s]",owner.toString(),source[2]);

	}



}
