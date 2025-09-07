package fr.keltou.biblio.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test void not_found_returns_404() {
        ResponseEntity<Map<String,Object>> resp = handler.handleNotFound(new ResourceNotFoundException("x"));
        assertEquals(404, resp.getStatusCode().value());
        assertTrue(resp.getBody().get("error").toString().contains("x"));
    }

    @Test void bad_request_returns_400() {
        ResponseEntity<Map<String,Object>> resp = handler.handleBadRequest(new BadRequestException("bad"));
        assertEquals(400, resp.getStatusCode().value());
    }

    @Test void other_returns_500() {
        ResponseEntity<Map<String,Object>> resp = handler.handleOther(new RuntimeException("boom"));
        assertEquals(500, resp.getStatusCode().value());
    }
}

