package src;

/**
 * A class to represent an exception that occurs when a login was not successful.
 */
public class LoginException extends Exception{

    private static final long serialVersionUID = 1L;
    private String msg;

    /**
     * Create an exception with the provided message
     * @param msg the message to display
     */
    public LoginException(String msg) {
        super(msg);
        this.msg=msg;
    }

    public String toString() {
        return msg;
    }

}
