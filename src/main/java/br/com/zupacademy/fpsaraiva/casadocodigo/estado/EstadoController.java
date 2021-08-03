package br.com.zupacademy.fpsaraiva.casadocodigo.estado;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class EstadoController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value="/api/estado")
    @Transactional
    public String criarEstado(@RequestBody @Valid EstadoFormRequest form) {
        Estado novoEstado = form.criarEstado(entityManager);
        entityManager.persist(novoEstado);

        return novoEstado.toString();
    }

}