package util;

/**
 * Created by Tainah Emmanuele on 24/07/2016.
 */
public class Verificador {
    public Verificador() {
    }

    public static String verificaUsername(String username) throws Exception{
        if(username.equals("")) {
            throw new Exception("Username nao pode ser vazio");
        }
        else if(username.startsWith(" ")){
            throw new Exception("Username nao pode ser vazio");
        }else{
            return username;
        }
    }

    public static String verificaEmail(String email) throws Exception {
        if (((email.endsWith(".com") || (email.endsWith(".com.br"))) && (email
                .matches("(.*)@(.*)")) == true)) {
            return email;
        } else {
            throw new Exception("Formato de e-mail incorreto");
        }
    }

    public static String verificaSenha(String senha) throws Exception {
        if (senha.equals("")) {
            throw new Exception(
                    "Senha nao pode ser vazia");
        } else if (senha.startsWith(" ")) {
            throw new Exception(
                    "Senha nao pode ser vazia.");
        } else {
            return senha;

        }
    }
}
