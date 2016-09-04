package exceptions;

/**
 * Created by SauloSamuel on 03/08/2016.
 */
public class EmptyStringException extends InputException {

    public EmptyStringException(String name) {
        super(name + " não pode ser vazio(a).Tente Novamente.");
    }

    public EmptyStringException() {
        super("Campos do formulário não podem ser vazios. Tente Novamente.");
    }

    public EmptyStringException(String name, String name2) {
        super(name +" e " + name2+ " não podem ser  vazios. Tente Novamente");
    }
}
