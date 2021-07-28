package br.com.zupacademy.fpsaraiva.casadocodigo.autor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorFormRequest {

    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    @Column(length = 400)
    @Size(max = 400)
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public Autor criarAutor() {
        return new Autor(nome, email, descricao);
    }
}
