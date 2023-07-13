package src;

/**
 * A class to represent a transaction of a user attempting a log in into their account.
 */
public class LoginTransaction extends Transaction {

	public LoginTransaction(String[] vs,String filePath) {
		super(vs, filePath);
	}

	/**
	 * This method executes the transaction, telling the backend service that the user has now logged in and should be able to
	 * perform further transaction or the method throws an exception when the login cannot occur due to invalid credentials.
	 */
	@Override
	public String doTransaction() throws TransactionConstraintException {
		User u;
		if(this.joinUsers.isEmpty()) {
			u=BackEndService.getInstance().addUser(source[1], UserType.valueOf(source[2]), Double.parseDouble(source[3]));
		}else {
			u=this.joinUsers.get(0);
		}
		if(u==null) {
			return "Login fail.Can't find the User : "+String.join(" ", source);
		}
		BackEndService.getInstance().setLoginUser(u);
		return String.format("%s login sucess,and user Info:%s", u.getUsername(),u.toString());
	}


}
