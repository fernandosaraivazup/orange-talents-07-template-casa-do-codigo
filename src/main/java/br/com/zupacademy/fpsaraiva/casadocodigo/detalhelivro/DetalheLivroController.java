package br.com.zupacademy.fpsaraiva.casadocodigo.detalhelivro;

import br.com.zupacademy.fpsaraiva.casadocodigo.livro.Livro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
public class DetalheLivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping(value="/api/livro/{id}")
    public ResponseEntity<?> listarDetalheLivro(@PathVariable("id") Long id) {
        Livro livroBuscado = entityManager.find(Livro.class, id);
        if(livroBuscado == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }

        DetalheLivroDTOResponse detalheLivroDTOResponse = new DetalheLivroDTOResponse(livroBuscado);

        return ResponseEntity.ok(detalheLivroDTOResponse);
    }

}