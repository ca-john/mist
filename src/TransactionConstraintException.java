package src;

/**
 * This class is the error that occurs when the transaction cannot be executed because of an invalid transaction code
 */
public class TransactionConstraintException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

	/**
	 * Creates the exception with the given error message
	 * @param msg The message to be displayed.
	 */
	public TransactionConstraintException(String msg) {
		super(msg);
		this.msg=msg;
	}
	
	public String toString() {
		return msg;
	}
	
}
