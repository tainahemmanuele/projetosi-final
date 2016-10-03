package models;


import exceptions.EmptyStringException;
import util.Verificador;

import javax.persistence.*;
import play.data.validation.Constraints;
import com.avaje.ebean.Model;

import java.io.Serializable;
//import play.db.ebean.Model;
/**
 * Created by Suelany on 05/08/2016.
 */
@Entity
public class Archive extends Model implements IArchive, Serializable {
    @Id
    public long id;
    private String name;
    private String text;
    private String type;
    @OneToOne (mappedBy = "usuario")
    private Directory parent;
    @OneToOne (mappedBy = "usuario")
    private Sharing compartilhamento;
    @ManyToOne (cascade = CascadeType.ALL)
    Usuario owner;


    public Archive(){
        this.text = "";
        this.compartilhamento = new Sharing();
    }

    public Archive(String name, String type) throws EmptyStringException {
        if (!Verificador.verificaString(name)) {
            throw new EmptyStringException("Nome do arquivo");
        }
        this.name = name;
        this.type = type;
        this.text = "";
        this.compartilhamento = new Sharing();
    }

    public Archive(String name, String type, String text){
        this.name = name;
        this.type = type;
        this.text = text;
        this.compartilhamento = new Sharing();
    }


    public void compartilhar(Usuario user, String tipo, String username) {
        this.compartilhamento.addCompartilhamento(user, tipo);
        user.getNotificacoes().add(username + " compartilhou o seguinte arquivo: " + this.getName() +"."+ this.type);
    }

    public void sairCompartilhamento(Usuario user) {
        this.compartilhamento.sairCompartilhamento(user);
    }

    public void cancelarCompartilhamento(String path) {
        this.compartilhamento.limparCompartilhamento(path);
    }


    public boolean isDirectory(){
        return false;
    }

    public boolean isShared() {
        return this.compartilhamento.getStatus();
    }


    // GETTERS AND SETTERS
    public String getName(){
        return this.name;
    }

    public String getNameType() {
        return  standardName(getName(), getType());
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getType(){ return this.type; }

    public void setType(String newType){
        this.type = newType;
    }

    public String getText() {
        return this.text;
    }

    public void setTexto(String newText) {
        this.text = newText;
    }

    public Sharing getCompartilhamento() {
        return this.compartilhamento;
    }

    public void setCompartilhamento(Sharing compartilhamento) {
        this.compartilhamento = compartilhamento;
    }

    public Usuario getOwner() {
        return this.owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

    @Override
    public Directory getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public String getPath() {
        if (this.parent == null) {
            return getNameType();
        } else {
            return this.parent.getPath() + "/" + getNameType();
        }
    }


    // Este metodo remove os caracteres em branco do inicio do titulo.
    private static String standardName(String nameArchive, String type){
        int indexCharacter = 0;
        while( nameArchive.charAt(indexCharacter) == ' '  ){
            indexCharacter++;
        }
        String newName = nameArchive.substring(indexCharacter, (nameArchive.length()));
        if (!newName.endsWith("." + type)) {
            newName = newName + "." + type;

        }
        return newName;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Archive) {
            Archive archive = (Archive) object;
            return name.equals(archive.getName()) && type.equals(archive.getType());
        } else
            return false;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
