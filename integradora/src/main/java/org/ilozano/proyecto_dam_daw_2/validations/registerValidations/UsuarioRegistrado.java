package org.ilozano.proyecto_dam_daw_2.validations.registerValidations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Target(value = {ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsuarioRegistradoValidator.class)
public @interface UsuarioRegistrado {
    String message() default "{UsuarioRegistrado.usuario.email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
