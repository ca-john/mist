package src;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * A class to facilitate the display of the Account Page after a successful login.
 */
public class AccountPage extends JFrame implements ActionListener{

    protected BackEndService backEnd;
    protected User user;

    protected JFrame f;

    protected JPanel p;


    protected JLabel inv;
    protected JTextArea textArea;

    protected JLabel acc;
    protected JLabel accamt;

    protected JLabel sell;
    protected JButton sellb;

    protected JLabel buy;
    protected JButton buyb;

    protected JLabel create;
    protected JButton createb;

    protected JLabel remove;
    protected JButton removeb;

    protected JLabel delete;
    protected JButton deleteb;

    protected JLabel refund;
    protected JButton refundb;

    protected JLabel sale;
    protected JButton saleb;

    protected JLabel gift;
    protected JButton giftb;

    protected JLabel credit;
    protected JButton creditb;

    protected JLabel logout;
    protected JButton logoutb;

    protected DailyTransactionLog log;

    public AccountPage(BackEndService backEnd, User user, DailyTransactionLog log){

        this.backEnd = backEnd;
        backEnd.setLoginUser(user);
        this.user = user;
        this.log = log;

        f = new JFrame("SCRUMptious Games");
        String inventory = this.inventoryFormat();

        textArea = new JTextArea(5, 20);
//        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textArea.setText(inventory);
//        textArea.append(inventory);
//        System.out.println(inventory);
        
        inv = new JLabel("INVENTORY");

        acc = new JLabel("ACCOUNT BALANCE");
        accamt = new JLabel("\t\t\t$" + String.valueOf(Math.round(user.getBalance()*100)/100));

        remove = new JLabel("REMOVE A GAME");
        removeb = new JButton("REMOVE");
        removeb.addActionListener(this);

        create = new JLabel("CREATE AN ACCOUNT");
        createb = new JButton("CREATE");
        createb.addActionListener(this);

        delete = new JLabel("DELETE AN ACCOUNT");
        deleteb = new JButton("DELETE");
        deleteb.addActionListener(this);

        sale = new JLabel("ACTIVATE SALE");
        saleb = new JButton("SALE");
        saleb.addActionListener(this);

        sell = new JLabel("SELL A GAME");
        sellb = new JButton("SELL");
        sellb.addActionListener(this);

        buy = new JLabel("BUY A GAME");
        buyb = new JButton("BUY");
        buyb.addActionListener(this);

        refund  = new JLabel("REQUEST A REFUND");
        refundb = new JButton("REQUEST");
        refundb.addActionListener(this);

        gift = new JLabel("GIFT A GAME");
        giftb = new JButton("GIFT");
        giftb.addActionListener(this);

        credit = new JLabel("ADD CREDIT TO USER ACCOUNT");
        creditb = new JButton("ADD");
        creditb.addActionListener(this);

        logout = new JLabel("LOGOUT OF ACCOUNT");
        logoutb  = new JButton("LOGOUT");
        logoutb.addActionListener(this);



        p = new JPanel();
        p.add(acc);
        p.add(accamt);
        p.add(inv);
        p.add(textArea);

        //Get user type and set buttons according
        System.out.println(user.userType.getValue());

        if (user.userType.getValue() == "admin"){
            p.add(create);
            p.add(createb);
            p.add(delete);
            p.add(deleteb);
            p.add(credit);
            p.add(creditb);
            p.add(sale);
            p.add(saleb);
            p.add(sell);
            p.add(sellb);
            p.add(buy);
            p.add(buyb);
            p.add(remove);
            p.add(removeb);
            p.add(refund);
            p.add(refundb);
            p.add(gift);
            p.add(giftb);
        }
        if (user.userType.getValue() == "buy-standard"){

            p.add(buy);
            p.add(buyb);
            p.add(remove);
            p.add(removeb);
            p.add(refund);
            p.add(refundb);
            p.add(gift);
            p.add(giftb);
            p.add(credit);
            p.add(creditb);

        }
        if (user.userType.getValue() == "sell-standard"){

            p.add(sell);
            p.add(sellb);
            p.add(remove);
            p.add(removeb);
            p.add(gift);
            p.add(giftb);
            p.add(credit);
            p.add(creditb);

        }
        if (user.userType.getValue() == "full-standard"){

            p.add(sell);
            p.add(sellb);
            p.add(buy);
            p.add(buyb);
            p.add(remove);
            p.add(removeb);
            p.add(gift);
            p.add(giftb);
            p.add(credit);
            p.add(creditb);

        }

        p.add(logout);
        p.add(logoutb);

        p.add(Box.createHorizontalGlue());
        p.add(Box.createHorizontalGlue());
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setAlignmentY(java.awt.Component.LEFT_ALIGNMENT);
        f.add(p);
        f.setSize(300, 700);
        f.show();


    }

    /**
     * This method displays the user's list of games and their respective price.
     * @return All games and their prices
     */
    public String inventoryFormat() {
        String str = "";
        for (int i = 0; i < this.user.getGames().size(); i++) {
            Game temp = this.user.getGames().get(i);
            str += temp.getName() + " - $" + temp.getDiscountPrice() + "0" + "\n";
        }
        return str;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();


        // ADD NAME TO TRANSACTION PAGE SO WE CAN FIGURE OUT WHICH TRANSACTION TO SETUP

        if (button == this.removeb){
            TransactionPage tp = new TransactionPage(backEnd, user, "remove", log);
            f.setVisible(false);
        }

        if (button == this.createb){
            System.out.println("creating");
            TransactionPage tp = new TransactionPage(backEnd, user, "create", log);
            f.setVisible(false);
        }
        if (button == this.deleteb){
            System.out.println("d");
            TransactionPage tp = new TransactionPage(backEnd, user, "delete", log);
            f.setVisible(false);
        }
        if (button == this.saleb){
            TransactionPage tp = new TransactionPage(backEnd, user, "sale", log);

        }
        if (button == this.sellb){
            System.out.println("s");
            TransactionPage tp = new TransactionPage(backEnd, user, "sell", log);
            f.setVisible(false);
        }
        if (button == this.buyb){
            System.out.println("b");
            TransactionPage tp = new TransactionPage(backEnd, user, "buy", log);
            f.setVisible(false);
        }
        if (button == this.refundb){
            System.out.println("r");
            TransactionPage tp = new TransactionPage(backEnd, user, "refund", log);
            f.setVisible(false);
        }
        if (button == this.giftb){
            System.out.println("g");
            TransactionPage tp = new TransactionPage(backEnd, user, "gift", log);
            f.setVisible(false);
        }
        if (button == this.creditb){
            System.out.println("ac");
            TransactionPage tp = new TransactionPage(backEnd, user, "credit", log);
            f.setVisible(false);
        }
        if (button == this.logoutb){

            String[] cod = {"10", this.user.getUsername(), String.valueOf(this.user.userType), String.valueOf(this.user.getBalance())};
            LogoutTransaction lo = new LogoutTransaction(cod, "Daily.txt");
            log.transactions.add(lo);
            try {
                lo.doTransaction();

            } catch (TransactionConstraintException transactionConstraintException) {
                transactionConstraintException.printStackTrace();
            }
            try {
                log.write();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            backEnd.save();
            System.exit(0);
        }

    }
}
