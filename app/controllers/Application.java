package controllers;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
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
    private List<Usuario> listaUsuarios = new ArrayList<>();

    public Result index() {
        return ok(index.render(listaUsuarios));
    }


    public Result cadastraUsuario() {
        Usuario usuario = formFactory.form(Usuario.class).bindFromRequest().get();
        listaUsuarios.add(usuario);
        //JPA.em().persist(usuario);
        return ok(mensagem.render(""));

    }

    public Result usuario(String email){
        return ok(usuario.render(getUsuarioEmail(email)));
    }



    public Result login(){
        return TODO;
    }

    private Usuario getUsuarioEmail(String email){
        for(Usuario usuario:listaUsuarios){
            if(usuario.getEmail().equals(email)){
                return usuario;
            }

        }
        return null;
    }


    public Result editarUsuario(String email)throws Exception{
        Usuario usuarioAtual= getUsuarioEmail(email);
        Usuario usuarioNovo = formFactory.form(Usuario.class).bindFromRequest().get();
        usuarioAtual.setUsername(usuarioNovo.getUsername());
        usuarioAtual.setEmail(usuarioNovo.getEmail());
        return redirect(routes.Application.index());
    }

    public Result loginEmail(String email, String senha) {
               Usuario user = getUsuarioEmail(email);
        if (user != null && user.getSenha().equals(senha)) {
                       LoggedUserController.loggedUser = user;
            return redirect(routes.LoggedUserController.index());
                   } else {
                       return badRequest("Usuario e/ou senha incorretos.");
        }
          }

           public Result loginUsername(String username, String senha) {
               Usuario user = getUsuarioUsername(username);
               if (user != null && user.getSenha().equals(senha)) {
                       LoggedUserController.loggedUser = user;
                       return redirect(routes.LoggedUserController.index());
                   } else {
                       return badRequest("Usuario e/ou senha incorretos.");
                   }
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
