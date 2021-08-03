package br.com.zupacademy.fpsaraiva.casadocodigo.detalhelivro;

import br.com.zupacademy.fpsaraiva.casadocodigo.livro.Livro;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class DetalheLivroDTOResponse {

    @NotBlank
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
    private String isbn;
    @NotBlank
    private String dataPublicacao;
    @NotNull
    private DetalheAutorLivroDTOResponse autor;
    @NotNull
    private DetalheCategoriaLivroDTOResponse categoria;

    public DetalheLivroDTOResponse(Livro livro) {
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao()
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.autor = new DetalheAutorLivroDTOResponse(livro.getAutor());
        this.categoria = new DetalheCategoriaLivroDTOResponse(livro.getCategoria());
    }

    //Sem os getters, não é encontrada uma representação de resposta aceitável (erro 406)
    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public DetalheAutorLivroDTOResponse getAutor() {
        return autor;
    }

    public DetalheCategoriaLivroDTOResponse getCategoria() {
        return categoria;
    }

}