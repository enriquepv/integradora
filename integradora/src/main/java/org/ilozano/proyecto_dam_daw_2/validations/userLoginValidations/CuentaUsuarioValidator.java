package org.ilozano.proyecto_dam_daw_2.validations.userLoginValidations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ilozano.proyecto_dam_daw_2.model.Usuario;
import org.ilozano.proyecto_dam_daw_2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CuentaUsuarioValidator implements ConstraintValidator<CuentaUsuario, Usuario> {

    @Autowired
    private UsuarioRepository usuarioRepository;



    public boolean isValid(Usuario usuario, ConstraintValidatorContext context) {
        String email = usuario.getEmail();
        String clave = usuario.getClave();

        Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByEmailAndClave(email, clave);
        System.out.println(usuario.getEmail());
        System.out.println(usuario.getClave());

        return usuarioOptional.isPresent();
    }


//    public boolean isValid(String clave, ConstraintValidatorContext context) {
//        Usuario usuario = usuarioRepository.findByClave(clave);
//        System.out.println(usuario);
//        return usuario != null;
//    }


}
