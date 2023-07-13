package src;

/**
 * This class is used to encapsulate the transaction of adding a credit to a user's account.
 */
public class AddcreditTransaction extends Transaction {

	public AddcreditTransaction(String[] vs, String filePath) {
		super(vs, filePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String doTransaction() throws TransactionConstraintException {
		User u = BackEndService.getInstance().getUserByUsername(source[1]);

		if(u==null) {
			return " Addcredit fail.Can't find the User : "+String.join(" ", source);
		}
		try {
			u.addCredit(Double.parseDouble(source[2]));
		} catch (NumberFormatException | CreditException e) {
			e.printStackTrace();
		}
		return String.format("%s  Addcredit sucess,and user Info:%s", u.getUsername(),u.toString());
	}

}
