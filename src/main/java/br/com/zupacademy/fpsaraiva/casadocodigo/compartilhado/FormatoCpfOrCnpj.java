package br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)

public @interface FormatoCpfOrCnpj {

    String message() default "Documento informado é inválido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}