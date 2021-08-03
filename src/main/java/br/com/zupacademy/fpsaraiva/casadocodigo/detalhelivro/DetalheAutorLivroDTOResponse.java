package br.com.zupacademy.fpsaraiva.casadocodigo.detalhelivro;

import br.com.zupacademy.fpsaraiva.casadocodigo.autor.Autor;

import javax.validation.constraints.NotBlank;

public class DetalheAutorLivroDTOResponse {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public DetalheAutorLivroDTOResponse(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    //Sem estes 2 getters, ocorre um erro de serialização da resposta no formato JSON
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
