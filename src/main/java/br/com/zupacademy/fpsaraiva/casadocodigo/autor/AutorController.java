package br.com.zupacademy.fpsaraiva.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping(value="/api/autor")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void criarAutor(@Valid @RequestBody AutorFormRequest form) {
        Autor novoAutor = form.criarAutor();
        autorRepository.save(novoAutor);
    }
}
