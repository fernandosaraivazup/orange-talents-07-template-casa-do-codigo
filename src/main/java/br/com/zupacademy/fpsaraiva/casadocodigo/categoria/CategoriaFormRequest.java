package br.com.zupacademy.fpsaraiva.casadocodigo.categoria;

import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.ValorUnico;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CategoriaFormRequest {

    @NotBlank
    @ValorUnico(domainClass = Categoria.class, fieldName = "nome", message="Nome de categoria duplicado!")
    private String nome;

    public CategoriaFormRequest(@JsonProperty("nome") @NotBlank String nome) { this.nome = nome; }

    public Categoria criarCategoria() { return new Categoria(nome); }

    public String getNome() { return nome; }
}
