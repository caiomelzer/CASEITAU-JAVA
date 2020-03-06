package com.melzer.caseitau.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private int idUsuarioCriado = 1;

    @Before
    public void init() throws Exception {
        MvcResult result = mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"cpf\": \"1.2.345.678.901\",\n" +
                        "  \"email\": \"caio@geda\",\n" +
                        "  \"nomeCompleto\": \"Caio Melzer\",\n" +
                        "}")) 
                .andReturn();
        idUsuarioCriado = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
    }

    @Test
    public void quandoSucessoNaCriacaoUsuario() throws Exception {
        mockMvc.perform(post("/usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"cpf\": \"1.2.345.678.901\",\n" +
                        "  \"email\": \"caio@geda\",\n" +
                        "  \"nomeCompleto\": \"Caio Melzer\",\n" +
                        "}")) 
                .andDo(print())
                .andExpect(jsonPath("$.nomeCompleto", is("Caio Melzer")))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().isCreated());
    }

    @Test
    public void quandoSucessoNaEdicaoUsuario() throws Exception {
        mockMvc.perform(put("/usuario" + idUsuarioCriado)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"cpf\": \"1.2.345.678.901\",\n" +
                        "  \"email\": \"caio@geda\",\n" +
                        "  \"nomeCompleto\": \"Caio Melzer\",\n" +
                        "}")) 
                .andDo(print())
                .andExpect(jsonPath("$.nomeCompleto", is("Ameno Dorime")))
                .andExpect(jsonPath("$.cpf", is("4.7.932.697.04")))
                .andExpect(jsonPath("$.email", is("dorime@gmail.com")))
                .andExpect(status().isCreated());
    }

    @Test
    public void quandoUsuarioNaoExiste() throws Exception {
        mockMvc.perform(put("/usuario/" + 2313214)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"cpf\": \"1.2.345.678.901\",\n" +
                        "  \"email\": \"caio@geda\",\n" +
                        "  \"nomeCompleto\": \"Caio Melzer\",\n" +
                        "}")) 
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void allUsuarios() throws Exception {
        mockMvc.perform(get("/usuario"))
                .andDo(print())
                .andExpect(jsonPath("$.[0].nome", is("Caio Melzer")))
                .andExpect(status().isAccepted());
    }

    @Test
    public void sucessoNoCadastroDoUsuario() throws Exception {
        mockMvc.perform(get("/usuario" + idUsuarioCriado))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(idUsuarioCriado)))
                .andExpect(jsonPath("$.nome", is("Caio Melzer")))
                .andExpect(jsonPath("$.cpf", is("1.2.345.678.901")))
                .andExpect(jsonPath("$.email", is("caio@geda")))
                .andExpect(status().isCreated());
    }

    @Test
    public void usuarioInexist() throws Exception {
        mockMvc.perform(get("/usuario" + 83249))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
