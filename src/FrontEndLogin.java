package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the main GUI that greets the user with the login screen upon the execution of the program.
 */
class FrontEndLogin extends JFrame implements ActionListener {

    protected BackEndService backEnd;
    protected DailyTransactionLog log;
    protected JTextField usernameInput;
    protected JTextField passwordInput;
    protected JLabel usernameLabel;
    protected JLabel passwordLabel;
    protected JFrame f;
    protected JButton loginb;
    protected JLabel lab;
    protected JPanel p;

    /**
     * Creates the GUI with all the necessary GUI elements.
     * @param backEnd the backend service associated with this session
     * @param log the transaction log of the session
     */
    public FrontEndLogin(BackEndService backEnd, DailyTransactionLog log) {

        this.backEnd = backEnd;
        this.log = log;
        f = new JFrame("SCRUMptious Games");
        lab = new JLabel("Please Enter Your Username and Password");
        loginb = new JButton("LOGIN");
        usernameInput = new JTextField(16);
        passwordInput = new JTextField(16);
        usernameLabel = new JLabel("USERNAME");
        passwordLabel = new JLabel("PASSWORD");

        p = new JPanel();

        p.add(usernameLabel);
        p.add(usernameInput);
        p.add(passwordLabel);
        p.add(passwordInput);
        p.add(loginb);
        p.add(lab);

        loginb.addActionListener(this);

        f.add(p);

        f.setSize(300, 300);

        f.show();


    }

    @Override
    /**
     * This method detects input from the user in the form of text box inputs and button clicks.
     */
    public void actionPerformed(ActionEvent e) {

        String user = this.usernameInput.getText();
        String pass = this.passwordInput.getText();
        User[] users = backEnd.getUsers().values().toArray(new User[0]);
        System.out.println(users.length);
        try {

            if ((this.usernameInput.getText().equals("") && this.passwordInput.getText().equals(""))) {
                throw new LoginException("INVALID CREDENTIALS");
            } else {
                for (int i = 0; i < users.length; i++) {
                    if (users[i].username.equals(user) && users[i].password.equals(pass)) {
                        this.f.setVisible(false);
                        User userobj = backEnd.getUserByUsername(user);
                        AccountPage ap = new AccountPage(backEnd, userobj, log);
                        String[] code = {"00", users[i].username, String.valueOf(users[i].userType), String.valueOf(users[i].balance)};
                        LoginTransaction lt = new LoginTransaction(code,"Daily.txt");
                        log.transactions.add(lt);
                    }
                }
                throw new LoginException("INVALID CREDENTIALS");
            }

        } catch (LoginException le) {
            this.loginb.setVisible(false);
            this.lab.setVisible(false);
            JLabel err = new JLabel("INVALID LOGIN CREDENTIALS");
            this.p.add(err);
            JButton again = new JButton("TRY AGAIN");
            this.p.add(again);
            again.addActionListener(this);

        }
        
    }
}
