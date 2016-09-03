package models;

import exceptions.EmptyStringException;
import exceptions.InvalidEmailException;
import exceptions.InputException;
import util.Verificador;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Tainah Emmanuele on 24/07/2016.
 */
public class Usuario {
    private String username;
    private String email;
    private String senha;
    private Directory folder;

    public Usuario() {
        this.folder = new Directory("Pasta Pessoal");
    }

    public Usuario(String username, String email, String senha) throws InputException {
        if (Verificador.verificaFormularioCadastro(username,email,senha))
            throw new EmptyStringException();
        if(!Verificador.verificaString(username) && !Verificador.verificaString(email))
            throw new EmptyStringException("Username", "Email");
        if(!Verificador.verificaString(username) && !Verificador.verificaString(senha))
            throw new EmptyStringException("Username", "Senha");
        if(!Verificador.verificaString(email) && !Verificador.verificaString(senha))
            throw new EmptyStringException("Email", "Senha");
        if (!Verificador.verificaString(username))
            throw new EmptyStringException("Username");
        if (!Verificador.verificaString(senha))
            throw new EmptyStringException("Senha");
        if (!Verificador.verificaString(email))
            throw new EmptyStringException("Email");
        if (!Verificador.verificaEmail(email))
            throw new InvalidEmailException();

        this.username = username;
        this.email = email;
        this.senha = senha;
        this.folder = new Directory("Pasta Pessoal");
    }

    // ADICIONA ARQUIVO (nome e tipo)
    public void addArchive(String nameArchive, String type) {
        Content archive = new Archive(nameArchive, type);
        this.folder.addContent(archive);
    }

    // Adiciona um objeto arquivo
    public void adicionaArquivo(Archive novoArquivo) {
        novoArquivo.setParent(this.folder);
        this.folder.addContent(novoArquivo);
    }

    // Adiciona Pasta (nome)
    public void addFolder(String nameFolder) {
        Content directory = new Directory(nameFolder);
        this.folder.addContent(directory);
    }


    //GETTERS AND SETTERS
    public List<Archive> getArchives() {
        return this.folder.getListArchive();
    }

    public List<Directory> getDirectory() {
        return this.folder.getListDirectory();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws Exception {
        if (!Verificador.verificaString(username))
            throw new EmptyStringException("Username");

        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
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


    // EQUALS
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
