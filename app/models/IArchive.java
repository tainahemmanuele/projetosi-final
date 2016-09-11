package models;

/**
 * Created by João Marcos on 10/09/2016.
 */
public interface IArchive extends Content {

    public boolean isDirectory();

    public String getName();

    public void setName(String name);

    public String getNameType();

    public String getType();

    public void setType(String type);

    public String getText();

    public void setTexto(String texto);

    public Sharing getCompartilhamento();

    public void setCompartilhamento(Sharing compartilhamento);

    public Usuario getOwner();

    public void setOwner(Usuario owner);

    public String toString();

    public String getPath();

    public Directory getParent();

    public void setParent(Directory dir);

    public boolean isShared();

    public void compartilhar(Usuario user, String tipo, String username);

    public void sairCompartilhamento(Usuario user);

    public void cancelarCompartilhamento(String path);

}
