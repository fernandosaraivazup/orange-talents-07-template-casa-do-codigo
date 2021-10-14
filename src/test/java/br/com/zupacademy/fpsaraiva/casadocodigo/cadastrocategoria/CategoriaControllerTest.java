package br.com.zupacademy.fpsaraiva.casadocodigo.cadastrocategoria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
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
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @DisplayName("Deve retornar status 200 e cadastrar categoria no banco")
    @Test
    void deveRetornarStatus200ECadastrarCategoriaNoBanco() throws Exception {
        URI uri = new URI("/api/categoria");
        String json = "{\"nome\":\"Categoria 1\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Optional<Categoria> novaCategoriaCriada = categoriaRepository.findById(1L);

        assertTrue(novaCategoriaCriada.isPresent());
        assertEquals("Categoria 1", novaCategoriaCriada.get().getNome());
    }

    @DisplayName("Deve retornar status 400 ao tentar cadastrar categoria com nome em branco")
    @Test
    void deveRetornarStatus400AoTentarCadastrarCategoriaComNomeEmBranco() throws Exception {
        URI uri = new URI("/api/categoria");
        String json = "{\"nome\":\"\"}";

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

//    @DisplayName("Deve retornar status 400 ao tentar cadastrar categoria com nome duplicado")
//    @Test
//    @Order(3)
//    public void deveRetornarStatus400AoTentarCadastrarCategoriaComNomeDuplicado() throws Exception {
//        this.categoriaRepository.deleteAll();
//        this.categoriaRepository.save(new Categoria("Categoria 1"));
//
//        URI uri = new URI("/api/categoria");
//        String json = "{\"nome\":\"Categoria 1\"}";
//
//        mockMvc.perform(MockMvcRequestBuilders.post(uri)
//                        .content(json)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().is(400));
//    }
}
