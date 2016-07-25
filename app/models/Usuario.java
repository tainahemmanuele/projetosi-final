package models;

import util.Verificador;

/**
 * Created by administrador1 on 24/07/2016.
 */
public class Usuario {
    private String username;
    private String email;
    private String senha;
    private Verificador verifica = new Verificador();

    public Usuario(String username, String email, String senha) throws  Exception {
        this.username = verifica.verificaUsername(username);
        this.email = verifica.verificaEmail(email);
        this.senha =verifica.verificaSenha(senha);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception{
        this.username =  verifica.verificaUsername(username);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        this.email = verifica.verificaEmail(email);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        this.senha = verifica.verificaSenha(senha);
    }

}
