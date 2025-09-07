package fr.keltou.biblio.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long start = System.currentTimeMillis();
        String rid = UUID.randomUUID().toString();

        try {
            MDC.put("rid", rid);
            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                MDC.put("user", principal.getName());
            }

            filterChain.doFilter(request, response);
        } finally {
            long duration = System.currentTimeMillis() - start;
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String query = request.getQueryString();
            String fullPath = query != null ? uri + "?" + query : uri;
            int status = response.getStatus();
            String ip = request.getRemoteAddr();

            log.info("{} {} -> status={} duration={}ms ip={}", method, fullPath, status, duration, ip);

            MDC.clear();
        }
    }
}

