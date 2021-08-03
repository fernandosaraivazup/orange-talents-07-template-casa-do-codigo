package br.com.zupacademy.fpsaraiva.casadocodigo.pais;

import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.ValorUnico;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class PaisFormRequest {

    @NotBlank
    @ValorUnico(domainClass = Pais.class, fieldName = "nome", message = "Foi encontrado na base um país com o mesmo nome informado.")
    private String nome;

    @Deprecated
    public PaisFormRequest() {
    }

    public PaisFormRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public Pais criaPais(EntityManager entityManager) {
        return new Pais(nome);
    }

    public String getNome() {
        return nome;
    }

}