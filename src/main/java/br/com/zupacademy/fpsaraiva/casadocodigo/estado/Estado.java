package br.com.zupacademy.fpsaraiva.casadocodigo.estado;

import br.com.zupacademy.fpsaraiva.casadocodigo.pais.Pais;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @ManyToOne
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(@NotBlank String nome, @NotNull Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    //Para fins de desenvolvimento apenas
    @Override
    public String toString() {
        return "Estado{" +
                "nome='" + nome + '\'' +
                ", pais=" + pais +
                '}';
    }

}