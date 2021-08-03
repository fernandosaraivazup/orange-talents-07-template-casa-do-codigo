package br.com.zupacademy.fpsaraiva.casadocodigo.pais;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class PaisController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value="/api/pais")
    @Transactional
    public String criarPais(@RequestBody @Valid PaisFormRequest form) {
        Pais novoPais = form.criaPais(entityManager);
        entityManager.persist(novoPais);

        return novoPais.toString();
    }

}