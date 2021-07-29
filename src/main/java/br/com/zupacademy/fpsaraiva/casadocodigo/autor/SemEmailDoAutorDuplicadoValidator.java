package br.com.zupacademy.fpsaraiva.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SemEmailDoAutorDuplicadoValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AutorFormRequest.class.isAssignableFrom((clazz));
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        AutorFormRequest request = (AutorFormRequest) target;
        Optional<Autor> possivelAutor = autorRepository.findByEmail(request.getEmail());

        if(possivelAutor.isPresent()) {
            errors.rejectValue("email", "400", "Já existe um autor cadastrado com o e-mail informado: " + request.getEmail());
        }
    }
}
