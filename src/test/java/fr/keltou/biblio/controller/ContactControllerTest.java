package fr.keltou.biblio.controller;

import fr.keltou.biblio.exception.GlobalExceptionHandler;
import fr.keltou.biblio.exception.BadRequestException;
import fr.keltou.biblio.exception.ResourceNotFoundException;
import fr.keltou.biblio.model.Contact;
import fr.keltou.biblio.service.ContactService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContactController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class ContactControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean ContactService service;

    @Test
    void list_returns_ok() throws Exception {
        Mockito.when(service.list()).thenReturn(List.of(new Contact("n","e","m")));
        mockMvc.perform(get("/api/contacts")).andExpect(status().isOk());
    }

    @Test
    void add_returns_ok() throws Exception {
        Mockito.when(service.add(Mockito.any(Contact.class))).thenReturn(new Contact("n","e","m"));
        mockMvc.perform(post("/api/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nom\":\"n\",\"email\":\"e\",\"message\":\"m\"}"))
            .andExpect(status().isOk());
    }

    @Test
    void get_returns_404_when_not_found() throws Exception {
        Mockito.when(service.get(5L)).thenThrow(new ResourceNotFoundException("not found"));
        mockMvc.perform(get("/api/contacts/5")).andExpect(status().isNotFound());
    }

    @Test
    void delete_returns_204() throws Exception {
        mockMvc.perform(delete("/api/contacts/1")).andExpect(status().isNoContent());
    }

    @Test
    void add_returns_400_when_bad_request() throws Exception {
        Mockito.when(service.add(Mockito.any(Contact.class))).thenThrow(new BadRequestException("invalid"));
        mockMvc.perform(post("/api/contacts").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"e\"}"))
            .andExpect(status().isBadRequest());
    }
}
