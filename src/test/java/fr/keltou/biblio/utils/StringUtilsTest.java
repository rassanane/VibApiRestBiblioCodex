package fr.keltou.biblio.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test void clean_trims_and_handles_null() {
        assertNull(StringUtils.clean(null));
        assertEquals("abc", StringUtils.clean("  abc  "));
    }

    @Test void isBlank_works() {
        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank("   "));
        assertFalse(StringUtils.isBlank("x"));
    }
}

