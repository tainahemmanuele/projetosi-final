package models;

import exceptions.EmptyStringException;
import exceptions.InvalidEmailException;
import util.Verificador;

/**
 * Created by Tainah Emmanuele on 24/07/2016.
 */
public class Usuario {
    private String username;
    private String email;
    private String senha;

    public Usuario(){

    }
    public Usuario(String username, String email, String senha) throws  Exception {
        if (!Verificador.verificaString(username))
            throw new EmptyStringException("Username");
        if (!Verificador.verificaString(senha))
            throw new EmptyStringException("Senha");
        if (!Verificador.verificaEmail(email))
            throw new InvalidEmailException();

        this.username = username;
        this.email = email;
        this.senha = senha;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception{
        if (!Verificador.verificaString(username))
            throw new EmptyStringException("Username");

        this.username =  username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        if (!Verificador.verificaEmail(email))
            throw new InvalidEmailException();

        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        if (!Verificador.verificaString(senha))
            throw new EmptyStringException("Senha");

        this.senha = senha;
    }

}
