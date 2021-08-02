package br.com.zupacademy.fpsaraiva.casadocodigo.livro;

import br.com.zupacademy.fpsaraiva.casadocodigo.autor.Autor;
import br.com.zupacademy.fpsaraiva.casadocodigo.categoria.Categoria;
import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.ExisteId;
import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.ValorUnico;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroFormRequest {

    @NotBlank
    @ValorUnico(domainClass = Livro.class, fieldName = "titulo", message = "Foi encontrado na base um livro com o mesmo título informado.")
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String resumo;
    @NotBlank
    private String sumario;
    @NotNull
    @Min(20)
    private BigDecimal preco;
    @Min(100)
    private int numeroPaginas;
    @NotBlank
    @ValorUnico(domainClass = Livro.class, fieldName = "isbn", message = "Foi encontrado na base um livro com o mesmo ISBN informado.")
    private String isbn;
    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;
    @NotNull
    @ExisteId(domainClass = Categoria.class, fieldName = "id", message = "A categoria informada não está cadastrada na base de dados.")
    private Long idCategoria;
    @NotNull
    @ExisteId(domainClass = Autor.class, fieldName = "id", message = "O autor informado não está cadastrado na base de dados.")
    private Long idAutor;

    public LivroFormRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
                            @NotNull @Min(20) BigDecimal preco, @Min(100) int numeroPaginas, @NotBlank String isbn,
                            @NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    /*
    Jackson não desserializa JSON com a data no parâmetro pelo construtor.
    Este setter é responsável por contornar essa situação.
    */

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Livro criaLivro(EntityManager entityManager) {
        Autor autor = entityManager.find(Autor.class, idAutor);
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);

        return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, autor, categoria);
    }
}
