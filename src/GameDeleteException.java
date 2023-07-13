package src;

/**
 * A class to handle the error when a user's library doesn't contain the game that needs to be deleted.
 */
public class GameDeleteException extends Exception{

    public GameDeleteException(String string){
        super(string);
    }



}
