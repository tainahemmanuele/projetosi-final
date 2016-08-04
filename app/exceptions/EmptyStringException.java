package exceptions;

/**
 * Created by SauloSamuel on 03/08/2016.
 */
public class EmptyStringException extends Exception {

    public EmptyStringException(String name) {
        super(name + " não pode ser vaizo(a).");
    }
}
