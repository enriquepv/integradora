package org.ilozano.proyecto_dam_daw_2.validations.userLoginValidations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.ilozano.proyecto_dam_daw_2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExisteUsuarioValidator implements ConstraintValidator<ExisteUsuario, String> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        // Verificar si el email ya existe en la base de datos
        Usuario usuarioExistente = usuarioRepository.findByEmail(email);
        System.out.println(usuarioExistente);
        // Si el usuario existe, la validación falla
        return usuarioExistente != null;
    }
}
