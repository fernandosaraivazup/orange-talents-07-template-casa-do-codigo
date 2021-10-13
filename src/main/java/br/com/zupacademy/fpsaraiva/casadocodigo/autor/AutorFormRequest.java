package br.com.zupacademy.fpsaraiva.casadocodigo.autor;

import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.ValorUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorFormRequest {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    @ValorUnico(domainClass = Autor.class, fieldName = "email", message="E-mail de autor duplicado!")
    private String email;
    @NotBlank
    @Size(max = 400)
    private String descricao;

    public AutorFormRequest(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor criarAutor() {
        return new Autor(nome, email, descricao);
    }
}
