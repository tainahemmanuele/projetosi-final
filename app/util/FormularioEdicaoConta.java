package util;

/**
 * Created by SauloSamuel on 09/09/2016.
 */
public class FormularioEdicaoConta {
    private String username;
    private String email;
    private String senhaAtual;
    private String senhaNova1;
    private String senhaNova2;

    public FormularioEdicaoConta(){

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

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getSenhaNova1() {
        return senhaNova1;
    }

    public void setSenhaNova1(String senhaNova1) {
        this.senhaNova1 = senhaNova1;
    }

    public String getSenhaNova2() {
        return senhaNova2;
    }

    public void setSenhaNova2(String senhaNova2) {
        this.senhaNova2 = senhaNova2;
    }
}
