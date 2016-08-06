package exceptions;

/**
 * Created by SauloSamuel on 03/08/2016.
 */
public class EmptyStringException extends InputException {

    public EmptyStringException(String name) {
        super(name + " não pode ser vazio(a).");
    }
}
