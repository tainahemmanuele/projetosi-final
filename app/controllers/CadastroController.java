package controllers;

import exceptions.*;
import play.*;
import play.mvc.*;
import play.mvc.Result;
import play.db.jpa.*;
import util.FormularioLogin;
import util.Verificador;
import views.html.*;
import models.Usuario;
import play.data.FormFactory;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Tainah Emmanuele on 05/08/2016.
 */
public class CadastroController extends Controller {
    @Inject
    private FormFactory formFactory;

    private Verificador verificador;

    public Result cadastraUsuario()  {

        try{
            Usuario usuario = formFactory.form(Usuario.class).bindFromRequest().get();
            try {
                Application.buscaUsuario(usuario);
                Application.adicionaUsuario(usuario);
                return ok(mensagem.render(""));
            } catch (UsuarioException e){
                flash("cadastro", e.getMessage());
                return ok(index.render());
            }

        }catch( Exception e){
            flash("cadastro", "Falha ao tentar cadastrar. Tente Novamente");
            return ok(index.render());

        }

        //JPA.em().persist(usuario);


    }





    public Result index() {
        return ok(index.render());
    }



}