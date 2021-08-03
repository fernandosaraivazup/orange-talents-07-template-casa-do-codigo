package br.com.zupacademy.fpsaraiva.casadocodigo.cliente;

import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.EstadoPertenceAPaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

    @Autowired
    private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(estadoPertenceAPaisValidator);
    }

    @PostMapping(value="/api/cliente")
    @Transactional
    public String criarCliente(@Valid @RequestBody ClienteFormRequest form) {
        Cliente novoCliente = form.criarCliente(entityManager);
        entityManager.persist(novoCliente);

        return novoCliente.toString();
    }

}