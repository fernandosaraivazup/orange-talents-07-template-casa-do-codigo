package br.com.zupacademy.fpsaraiva.casadocodigo.cliente;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class ClienteController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping(value="/api/cliente")
    @Transactional
    public String criarCliente(@Valid @RequestBody ClienteFormRequest form) {
        Cliente novoCliente = form.criarCliente(entityManager);
        entityManager.persist(novoCliente);
        //return "Endpoint de cliente UP!";
        return novoCliente.toString();
    }
}