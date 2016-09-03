package exceptions;

/**
 * Created by Tainah Emmanuele on 06/08/2016.
 */
public class UsuarioException extends Exception{

    public UsuarioException(String message) {
        super("Já existe um usuario com esse "+message+". Tente Novamente.");
    }
}
