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
    private Content folder;

    public Usuario(){
        this.folder = new Directory("Pasta Pessoal");
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
        this.folder = new Directory("Pasta Pessoal");

    }

    // ADICIONA ARQUIVOS E PASTAS
    public void addArchive(String nameArchive){
        Content archive = new Archive(nameArchive);
        ((Directory)this.folder).addContent(archive, folder);
    }

    public void addFolder(String nameFolder){
        Content directory = new Directory(nameFolder);
        ((Directory)this.folder).addContent(directory, folder);
    }

    public String getContents(){
        return this.folder.toString();
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario usuario = (Usuario) obj;
            if (usuario.getUsername().equals(this.getUsername())
                    && usuario.getEmail() == this.getEmail()) {
                return true;

            } else {

                return false;
            }
        }
        return false;
    }

}
