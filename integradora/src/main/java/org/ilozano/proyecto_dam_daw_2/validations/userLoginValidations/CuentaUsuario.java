package org.ilozano.proyecto_dam_daw_2.validations.userLoginValidations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(value = {ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CuentaUsuarioValidator.class)
public @interface CuentaUsuario {
    String message() default "{CuentaUsuario.usuario.clave}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
