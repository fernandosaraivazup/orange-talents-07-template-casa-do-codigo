package br.com.zupacademy.fpsaraiva.casadocodigo.livro;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class LivroDTOResponse {

    @Autowired
    private LivroRepository livroRepository;
    @NotNull
    private Long id;
    @NotBlank
    private String titulo;

    public LivroDTOResponse(@NotNull Long id, @NotBlank String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    //Sem estes 2 getters, ocorre um erro de serialização da resposta no formato JSON
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public static List<LivroDTOResponse> converterEmResposta(List<Livro> livros) {
        return livros.stream().map(livro -> new LivroDTOResponse(livro.getId(), livro.getTitulo())).collect(Collectors.toList());
    }

}