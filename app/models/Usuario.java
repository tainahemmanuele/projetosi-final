package models;

import util.Verificador;

import java.lang.reflect.Array;
import java.util.*;


/**
 * Created by Tainah Emmanuele on 24/07/2016.
 */
public class Usuario {
    private String username;
    private String email;
    private String senha;
    private List<Texto> arquivosTexto;

    public Usuario(){

    }
    public Usuario(String username, String email, String senha) throws  Exception {
        this.username = Verificador.verificaUsername(username);
        this.email = Verificador.verificaEmail(email);
        this.senha = Verificador.verificaSenha(senha);
        this.arquivosTexto = new ArrayList<Texto>();

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception{
        this.username =  Verificador.verificaUsername(username);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception{
        this.email = Verificador.verificaEmail(email);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        this.senha = Verificador.verificaSenha(senha);
    }

    public List<Texto> getListaArquivos(){ return arquivosTexto;}
}
