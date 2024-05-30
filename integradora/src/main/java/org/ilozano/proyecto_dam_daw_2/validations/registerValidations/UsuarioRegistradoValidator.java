package org.ilozano.proyecto_dam_daw_2.validations.registerValidations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.ilozano.proyecto_dam_daw_2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioRegistradoValidator implements ConstraintValidator<UsuarioRegistrado, String> {

    @Autowired
    UsuarioRepository usuarioRepository;


    public boolean isValid(String email, ConstraintValidatorContext context) {
        // Verificar si el email ya existe en la base de datos
        Usuario usuarioExistente = usuarioRepository.findByEmail(email);
        // Si el usuario existe, la validación falla
        return usuarioExistente == null;
    }
}
