package src;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is an abstract class that captures the essence of all transactions.
 */
public abstract class Transaction {

	// XX UUUUUUUUUUUUUUU TT CCCCCCCCC
	private static List<String> TransationCode = Arrays.asList("00", "01", "02", "03", "04", "05", "06", "08", "09",
			"10", "11");

	public static boolean isCorrectTransationCode(String code) {
		return TransationCode.contains(code);
	}

	public String[] split(String line) {

		return null;
	}

	/**
	 * Given a transaction code, this method creates and returns the applicable type of transaction.
	 * @param line Transaction code
	 * @param filePath
	 * @return The specific type of transaction
	 * @throws TransactionErrorException Error for when the provided transaction code is invalid
	 */
	public static Transaction getTransaction(String line, String filePath) throws TransactionErrorException {
		String[] vs = line.split(" ");
		if (isCorrectTransationCode(vs[0])) {
			switch (Integer.parseInt(vs[0])) {
			case 0:
				return new LoginTransaction(vs, filePath);
			case 1:
				return new CreateTransaction(vs, filePath);
			case 2:
				return new DeleteTransaction(vs, filePath);
			case 3:
				return new SellTransaction(vs, filePath);
			case 4:
				return new BuyTransaction(vs, filePath);
			case 5:
				return new RefundTransaction(vs, filePath);
			case 6:
				return new AddcreditTransaction(vs, filePath);
			case 8:
				return new RemoveGameTransaction(vs, filePath);
			case 9:
				return new GiftTransaction(vs, filePath);
			case 11:
				return new RemoveGameTransaction(vs, filePath);
			default:
				return new LogoutTransaction(vs, filePath);
			}
		} else {
			throw new TransactionErrorException("error code", filePath);
		}
	}

	protected String code;
	protected String[] source;
	protected List<User> joinUsers = new ArrayList<>();

	/**
	 * Creates a transaction given a transaction code
	 * @param source Transaction code
	 * @param filePath
	 */
	public Transaction(String[] source, String filePath) {
		this.source = source;
		this.code = source[0];
		if (!isCorrectTransationCode(code)) {
			return;
		}

	}

	/**
	 * Execution of the transaction, handled in the subclasses.
	 * @return The message to be displayed
	 * @throws TransactionConstraintException Error when the transaction cannot occur because of invalid info.
	 */
	public abstract String doTransaction() throws TransactionConstraintException;

	/**
	 * THis method returns the transaction code of this transaction
	 * @return String representation of the transaction code
	 */
	public String toString(){
		return String.join(" ", this.source);
	}
}
