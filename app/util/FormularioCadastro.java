package util;

import java.io.Serializable;

/**
 * Created by Tainah Emmanuele on 24/08/2016.
 */
public class FormularioCadastro implements Serializable {
    private String username;
    private String email;
    private String senha;

    public FormularioCadastro() {
    }

    public FormularioCadastro(String username,String email, String senha) {
        this.username = username;
        this.email = email;
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
