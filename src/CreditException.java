package src;

/**
 * A class to represent an exception that occurs when adding or removing credit from an account cannot be accomplished.
 */
public class CreditException extends Exception{

    public CreditException(String string){
        super(string);
    }

}
