package exceptions;

/**
 * Created by SauloSamuel on 03/08/2016.
 */
public class InvalidEmailException extends InputException{

    public InvalidEmailException() {
        super("Formato de email invalido. Tente novamente.");
    }

}
