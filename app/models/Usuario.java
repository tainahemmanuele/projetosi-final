package models;

import exceptions.EmptyStringException;
import exceptions.InvalidEmailException;
import util.Verificador;

import java.util.List;

/**
 * Created by Tainah Emmanuele on 24/07/2016.
 */
public class Usuario {
    private String username;
    private String email;
    private String senha;
    private Directory folder;

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
        this.folder.addContent(archive);
    }

    // Adiciona um arquivo ja existente.
    public void adicionaArquivo(Archive novoArquivo) {
        novoArquivo.setParent(this.folder);
        this.folder.addContent(novoArquivo);
    }

    public void addFolder(String nameFolder){
        Content directory = new Directory(nameFolder);
        this.folder.addContent(directory);
    }


    public List<Archive> getArchives(){
        return this.folder.getListArchive();
    }
    public List<Directory> getDirectory(){
        return this.folder.getListDirectory();
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

    public Content getContent(String path) {
        String[] pathComponents = path.split("/");
        Content content = this.folder;
        for (int i = 1; i < pathComponents.length; i++) {
            content = ((Directory) content).getContent(pathComponents[i]);
        }
        return content;
    }

    public Directory getFolder() {
        return this.folder;
    }

    public void setFolderContent(Directory dir, Content conteudo) {
        conteudo.setParent(dir);
    }
}
