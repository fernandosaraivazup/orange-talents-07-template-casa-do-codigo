package br.com.zupacademy.fpsaraiva.casadocodigo.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SemNomeDeCategoriaDuplicadoValidator implements Validator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoriaFormRequest.class.isAssignableFrom((clazz));
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        CategoriaFormRequest request = (CategoriaFormRequest) target;
        Optional<Categoria> possivelCategoria = categoriaRepository.findByNome(request.getNome());

        if(possivelCategoria.isPresent()) {
            errors.rejectValue("nome", "400", "Já existe uma categoria cadastrada com o nome informado: " + request.getNome());
        }
    }
}
