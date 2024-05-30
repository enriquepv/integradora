package org.ilozano.proyecto_dam_daw_2.validations.userLoginValidations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Target(value = {ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExisteUsuarioValidator.class)
public @interface ExisteUsuario {
    String message() default "{ExisteUsuario.usuario.email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
