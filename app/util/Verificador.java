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


}
