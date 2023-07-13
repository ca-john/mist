package src;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class handles the GUI of the transaction page that is displayed upon a
 * successful login and executed by the user.
 */
public class TransactionPage extends JFrame implements ActionListener {

	protected BackEndService backEnd;
	protected User user;
	protected DailyTransactionLog log;


	protected JFrame f;
	protected JPanel p;

	protected JButton back;

	protected JLabel rec;
	protected JTextField recb;

	protected JLabel send;
	protected JTextField sendb;

	protected JLabel gamest;
	protected JTextField gamestb;
	protected String typenum;

	protected JButton proceedb;

	protected Transaction trans;
	protected String type;

	String receiver;
	String sender;
	Game game;
	String game_name;
	JTextField[] areas;

	private void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Tips", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Create the window with the necessary GUI elements
	 * 
	 * @param backEnd The backend service associated with this session
	 * @param user    The user operating on this GUI
	 * @param type    The type of this user
	 * @param log     The transaction log
	 */
	public TransactionPage(BackEndService backEnd, User user, String type, DailyTransactionLog log) {

		this.backEnd = backEnd;
		this.type = type;
		this.user = user;
		this.log = log;
		f = new JFrame();
		p = new JPanel();
		back = new JButton("RETURN TO HOME");
		proceedb = new JButton("COMPLETE");
		back.addActionListener(this);

		// buy, sell, refund, gift, create, delete, addcredit, logout, delete_game

		// 00 login, 01 create, 02 delete, 06 addcredit, 05 refund, 03 sell, 04 buy, 07
		// Gift

		if (type.equals("refund")) {
			// buyer username, seller username, amt
			JLabel refbuy = new JLabel("ENTER THE BUYERS USERNAME");
			JTextField refbuyn = new JTextField(16);
			JLabel refsell = new JLabel("ENTER THE SELLERS USERNAME");
			JTextField refselln = new JTextField(16);
			JLabel refamt = new JLabel("ENTER REFUND ATM");
			JTextField refamtamt = new JTextField(16);
			JLabel gameLabel = new JLabel("ENTER REFUND GAME");
			JTextField gameName = new JTextField(16);
			p.add(refbuy);
			p.add(refbuyn);
			p.add(refsell);
			p.add(refselln);
			p.add(refamt);
			p.add(refamtamt);
			p.add(gameLabel);
			p.add(gameName);
			typenum = "05";
			areas = new JTextField[] { refbuyn, refselln, refamtamt,gameName };

		}
		if (type.equals("buy")) {
			// game name, seller, buyer
			JLabel refbuy = new JLabel("ENTER THE GAME'S NAME");
			JTextField refbuyn = new JTextField(16);
			JLabel refsell = new JLabel("ENTER THE SELLERS USERNAME");
			JTextField refselln = new JTextField(16);
			JLabel refamt = new JLabel("ENTER THE BUYER'S NAME");
			JTextField refamtamt = new JTextField(16);
			p.add(refbuy);
			p.add(refbuyn);
			p.add(refsell);
			p.add(refselln);
			p.add(refamt);
			p.add(refamtamt);
			typenum = "04";
			areas = new JTextField[] { refbuyn, refselln, refamtamt };

		}

		if (type.equals("remove")){
			if (user.userType.getValue().equals("admin")) {
				JLabel refbuy = new JLabel("ENTER THE OWNER OF THE GAME YOU WISH TO REMOVE");
				JTextField refbuyn = new JTextField(16);
				JLabel refbuy2 = new JLabel("ENTER THE GAME YOU WISH TO REMOVE");
				JTextField refbuyn2 = new JTextField(16);
				p.add(refbuy);
				p.add(refbuyn);
				p.add(refbuy2);
				p.add(refbuyn2);
				areas = new JTextField[] {refbuyn, refbuyn2};
			}
			else{
				JTextField refbuyn2 = new JTextField(16);
				refbuyn2.setText(user.username);
				JLabel refbuy2 = new JLabel("ENTER THE GAME YOU WISH TO REMOVE");
				JTextField refbuyn23 = new JTextField(16);
				p.add(refbuy2);
				p.add(refbuyn23);
				areas = new JTextField[] {refbuyn2, refbuyn23};
				



			}

			typenum = "11";

		}

		if (type.equals("sell")) {
			// game name, seller user, discount percentage, price
			JLabel refbuy = new JLabel("ENTER THE GAME'S NAME");
			JTextField refbuyn = new JTextField(16);
			JLabel refsell = new JLabel("ENTER THE SELLERS USERNAME");
			JTextField refselln = new JTextField(16);
			JLabel refamt = new JLabel("ENTER THE DISCOUNT");
			JTextField refamtamt = new JTextField(16);
			p.add(refbuy);
			p.add(refbuyn);
			p.add(refsell);
			p.add(refselln);
			p.add(refamt);
			p.add(refamtamt);
			typenum = "03";
			areas = new JTextField[] { refbuyn, refselln, refamtamt };
		}

		if (type.equals("gift")) {
			JLabel refbuy = new JLabel("ENTER THE GAME'S NAME");
			JTextField refbuyn = new JTextField(16);
			JLabel refamt = new JLabel("ENTER THE GIFTEES NAME");
			JTextField refamtamt = new JTextField(16);
			p.add(refbuy);
			p.add(refbuyn);
			p.add(refamt);
			p.add(refamtamt);
			typenum = "07";
			areas = new JTextField[] { refbuyn, refamtamt };
		}

		if (type.equals("sale")){
			JLabel refbuy = new JLabel("ENTER THE SALE DISCOUNT (1-100 [percentage])");
			JTextField refbuyn = new JTextField(16);
			JLabel rem = new JLabel("ENTER GAME TO PUT ON SALE");
			JTextField remr = new JTextField(16);
			JLabel rere = new JLabel("ENTER USER TO PUT ON SALE");
			JTextField rerere = new JTextField(16);
			p.add(refbuy);
			p.add(refbuyn);
			p.add(rem);
			p.add(remr);
			p.add(rere);
			p.add(rerere);
			areas = new JTextField[] { remr,rerere,refbuyn };
			typenum = "12";
		}

		if (type.equals("credit")) {
			if (user.userType.getValue().equals("admin")) {
				JLabel refbuy = new JLabel("ENTER THE RECEIVER'S NAME");
				JTextField refbuyn = new JTextField(16);
				JLabel refamt = new JLabel("ENTER THE AMOUNT");
				JTextField refamtamt = new JTextField(16);
				p.add(refbuy);
				p.add(refbuyn);
				p.add(refamt);
				p.add(refamtamt);
				typenum = "06";
				areas = new JTextField[] { refbuyn, refamtamt };
			} else {
				JLabel refamt = new JLabel("ENTER THE AMOUNT YOU WISH TO ADD");
				JTextField refamtamt = new JTextField(16);
				p.add(refamt);
				p.add(refamtamt);
				typenum = "06";
				areas = new JTextField[] { refamtamt };
			}
		}
		if (type.equals("create")) {
			JLabel refamt = new JLabel("ENTER THE USER TO CREATE");
			JTextField refamtamt2 = new JTextField(16);
			JLabel refam = new JLabel("ENTER THE USER TYPE (AA, FS, BS, SS)");
			JTextField refamta = new JTextField(16);
			JLabel passl = new JLabel("ENTER THE PASSWORD FOR THIS USER");
			JTextField passf = new JTextField(16);
			JLabel reamt = new JLabel("ENTER THE ACCOUNT AMOUNT (MAX: $999.99");
			JTextField reamtamt = new JTextField(16);
			p.add(refamt);
			p.add(refamtamt2);
			p.add(refam);
			p.add(refamta);
			p.add(passl);
			p.add(passf);
			p.add(reamt);
			p.add(reamtamt);
			typenum = "01";
			areas = new JTextField[] { refamtamt2, refamta, reamtamt, passf};

		}
		if (type.equals("delete")) {
			JLabel refamt = new JLabel("ENTER THE USER TO DELETE");
			JTextField refamtamt = new JTextField(16);
			p.add(refamt);
			p.add(refamtamt);
			typenum = "02";
			areas = new JTextField[] { refamtamt };
		}

		p.add(proceedb);
		proceedb.addActionListener(this);
		p.add(Box.createHorizontalGlue());
		p.add(Box.createHorizontalGlue());
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setAlignmentY(java.awt.Component.LEFT_ALIGNMENT);
		p.add(back);
		f.add(p);
		f.setSize(300, 400);
		f.show();

	}

