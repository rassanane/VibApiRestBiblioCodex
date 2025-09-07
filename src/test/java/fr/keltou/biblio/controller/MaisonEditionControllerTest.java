package fr.keltou.biblio.controller;

import fr.keltou.biblio.exception.GlobalExceptionHandler;
import fr.keltou.biblio.exception.BadRequestException;
import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.MaisonEdition;
import fr.keltou.biblio.service.MaisonEditionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MaisonEditionController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class MaisonEditionControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean MaisonEditionService service;

    @Test
    void getAll_returns_ok() throws Exception {
        Mockito.when(service.getAll()).thenReturn(List.of(new MaisonEdition("M")));
        mockMvc.perform(get("/api/maisons-edition")).andExpect(status().isOk());
    }

    @Test
    void get_returns_404_when_not_found() throws Exception {
        Mockito.when(service.getById(7L)).thenThrow(new ResourceNotFoundException("not found"));
        mockMvc.perform(get("/api/maisons-edition/7")).andExpect(status().isNotFound());
    }

    @Test
    void create_returns_400_when_bad_request() throws Exception {
        Mockito.when(service.create(Mockito.any(MaisonEdition.class))).thenThrow(new BadRequestException("invalid"));
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/maisons-edition")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"M\"}"))
            .andExpect(status().isBadRequest());
    }
}
