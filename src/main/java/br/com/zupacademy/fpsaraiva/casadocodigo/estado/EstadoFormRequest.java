package br.com.zupacademy.fpsaraiva.casadocodigo.estado;

import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.ExisteId;
import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.ValorUnico;
import br.com.zupacademy.fpsaraiva.casadocodigo.pais.Pais;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoFormRequest {

    @NotBlank
    @ValorUnico(domainClass = Estado.class, fieldName = "nome", message = "Foi encontrado na base um estado com o mesmo nome informado.")
    private String nome;
    @NotNull
    @ExisteId(domainClass = Pais.class, fieldName = "id", message = "O id de país informado não existe na base.")
    private Long idPais;

    public EstadoFormRequest(String nome, Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public Estado criarEstado(EntityManager entityManager) {
        return new Estado(nome, entityManager.find(Pais.class, idPais));
    }

}