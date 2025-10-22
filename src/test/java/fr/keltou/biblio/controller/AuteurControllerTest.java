package fr.keltou.biblio.controller;

import fr.keltou.biblio.exception.GlobalExceptionHandler;
import fr.keltou.biblio.exception.BadRequestException;
import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.Auteur;
import fr.keltou.biblio.service.AuteurService;
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

@WebMvcTest(AuteurController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class AuteurControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean AuteurService service;

    @Test
    void getAll_returns_ok() throws Exception {
        Mockito.when(service.getAll()).thenReturn(List.of(new Auteur("A","B")));
        mockMvc.perform(get("/api/auteurs")).andExpect(status().isOk());
    }

    @Test
    void get_returns_404_when_not_found() throws Exception {
        Mockito.when(service.getById(42L)).thenThrow(new ResourceNotFoundException("not found"));
        mockMvc.perform(get("/api/auteurs/42")).andExpect(status().isNotFound());
    }

    @Test
    void update_returns_ok() throws Exception {
        Mockito.when(service.update(Mockito.eq(1L), Mockito.any(Auteur.class))).thenReturn(new Auteur("A","B"));
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/auteurs/1")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content("{\"prenom\":\"A\",\"nom\":\"B\"}"))
            .andExpect(status().isOk());
    }

    @Test
    void delete_returns_204() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/auteurs/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void create_returns_400_when_bad_request() throws Exception {
        Mockito.when(service.create(Mockito.any(Auteur.class))).thenThrow(new BadRequestException("invalid"));
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/auteurs")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .content("{\"prenom\":\"A\"}"))
            .andExpect(status().isBadRequest());
    }

    /*
    @Test
    void should_fail_intentionally() throws Exception {
        // Prépare un retour OK côté service pour que l'endpoint réponde 200 
        Mockito.when(service.getAll()).thenReturn(List.of(new Auteur("A","B")));

        // On attend 418 (I'm a teapot) -> le test va forcément échouer (la route renverra 200)
        mockMvc.perform(get("/api/auteurs"))
               .andExpect(status().isIAmATeapot());
    }
    */
    
}
