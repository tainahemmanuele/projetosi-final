package util;

/**
 * Created by João Marcos on 09/09/2016.
 */
public class FormularioCompartilhamento {

    String emailUser;
    String tipo;

    public FormularioCompartilhamento() {
        this.tipo = "";
        this.emailUser = "";
    }

    public FormularioCompartilhamento(String emailUser, String tipo) {
        this.emailUser = emailUser;
        this.tipo = tipo;

    }

    public FormularioCompartilhamento(String emailUser) {
        this.emailUser = emailUser;
        this.tipo = "";
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmailUser() {
        return this.emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

}
