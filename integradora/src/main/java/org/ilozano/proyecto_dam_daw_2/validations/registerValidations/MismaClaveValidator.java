package org.ilozano.proyecto_dam_daw_2.validations.registerValidations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ilozano.proyecto_dam_daw_2.model.Usuario;

public class MismaClaveValidator implements ConstraintValidator<MismaClave, Usuario> {

    //Verifica que los campos clave y confirmarClave son iguales en el formulario
    public boolean isValid(Usuario usuario, ConstraintValidatorContext c) {
        if (usuario == null) {
            return false;
        }
        return usuario.getClave().equals(usuario.getConfirmarClave());
    }
}
