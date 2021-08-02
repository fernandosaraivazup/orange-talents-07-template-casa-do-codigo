package br.com.zupacademy.fpsaraiva.casadocodigo.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class LivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping(value="/api/livro")
    @Transactional
    public String criarLivro(@RequestBody @Valid LivroFormRequest form) {
        Livro novoLivro = form.criaLivro(entityManager);
        entityManager.persist(novoLivro);

        return novoLivro.toString();
    }

    @GetMapping(value="/api/livro")
    public List<LivroDTOResponse> listarLivros(LivroDTOResponse livroDTOResponse) {
        List<Livro> livrosCadastrados = livroRepository.findAll();

        return LivroDTOResponse.converterEmResposta(livrosCadastrados);
    }
}
