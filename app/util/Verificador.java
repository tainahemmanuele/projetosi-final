package util;

/**
 * Created by Tainah Emmanuele on 24/07/2016.
 */
public class Verificador {
    public Verificador() {
    }

    public static boolean verificaString(String string) {
        if(string.trim().length() > 0){
            return true;
        }else{
            return false;
        }
    }

    public static boolean verificaEmail(String email) {
        if (((email.endsWith(".com") || (email.endsWith(".com.br"))) && (email
                .matches("(.*)@(.*)")) == true)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean verificaFormularioCadastro(String username, String email, String senha){
        if (username.trim().length() ==0 && email.trim().length() ==0 && senha.trim().length()==0 ) {
            return true;
        }else{
            return false;
        }


    }

}
