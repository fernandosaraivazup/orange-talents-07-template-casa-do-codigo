package br.com.zupacademy.fpsaraiva.casadocodigo.livro;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class LivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value="/api/livro")
    @Transactional
    public String criarLivro(@RequestBody @Valid LivroFormRequest form) {
        Livro novoLivro = form.criaLivro(entityManager);
        entityManager.persist(novoLivro);

        return novoLivro.toString();
    }
}
