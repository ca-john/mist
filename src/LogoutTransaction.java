package src;

/**
 * This class represents a transaction of a user logging out of their account.
 */
public class LogoutTransaction extends Transaction {

	public LogoutTransaction(String[] vs, String filePath) {
		super(vs, filePath);
	}

	/**
	 * This method executes the transaction by logging the user out of the account
	 * @return Logged out message
	 * @throws TransactionConstraintException The exception to throw when the user cannot be logged out
	 */
	@Override
	public String doTransaction() throws TransactionConstraintException {
		return "LOGGED OUT";
	}


}
