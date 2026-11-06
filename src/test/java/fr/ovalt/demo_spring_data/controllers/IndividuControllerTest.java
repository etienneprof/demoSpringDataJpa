package fr.ovalt.demo_spring_data.controllers;

import fr.ovalt.demo_spring_data.entities.Individu;
import fr.ovalt.demo_spring_data.repositories.IndividuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndividuController.class)
@AutoConfigureMockMvc(addFilters = false)
class IndividuControllerTest {
    @MockitoBean
    IndividuRepository repositoryMock;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void insert() throws Exception {
        Individu ind1 = Individu.builder().prenom("Etienne").build();
        mockMvc.perform(post("/individus")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(ind1)))
                            .andExpect(status().isOk());

        verify(repositoryMock, times(1)).save(any(Individu.class));
    }

    @Test
    void getAll() throws Exception {
        Individu ind1 = Individu.builder().id(1).prenom("Etienne").build();
        Individu ind2 = Individu.builder().id(2).prenom("Thomas").build();

        var individus = List.of(ind1, ind2);

        when(repositoryMock.findAll()).thenReturn(individus);

        mockMvc.perform(get("/individus"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(individus)));
    }

    @Test
    void findByPrenom() {
    }
}