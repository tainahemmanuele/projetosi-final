package models;

/**
 * Created by João Marcos on 10/09/2016.
 */
import javax.persistence.*;
import javax.validation.Constraint;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
public class ArchiveLink implements IArchive {

    @Id
    public Long id;
    @Constraints.Required
    private IArchive archive;
    @Constraints.Required
    private Directory parent;

    public ArchiveLink(IArchive archive) {
        this.archive = archive;
    }


    public IArchive getArchive() {
        return this.archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public String getName() {
        return this.archive.getName();
    }

    public void setName(String name) {
        this.archive.setName(name);
    }

    public String getNameType() {
        return this.archive.getNameType();
    }

    public String getType() {
        return this.archive.getType();
    }

    public void setType(String type) {
        this.archive.setType(type);
    }

    public String getText() {
        return this.archive.getText();
    }

    public void setTexto(String texto) {
        this.archive.setTexto(texto);
    }

    public Sharing getCompartilhamento() {
        return this.archive.getCompartilhamento();
    }

    public void setCompartilhamento(Sharing compartilhamento) {
        this.archive.setCompartilhamento(compartilhamento);
    }

    public Usuario getOwner() {
        return this.archive.getOwner();
    }

    public void setOwner(Usuario owner) {
        this.archive.setOwner(owner);
    }

    @Override
    public String toString() {
        return this.archive.toString();
    }

    @Override
    public String getPath() {
        return this.parent.getPath() + "/" + getNameType();
    }

    @Override
    public Directory getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Directory dir) {
        this.parent = dir;
    }

    public boolean isShared() {
        return this.archive.isShared();
    }

    public void compartilhar(Usuario user, String tipo, String username) {
        this.archive.compartilhar(user, tipo, username);
    }

    public void sairCompartilhamento(Usuario user) {
        this.archive.sairCompartilhamento(user);
    }

    public void cancelarCompartilhamento(String path) {
        this.archive.cancelarCompartilhamento(path);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Archive) {
            Archive archive = (Archive) object;
            return archive.equals(this.archive);
        } else if(object instanceof ArchiveLink) {
            ArchiveLink link = (ArchiveLink) object;
            return link.getArchive().equals(this.archive);
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return archive != null ? archive.hashCode() : 0;
    }

}
