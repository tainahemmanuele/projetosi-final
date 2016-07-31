package util;

import javax.security.auth.login.LoginContext;

/**
 * Created by SauloSamuel on 31/07/2016.
 */
public class FormularioLogin {

    private String login;
    private String senha;

    public FormularioLogin() {

    }

    public FormularioLogin(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
