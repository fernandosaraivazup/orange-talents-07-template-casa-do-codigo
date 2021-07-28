package br.com.zupacademy.fpsaraiva.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping(value="/api/autor")
    @Transactional
    public String criarUsuario(@Valid @RequestBody AutorFormRequest form) {
        Autor novoAutor = form.criarAutor();
        System.out.println(novoAutor);
        autorRepository.save(novoAutor);
    }
}
