package exceptions;

/**
 * Created by SauloSamuel on 09/09/2016.
 */
public class AlreadyExistingContentException extends Exception {

    public AlreadyExistingContentException() {
        super("Conteudo ja existente no destino.");
    }

    public AlreadyExistingContentException(String name) {
        super(name + " ja existente no destino.");
    }
}
