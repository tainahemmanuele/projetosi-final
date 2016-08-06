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
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private Verificador verificador;

    public Result cadastraUsuario()  {

        try{
            Usuario usuario = formFactory.form(Usuario.class).bindFromRequest().get();
            listaUsuarios.add(usuario);
            return ok(mensagem.render(""));

        }catch( Exception e){
            flash("cadastro", "Falha ao tentar cadastrar. Tente Novamente");
            return ok(index.render(listaUsuarios));

        }







        //JPA.em().persist(usuario);


    }

    public static List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }



    public Result index() {
        return ok(index.render(listaUsuarios));
    }
}

