package br.com.zupacademy.fpsaraiva.casadocodigo.autor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SemEmailDoAutorDuplicado implements Validator {

    private final AutorRepository autorRepository;

    public SemEmailDoAutorDuplicado(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AutorFormRequest.class.isAssignableFrom((clazz));
    }

    @Override
    public void validate(Object target, Errors errors) {
        AutorFormRequest request = (AutorFormRequest) target;
        Optional<Autor> possivelEmail = autorRepository.findByEmail(request.getEmail());

        if(possivelEmail.isPresent()) {
            errors.rejectValue("email", "400", "Já existe um autor cadastrado com o e-mail informado: " + request.getEmail());
        }
    }
}
