package fr.keltou.biblio.controller;

import fr.keltou.biblio.model.Utilisateur;
import fr.keltou.biblio.service.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Import;
import fr.keltou.biblio.exception.GlobalExceptionHandler;
import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.exception.BadRequestException;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UtilisateurController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class UtilisateurControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean UtilisateurService service;

    @Test
    void getAll_returns_ok() throws Exception {
        Mockito.when(service.getAll()).thenReturn(List.of(new Utilisateur("n","p","id","pwd")));
        mockMvc.perform(get("/api/utilisateurs")).andExpect(status().isOk());
    }

    @Test
    void create_returns_ok() throws Exception {
        Mockito.when(service.create(Mockito.any(Utilisateur.class))).thenReturn(new Utilisateur("n","p","id","pwd"));
        mockMvc.perform(post("/api/utilisateurs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"n\",\"prenom\":\"p\",\"identifiant\":\"id\",\"motPasse\":\"pwd\"}"))
            .andExpect(status().isOk());
    }

    @Test
    void get_returns_404_when_not_found() throws Exception {
        Mockito.when(service.getById(12L)).thenThrow(new ResourceNotFoundException("not found"));
        mockMvc.perform(get("/api/utilisateurs/12")).andExpect(status().isNotFound());
    }

    @Test
    void update_returns_ok() throws Exception {
        Mockito.when(service.update(Mockito.eq(1L), Mockito.any(Utilisateur.class))).thenReturn(new Utilisateur("n","p","id","pwd"));
        mockMvc.perform(put("/api/utilisateurs/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"n\",\"prenom\":\"p\",\"identifiant\":\"id\",\"motPasse\":\"pwd\"}"))
            .andExpect(status().isOk());
    }

    @Test
    void delete_returns_204() throws Exception {
        mockMvc.perform(delete("/api/utilisateurs/1")).andExpect(status().isNoContent());
    }

    @Test
    void create_returns_400_when_bad_request() throws Exception {
        Mockito.when(service.create(Mockito.any(Utilisateur.class))).thenThrow(new BadRequestException("invalid"));
        mockMvc.perform(post("/api/utilisateurs")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"n\"}"))
            .andExpect(status().isBadRequest());
    }
}
