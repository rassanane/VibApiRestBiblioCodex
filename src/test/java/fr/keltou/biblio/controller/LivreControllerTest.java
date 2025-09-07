package fr.keltou.biblio.controller;

import fr.keltou.biblio.exception.GlobalExceptionHandler;
import fr.keltou.biblio.exception.BadRequestException;
import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.Livre;
import fr.keltou.biblio.service.LivreService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LivreController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class LivreControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean LivreService service;

    @Test
    void getAll_returns_ok() throws Exception {
        Mockito.when(service.getAll()).thenReturn(List.of(new Livre("T", LocalDate.now())));
        mockMvc.perform(get("/api/livres")).andExpect(status().isOk());
    }

    @Test
    void create_returns_ok() throws Exception {
        Mockito.when(service.create(Mockito.any(Livre.class))).thenReturn(new Livre("T", LocalDate.now()));
        mockMvc.perform(post("/api/livres")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"titre\":\"T\"}"))
            .andExpect(status().isOk());
    }

    @Test
    void get_returns_404_when_not_found() throws Exception {
        Mockito.when(service.getById(9L)).thenThrow(new ResourceNotFoundException("not found"));
        mockMvc.perform(get("/api/livres/9")).andExpect(status().isNotFound());
    }

    @Test
    void update_returns_ok() throws Exception {
        Mockito.when(service.update(Mockito.eq(1L), Mockito.any(Livre.class))).thenReturn(new Livre("T2", LocalDate.now()));
        mockMvc.perform(put("/api/livres/1").contentType(MediaType.APPLICATION_JSON).content("{\"titre\":\"T2\"}"))
            .andExpect(status().isOk());
    }

    @Test
    void delete_returns_204() throws Exception {
        mockMvc.perform(delete("/api/livres/1")).andExpect(status().isNoContent());
    }

    @Test
    void create_returns_400_when_bad_request() throws Exception {
        Mockito.when(service.create(Mockito.any(Livre.class))).thenThrow(new BadRequestException("invalid"));
        mockMvc.perform(post("/api/livres").contentType(MediaType.APPLICATION_JSON).content("{\"titre\":\"T\"}"))
            .andExpect(status().isBadRequest());
    }
}
