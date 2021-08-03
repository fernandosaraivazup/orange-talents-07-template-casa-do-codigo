package br.com.zupacademy.fpsaraiva.casadocodigo.cliente;

import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.FormatoCpfOrCnpj;
import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.ExisteId;
import br.com.zupacademy.fpsaraiva.casadocodigo.compartilhado.ValorUnico;
import br.com.zupacademy.fpsaraiva.casadocodigo.estado.Estado;
import br.com.zupacademy.fpsaraiva.casadocodigo.pais.Pais;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteFormRequest {

    @NotBlank
    @Email
    @ValorUnico(domainClass = Cliente.class, fieldName = "email", message="Endereço de e-mail duplicado!")
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    @ValorUnico(domainClass = Cliente.class, fieldName = "documento", message="Documento duplicado!")
    @FormatoCpfOrCnpj
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull
    @ExisteId(domainClass = Pais.class, fieldName = "id", message = "O id de país informado não existe na base.")
    private Long idPais;
    @ExisteId(domainClass = Estado.class, fieldName = "id", message = "O id de estado informado não existe na base.")
    private Long idEstado;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;

    public ClienteFormRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
                              @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
                              @NotBlank String cidade, @NotNull Long idPais, Long idEstado, @NotBlank String telefone,
                              @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Cliente criarCliente(EntityManager entityManager) {
        return new Cliente(email, nome, sobrenome, documento, endereco, complemento, cidade, cep, telefone,
                entityManager.find(Pais.class, idPais), entityManager.find(Estado.class, idEstado));
    }

}