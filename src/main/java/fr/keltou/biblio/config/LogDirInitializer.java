package fr.keltou.biblio.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class LogDirInitializer implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(LogDirInitializer.class);

    @Override
    public void run(ApplicationArguments args) {
        try {
            Path logDir = Path.of("logs");
            if (Files.notExists(logDir)) {
                Files.createDirectories(logDir);
                log.info("Dossier des logs créé: {}", logDir.toAbsolutePath());
            }
        } catch (Exception e) {
            log.warn("Impossible de créer le dossier des logs", e);
        }
    }
}

