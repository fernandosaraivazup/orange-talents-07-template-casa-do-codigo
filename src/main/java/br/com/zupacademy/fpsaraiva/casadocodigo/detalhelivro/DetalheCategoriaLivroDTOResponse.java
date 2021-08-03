package br.com.zupacademy.fpsaraiva.casadocodigo.detalhelivro;

import br.com.zupacademy.fpsaraiva.casadocodigo.categoria.Categoria;

import javax.validation.constraints.NotBlank;

public class DetalheCategoriaLivroDTOResponse {

    @NotBlank
    private String nome;

    public DetalheCategoriaLivroDTOResponse(Categoria categoria) {
        this.nome = categoria.getNome();
    }

    //Sem este getter, ocorre um erro de serialização da resposta no formato JSON
    public String getNome() {
        return nome;
    }
}
