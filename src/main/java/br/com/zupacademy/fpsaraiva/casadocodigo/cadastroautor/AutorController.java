package br.com.zupacademy.fpsaraiva.casadocodigo.cadastroautor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping(value="/api/autor")
    public ResponseEntity<?> criarAutor(@Valid @RequestBody AutorRequest request,
                                        UriComponentsBuilder uriComponentsBuilder) {
        Autor novoAutor = request.criarAutor();
        autorRepository.save(novoAutor);

        return ResponseEntity.ok(uriComponentsBuilder
                .path("/cartoes/{id}/aviso")
                .buildAndExpand(novoAutor.getId())
                .toUri());
    }
}
