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
    private String password;
    private Directory folder;
    private Directory compartilhados;
    private List<String> notificacoes;
    private List<IArchive> depositingGarbage;

    public Usuario() throws EmptyStringException {
        this.folder = new Directory(DEFAULT_FOLDER_NAME);
        this.compartilhados = new Directory(SHARING_FOLDER_NAME);
        this.notificacoes = new ArrayList<String>();
        this.notificacoes.add("Bem vindo ao TextDropBox");
        this.depositingGarbage = new ArrayList<IArchive>();

        try {
            this.folder.addContent(compartilhados);
        } catch (AlreadyExistingContentException e){
            throw new RuntimeException("Erro do sistema");
        }
    }

    public Usuario(String username, String email, String senha) throws InputException {

        verify(username, email, senha);

        this.username = username;
        this.email = email;
        this.password = senha;
        this.folder = new Directory(DEFAULT_FOLDER_NAME);
        this.compartilhados = new Directory(SHARING_FOLDER_NAME);
        this.notificacoes = new ArrayList<String>();
        this.notificacoes.add("Bem vindo ao TextDropBox");
        this.depositingGarbage = new ArrayList<IArchive>();
        try {
            this.folder.addContent(compartilhados);
        } catch (AlreadyExistingContentException e){
            throw new RuntimeException("Erro do sistema");
        }
    }

    private void verify(String username, String email, String senha) throws InputException{
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

    public void removeArchive(String path){
        IArchive archive = (IArchive) getContent(path);
        Directory parent = archive.getParent();
        //Adiciona na lixeira
        this.depositingGarbage.add(archive);
        //Remove do Diretorio
        parent.delContent(archive);
    }

    //GETTERS
    public List<IArchive> getDepositingGarbage(){ return this.depositingGarbage; }

    public List<IArchive> getArchives() { return this.folder.getListArchive(); }

    public List<Directory> getDirectory() { return this.folder.getListDirectory(); }

    public Directory getCompartilhados() {
        return this.compartilhados;
    }

    public String getEmail() { return this.email; }

    public String getUsername() {
        return username;
    }

    public Directory getFolder() { return this.folder; }

    public String getSenha() { return password; }

    public List<String> getNotificacoes() {
        return notificacoes;
    }


    public Content getContent(String path) {
        String[] pathComponents = path.split("/");
        Content content = this.folder;
        for (int i = 1; i < pathComponents.length; i++) {
            content = ((Directory) content).getContent(pathComponents[i]);
        }
        return content;
    }


    //SETTERS

    public void setNotificacoes(ArrayList<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public void setFolderContent(Directory dir, Content conteudo) {
        conteudo.setParent(dir);
    }

    public void setUsername(String username) throws InputException {
        if (!Verificador.verificaString(username))
            throw new EmptyStringException("Username");

        this.username = username;
    }

    public void setEmail(String email) throws InputException {
        if (!Verificador.verificaEmail(email))
            throw new InvalidEmailException();

        this.email = email;
    }

    public void setSenha(String password) throws InputException {
        if (!Verificador.verificaString(password))
            throw new EmptyStringException("Senha");

        this.password = password;
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
