package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a transaction of deleting an account from the system.
 */
public class DeleteTransaction extends Transaction {
	
	public DeleteTransaction(String[] vs, String filePath) {
		super(vs, filePath);
	}

	@Override
	/**
	 * This method executed the transaction which communicates all the necessary information to the backend service and throws an exception
	 * when the user to be deleted cannot be deleted by the logged in user due to privilege issues.
	 */
	public String doTransaction() throws TransactionConstraintException {
		
//		if(this.joinUsers.isEmpty()) {
//			throw new TransactionConstraintException(String.format("Delete user fail.The user  does not exist"));
//		}
		User u=BackEndService.getInstance().getUserByUsername(source[1]);
		if(u==null) {
			throw new TransactionConstraintException(String.format("Delete user fail.The user  does not exist"));
		}
		User logu = BackEndService.getInstance().getLoginUser();
		if(logu == null || !logu.isAdmin()) {
			throw new TransactionConstraintException(String.format("Delete user fail.The login user is null or  not authority", source[1],source[2],source[3]));
		}
		if(u.getUsername().equals(logu.getUsername())) {
			throw new TransactionConstraintException(String.format("Delete user fail.Can't not delete current user", source[1],source[2],source[3]));
		}
		BackEndService.getInstance().deleteUser(u);
		return String.format("%s Delete sucess,and delete user Info:%s", u.getUsername(),u.toString());
	}


}
