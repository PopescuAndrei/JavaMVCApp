package ro.teamnet.zth.web.exceptions;

/**
 * Created by Andrei on 5/6/2015.
 */
public class DispatchException extends RuntimeException{
    public DispatchException() {
        super();
    }

    public DispatchException(String message) {
        super(message);
    }
}
