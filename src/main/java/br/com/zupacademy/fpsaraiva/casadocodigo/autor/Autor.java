package br.com.zupacademy.fpsaraiva.casadocodigo.autor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    @Column(length = 400)
    @Size(max = 400)
    private String descricao;
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    public Autor(@NotBlank String nome, @NotBlank @Email String email, @Size(max = 400) String descricao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Autor{" +
                " nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
