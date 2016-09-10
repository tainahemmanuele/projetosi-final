package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by João Marcos on 08/09/2016.
 */
public class Sharing {

    private List<Usuario> compartilhadoLeitura;
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
        } else if (tipo.equals("Editar") && !canEdit(user)) {
            this.compartilhadoEdicao.add(user);
        }
    }

    public void cancelarCompartilhamento(Usuario user) {
        if (this.compartilhadoLeitura.contains(user)) {
            this.compartilhadoLeitura.remove(user);
        } else if (this.compartilhadoEdicao.contains(user)) {
            this.compartilhadoEdicao.remove(user);
        }

    }

}