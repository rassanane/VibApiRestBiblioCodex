package fr.keltou.biblio.utils;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {
    @Test void parseIso_valid_and_invalid() {
        LocalDate d = DateUtils.parseIso("2024-01-31");
        assertNotNull(d);
        assertEquals(2024, d.getYear());
        assertNull(DateUtils.parseIso("2024-02-30"));
        assertNull(DateUtils.parseIso(null));
    }
}

