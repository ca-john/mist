package src;

/**
 * This class represents a transaction of creating a user, which can only be
 * done by an admin user.
 */
public class CreateTransaction extends Transaction {

	public CreateTransaction(String[] vs, String filePath) {
		super(vs, filePath);
	}

	@Override
	/**
	 * This method executes the transaction, communicates all the necessary
	 * information to the backend service and throws an exception if the user cannot
	 * be created due to the logged in user not being an admin or other invalid info
	 * provided.
	 */
	public String doTransaction() throws TransactionConstraintException {

		User loginUser = BackEndService.getInstance().getLoginUser();
		if (loginUser == null) {
			throw new TransactionConstraintException(String.format("Create user fail. Can not find the user."));

		}
		if (!loginUser.isAdmin()) {
			throw new TransactionConstraintException(
					String.format("Create user fail. Login User%s have not a Admin user.", loginUser.toString()));
		}
		User u = BackEndService.getInstance().getUserByUsername(this.source[1]);

		if (u != null)
			throw new TransactionConstraintException(
					String.format("Create user fail User%s already exists .", u.toString()));
		try {
			double b = Double.parseDouble(source[3]);
			if (b > User.MAX_CREDIT)
				throw new TransactionConstraintException(
						String.format("Create user fail. %.2f over max credit %f", b, User.MAX_CREDIT));
			u = BackEndService.getInstance().addUserInit(source[1], UserType.valueOf(source[2]),
					Double.parseDouble(source[3]), source[4]);
			source[4] = "";

		} catch (Exception e) {
			throw new TransactionConstraintException(
					String.format("Create user fail. "+e.toString()));

		}

		return String.format("Create User Success,and new user info:%s", u.toString());
	}

}
