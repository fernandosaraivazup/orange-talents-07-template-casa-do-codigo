package br.com.zupacademy.fpsaraiva.casadocodigo.cadastrocategoria;

import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.ValorUnico;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @ValorUnico(domainClass = Categoria.class, fieldName = "nome", message="Nome de categoria duplicado!")
    private String nome;

    public CategoriaRequest(@JsonProperty("nome") @NotBlank String nome) { this.nome = nome; }

    public Categoria criarCategoria() { return new Categoria(nome); }

    public String getNome() { return nome; }
}
