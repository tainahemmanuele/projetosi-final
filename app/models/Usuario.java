package models;

import exceptions.AlreadyExistingContentException;
import exceptions.EmptyStringException;
import exceptions.InvalidEmailException;
import exceptions.InputException;
import util.Verificador;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Tainah Emmanuele on 24/07/2016.
 */
public class Usuario {

    public static String DEFAULT_FOLDER_NAME = "Pasta Pessoal";
    public static String SHARING_FOLDER_NAME = "Compartilhados";

    private String username;
    private String email;
    private String senha;
    private Directory folder;
    private Directory compartilhados;
    private ArrayList<String> notificacoes;

    public Usuario() throws EmptyStringException {
        this.folder = new Directory(DEFAULT_FOLDER_NAME);
        this.compartilhados = new Directory(SHARING_FOLDER_NAME);
        this.notificacoes = new ArrayList<String>();
        this.notificacoes.add("Bem vindo ao TextDropBox");
        try {
            this.folder.addContent(compartilhados);
        } catch (AlreadyExistingContentException e){
            throw new RuntimeException("Erro do sistema");
        }
    }

    public Usuario(String username, String email, String senha) throws InputException {
        if (!Verificador.verificaString(username) && !Verificador.verificaString(email) && !Verificador.verificaString(senha))
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
        this.folder = new Directory(DEFAULT_FOLDER_NAME);
        this.compartilhados = new Directory(SHARING_FOLDER_NAME);
        this.notificacoes = new ArrayList<String>();
        this.notificacoes.add("Bem vindo ao TextDropBox");
        try {
            this.folder.addContent(compartilhados);
        } catch (AlreadyExistingContentException e){
            throw new RuntimeException("Erro do sistema");
        }
    }

    //GETTERS AND SETTERS
    public List<IArchive> getArchives() {
        return this.folder.getListArchive();
    }

    public List<Directory> getDirectory() {
        return this.folder.getListDirectory();
    }

    public Directory getCompartilhados() {
        return this.compartilhados;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws InputException {
        if (!Verificador.verificaString(username))
            throw new EmptyStringException("Username");

        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InputException {
        if (!Verificador.verificaEmail(email))
            throw new InvalidEmailException();

        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws InputException {
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

    public void compartilhar(Usuario user, String tipo, String path) throws AlreadyExistingContentException {
        Content arquivo = getContent(path);
        if (!(arquivo.isDirectory()) && !(user.getCompartilhados().hasArchive((IArchive) arquivo))) {
            ((IArchive) arquivo).compartilhar(user, tipo, this.getUsername());
            ArchiveLink linkArchive = new ArchiveLink((IArchive)arquivo);
            user.getCompartilhados().addContent(linkArchive);
        }
    }

    public void sairCompartilhamento(String path) {
        Content arquivo = getContent(path);
        if (!arquivo.isDirectory() && (this.compartilhados.hasArchive((IArchive) arquivo))) {
            this.getCompartilhados().delContent(arquivo);
            ((IArchive) arquivo).sairCompartilhamento(this);
        }
    }

    // Metodo para o dono do arquivo cancelar o compartilhamento.
    public void cancelarCompartilhamento(String path) {
        IArchive archive = (IArchive) getContent(path);
        String sharingPath = DEFAULT_FOLDER_NAME + "/" + SHARING_FOLDER_NAME + "/" + archive.getNameType();
        archive.cancelarCompartilhamento(sharingPath);
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

    public ArrayList<String> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(ArrayList<String> notificacoes) {
        this.notificacoes = notificacoes;
    }
}
