package fr.keltou.biblio.controller;

import fr.keltou.biblio.service.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean UtilisateurService utilisateurService;

    @Test
    void login_returns_authenticated_true_when_ok() throws Exception {
        Mockito.when(utilisateurService.authenticate("john","pwd")).thenReturn(true);
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"identifiant\":\"john\",\"motPasse\":\"pwd\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.authenticated").value(true));
    }

    @Test
    void login_returns_authenticated_false_when_ko() throws Exception {
        Mockito.when(utilisateurService.authenticate("john","bad")).thenReturn(false);
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"identifiant\":\"john\",\"motPasse\":\"bad\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.authenticated").value(false));
    }
}