	/**
	 * This method returns the type that this user is (Admin, Buyer, etc.)
	 * 
	 * @return String of this user's type
	 */
	public String getTyp() {
		return this.type;
	}

	/**
	 * This method handles the interaction with the GUI elements, responds to clicks
	 * and inputs, and alerts the backend service to update the necessary
	 * information based on the user input.
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.back) {
			p.setVisible(false);
			AccountPage ac = new AccountPage(backEnd, user, log);
			f.setVisible(false);
			return;
		}

		ArrayList<String> temp = new ArrayList<String>();
		temp.add(this.typenum);
		for (int i = 0; i < this.areas.length; i++) {
			temp.add(areas[i].getText());

		}
		String[] code = new String[temp.size()];
		temp.toArray(code);

		if (this.getTyp().equals("remove")){
		try{
			Transaction trans = new RemoveGameTransaction(code, "Daily.txt");
			log.transactions.add(trans);
			trans.doTransaction();
			System.out.println(code[0]);
			System.out.println(code[2]);
		} catch (TransactionConstraintException transactionConstraintException) {
			transactionConstraintException.printStackTrace();
			this.showMsg(transactionConstraintException.toString());
			return;
		}
		}

		if (this.getTyp().equals("refund")) {

			try {
				Transaction trans = new RefundTransaction(code, "Daily.txt");
				trans.doTransaction();
				log.transactions.add(trans);
			} catch (TransactionConstraintException transactionConstraintException) {
				transactionConstraintException.printStackTrace();
				this.showMsg(transactionConstraintException.toString());
				return;
			}

		}
		if (this.getTyp().equals("buy")) {

			try {
				Transaction trans = new BuyTransaction(code, "Daily.txt");
				trans.doTransaction();
				log.transactions.add(trans);

			} catch (TransactionConstraintException transactionConstraintException) {
				transactionConstraintException.printStackTrace();
				this.showMsg(transactionConstraintException.toString());
				return;
			}

		}
		if (this.getTyp().equals("sell")) {

			try {
				Transaction trans = new SellTransaction(code, "Daily.txt");
				trans.doTransaction();
				log.transactions.add(trans);

			} catch (TransactionConstraintException transactionConstraintException) {
				transactionConstraintException.printStackTrace();
				this.showMsg(transactionConstraintException.toString());
				return;
			}
		}
		if (this.getTyp().equals("sale")) {

			try {
				Transaction trans = new SaleTransaction(code, "Daily.txt");
				trans.doTransaction();
				log.transactions.add(trans);

			} catch (TransactionConstraintException transactionConstraintException) {
				transactionConstraintException.printStackTrace();
				this.showMsg(transactionConstraintException.toString());
				return;
			}
		}

		if (this.getTyp().equals("gift")) {

			try {
				trans = new GiftTransaction(code, "Daily.txt");
				trans.doTransaction();
				log.transactions.add(trans);

			} catch (TransactionConstraintException transactionConstraintException) {
				transactionConstraintException.printStackTrace();
				this.showMsg(transactionConstraintException.toString());
				return;
			}
		}

		if (this.getTyp().equals("credit")) {

			try {
				trans = new AddcreditTransaction(code, "Daily.txt");
				trans.doTransaction();
				log.transactions.add(trans);
//				this.showMsg(msg);
			} catch (TransactionConstraintException transactionConstraintException) {
				transactionConstraintException.printStackTrace();
				this.showMsg(transactionConstraintException.toString());
				return;
			}
		}

		if (this.getTyp().equals("create")) {

			try {
				Transaction trans = new CreateTransaction(code, "Daily.txt");
				trans.doTransaction();
				log.transactions.add(trans);
			} catch (TransactionConstraintException transactionConstraintException) {
				transactionConstraintException.printStackTrace();
				this.showMsg(transactionConstraintException.toString());
				return;
			}

		}
		if (this.getTyp().equals("delete")) {

			try {
				User u = backEnd.getUserByUsername(code[1]);

				String[] code2 = new String[]{"02", u.username, u.userType.toString(), Double.toString(u.getBalance())};
				Transaction trans = new DeleteTransaction(code2, "Daily.txt");
				trans.doTransaction();
				log.transactions.add(trans);

			} catch (TransactionConstraintException transactionConstraintException) {
				transactionConstraintException.printStackTrace();
				this.showMsg(transactionConstraintException.toString());
				return;
			}
		}

		if (e.getSource() == this.proceedb) {

			this.backEnd.save();
			this.backEnd.loadGames("gameinfo.txt");
			AccountPage ap2 = new AccountPage(backEnd, user, this.log);
			f.setVisible(false);
		}
	}
}
