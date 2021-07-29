package br.com.zupacademy.fpsaraiva.casadocodigo.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorFormRequest {

    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    @NotBlank
    @Size(max = 400)
    private String descricao;

    public AutorFormRequest(@NotBlank String nome,  @NotBlank @Email String email,  @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor criarAutor() {
        return new Autor(this.nome, this.email, this.descricao);
    }

    public String getEmail() {
        return email;
    }
}
