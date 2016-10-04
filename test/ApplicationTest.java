import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import exceptions.EmptyStringException;
import exceptions.InputException;
import models.Usuario;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import views.html.helper.input;

import static play.test.Helpers.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


/**
*Created by Elvis Victor 02/10/2016
*/
public class ApplicationTest {

    Usuario usuarioPadrao;

    @Before
    public void InitializationVariable () throws InputException {
        usuarioPadrao = new Usuario("Elvis","","");
    }

    @Test
    public void  TestsUsuarioNull (){
        Usuario UsuarioNulo = null;
        try {

            UsuarioNulo =  new Usuario("","","");
            fail("Usuario e nulo");
        } catch (InputException e) {

            assertNull(e.getMessage(),UsuarioNulo);

        }
    }

}
