package controllers;

import models.Texto;
import play.*;
import play.mvc.*;
import play.db.jpa.*;
import util.FormularioLogin;
import util.Verificador;
import views.html.*;
import models.Usuario;
import play.data.FormFactory;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


import static play.libs.Json.*;

public class Application extends Controller {

    @Inject
    private FormFactory formFactory;
    private static List<Usuario> listaUsuarios = new ArrayList<>();





    public Result index() {
        return ok(index.render(listaUsuarios));
    }


    public Result cadastraUsuario() {
        Usuario usuario = formFactory.form(Usuario.class).bindFromRequest().get();
        listaUsuarios.add(usuario);
        //JPA.em().persist(usuario);
        return ok(mensagem.render(""));

    }

    public Result loginRender() {
        return ok(login.render());
    }

    public Result login(){

        FormularioLogin formLogin = formFactory.form(FormularioLogin.class).bindFromRequest().get();
        Usuario user = null;

        if (!Verificador.verificaString(formLogin.getLogin())) {
            flash("login", "Campo de Loging nao pode ser vazio.");
            return redirect(routes.Application.loginRender());
        } else if (!Verificador.verificaString(formLogin.getSenha())) {
            flash("login", "Campo de senha nao pode ser vazio.");
            return redirect(routes.Application.loginRender());

        } else if (Verificador.verificaEmail(formLogin.getLogin()))
            user = getUsuarioEmail(formLogin.getLogin());
        else
            user = getUsuarioUsername(formLogin.getLogin());

        if (user != null && user.getSenha().equals(formLogin.getSenha())) {
            session("email", user.getEmail());
            return redirect(routes.LoggedUserController.index());
        } else {
            flash("login", "Senha e/ou usuario incorretos.");
            return redirect(routes.Application.loginRender());
        }

    }

    public static Usuario getUsuarioEmail(String email){
        for(Usuario usuario:listaUsuarios){
            if(usuario.getEmail().equals(email)){
                return usuario;
            }

        }
        return null;
    }

    private Usuario getUsuarioUsername(String username){
        for(Usuario usuario:listaUsuarios){
            if(usuario.getUsername().equals(username)){
                return usuario;
            }

        }
        return null;
    }
}