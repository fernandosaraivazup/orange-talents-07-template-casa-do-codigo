package br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado;

import br.com.zupacademy.fpsaraiva.casadocodigo.cliente.ClienteFormRequest;
import br.com.zupacademy.fpsaraiva.casadocodigo.estado.Estado;
import br.com.zupacademy.fpsaraiva.casadocodigo.pais.Pais;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Component
public class EstadoPertenceAPaisValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteFormRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        ClienteFormRequest request = (ClienteFormRequest) target;

        Pais pais = manager.find(Pais.class, request.getIdPais());
        Estado estado = manager.find(Estado.class, request.getIdEstado());

        if(!estado.pertenceAPais(pais)) {
            errors.rejectValue("idEstado", null, "Estado não pertence ao país selecionado.");
        }
    }

}