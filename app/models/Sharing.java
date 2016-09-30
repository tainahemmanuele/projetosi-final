package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Constraint;

import play.data.validation.Constraints;
import play.db.ebean.Model;

/**
 * Created by João Marcos on 08/09/2016.
 */
@Entity
public class Sharing extends Model {

    @Id
    public long id;
    @Constraints.Required
    private List<Usuario> compartilhadoLeitura;
    @Constraints.Required
    private List<Usuario> compartilhadoEdicao;

    public Sharing() {
        this.compartilhadoEdicao = new ArrayList<Usuario>();
        this.compartilhadoLeitura = new ArrayList<Usuario>();
    }

    public boolean getStatus() {
        return this.compartilhadoEdicao.size() > 0 || this.compartilhadoLeitura.size() > 0;
    }

    public List<Usuario> getCompartilhadoLeitura() {
        return this.compartilhadoLeitura;
    }

    public void setCompartilhadoLeitura(List<Usuario> compartilhadoLeitura) {
        this.compartilhadoLeitura = compartilhadoLeitura;
    }

    public List<Usuario> getCompartilhadoEdicao() {
        return this.compartilhadoEdicao;
    }

    public void setCompartilhadoEdicao(List<Usuario> compartilhadoEdicao) {
        this.compartilhadoEdicao = compartilhadoEdicao;
    }

    public boolean canEdit(Usuario user) {
        return this.compartilhadoEdicao.contains(user);
    }

    public boolean canOnlyRead(Usuario user) {
        return this.compartilhadoLeitura.contains(user);
    }

    public void addCompartilhamento(Usuario user, String tipo) {
        if (tipo.equals("Leitura") && !canOnlyRead(user) && !canEdit(user)) {
            this.compartilhadoLeitura.add(user);
        } else if (tipo.equals("Edicao") && !canEdit(user)) {
            this.compartilhadoEdicao.add(user);
        }
    }

    public void sairCompartilhamento(Usuario user) {
        if (this.compartilhadoLeitura.contains(user)) {
            this.compartilhadoLeitura.remove(user);
        } else if (this.compartilhadoEdicao.contains(user)) {
            this.compartilhadoEdicao.remove(user);
        }
    }

    public void limparCompartilhamento(String path) {
        for (Usuario user : this.compartilhadoLeitura) {
            user.getCompartilhados().delContent(user.getContent(path));
            user.save();
        }
        for (Usuario user : this.compartilhadoEdicao) {
            user.getCompartilhados().delContent(user.getContent(path));
            user.save();
        }
        this.compartilhadoEdicao = new ArrayList<Usuario>();
        this.compartilhadoLeitura = new ArrayList<Usuario>();
    }
}