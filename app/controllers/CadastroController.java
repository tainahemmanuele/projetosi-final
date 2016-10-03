package controllers;

import exceptions.*;
import play.*;
import play.mvc.*;
import play.mvc.Result;
import play.db.jpa.*;
import util.FormularioCadastro;
import util.FormularioLogin;
import util.Verificador;
import views.html.*;
import models.Usuario;
import play.data.FormFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import play.data.Form;


/**
 * Created by Tainah Emmanuele on 05/08/2016.
 */
public class CadastroController extends Controller {
    @Inject
    private FormFactory formFactory;

    private Verificador verificador;

    public Result cadastraUsuario() {

        try {

            FormularioCadastro formCadastro = formFactory.form(FormularioCadastro.class).bindFromRequest().get();
            Usuario usuario = new Usuario(formCadastro.getUsername(), formCadastro.getEmail(), formCadastro.getSenha());
            

            try {

                Application.buscaUsuario(usuario);
                Application.adicionaUsuario(usuario);
                return ok(mensagem.render(""));
            } catch (UsuarioException e) {
                flash("cadastro", e.getMessage());
                return ok(cadastro.render());
            }

        } catch (InputException e) {
            flash("cadastro", e.getMessage());
            return ok(cadastro.render());

        }

        //JPA.em().persist(usuario);


    }


    public Result index() {
        return ok(cadastro.render());
    }


}