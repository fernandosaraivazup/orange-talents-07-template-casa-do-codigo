package br.com.zupacademy.fpsaraiva.casadocodigo.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping(value="/api/categoria")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void criarCategoria(@Valid @RequestBody CategoriaFormRequest form) {
        Categoria novaCategoria = form.criarCategoria();
        categoriaRepository.save(novaCategoria);
    }
}
