package buu.mypizza.exceptions;

/**
 *
 * @author nazar
 */
public class DBException extends Exception{

    @Override
    public String getMessage() {
        return "Problem With DB";
    }
    
    
}
