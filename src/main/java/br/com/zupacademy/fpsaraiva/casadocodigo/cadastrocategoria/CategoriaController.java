package br.com.zupacademy.fpsaraiva.casadocodigo.cadastrocategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping(value="/api/categoria")
    public ResponseEntity<?>  criarCategoria(@Valid @RequestBody CategoriaRequest form,
                                              UriComponentsBuilder uriComponentsBuilder) {
        Categoria novaCategoria = form.criarCategoria();
        categoriaRepository.save(novaCategoria);

        return ResponseEntity.ok(uriComponentsBuilder
                .path("/api/categoria/{id}")
                .buildAndExpand(novaCategoria.getId())
                .toUri());
    }
}
