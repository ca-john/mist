package src;

public class TransactionErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private String filePath;

	public TransactionErrorException(String msg, String filePath) {
		super(msg);
		this.msg = msg;
		this.filePath = filePath;
	}

}
