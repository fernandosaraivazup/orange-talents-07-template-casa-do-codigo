package br.com.zupacademy.fpsaraiva.casadocodigo.cadastroautor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AutorRepository autorRepository;

    @DisplayName("Deve retornar status 200 e cadastrar autor no banco")
    @Test
    void deveRetornarStatus200ECadastrarAutorNoBanco() throws Exception {
        //cenario
        URI uri = new URI("/api/autor");
        String json = "{\"nome\":\"Fernando\",\"email\":\"fernando@email.com\",\"descricao\":\"Teste descricao\"}";

        //acao
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Optional<Autor> novoAutorCriado = autorRepository.findById(1L);

        //validacao
        assertTrue(novoAutorCriado.isPresent());
        assertEquals("Fernando", novoAutorCriado.get().getNome());
        assertEquals("fernando@email.com", novoAutorCriado.get().getEmail());
        assertEquals("Teste descricao", novoAutorCriado.get().getDescricao());
    }

    @DisplayName("Deve retornar status 400 ao tentar cadastrar autor com nome inválido")
    @Test
    void deveRetornarStatus400AoTentarCadastrarAutorComNomeInvalido() throws Exception {
        URI uri = new URI("/api/autor");
        String json = "{\"nome\":\"\",\"email\":\"fernando@email.com\",\"descricao\":\"Teste descricao\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @DisplayName("Deve retornar status 400 ao tentar cadastrar autor com e-mail incorreto")
    @Test
    void deveRetornarStatus400AoTentarCadastrarAutorComEmailIncorreto() throws Exception {
        URI uri = new URI("/api/autor");
        String json = "{\"nome\":\"Fernando\",\"email\":\"fernandoemail.com\",\"descricao\":\"Teste descricao\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @DisplayName("Deve retornar status 400 ao tentar cadastrar autor com descrição inválida")
    @Test
    void deveRetornarStatus400AoTentarCadastrarAutorComDescricaoInvalida() throws Exception {
        URI uri = new URI("/api/autor");
        String json = "{\"nome\":\"\",\"email\":\"fernando@email.com\",\"descricao\":\"\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @DisplayName("Deve retornar status 400 ao tentar cadastrar autor com email duplicado")
    @Test
    public void deveRetornarStatus4000AoTentarCadastrarAutorComEmailDuplicado() throws Exception {
        this.autorRepository.deleteAll();
        this.autorRepository.save(new Autor("Autor", "autor@email.com", "Teste descricao"));

        URI uri = new URI("/api/autor");
        String json = "{\"nome\":\"Autor\", \"email\":\"autor@email.com\", \"descricao\": \"Teste descricao\"}";

        mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
}
