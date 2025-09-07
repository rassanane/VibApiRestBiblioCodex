package fr.keltou.biblio.utils;

public final class StringUtils {
    private StringUtils() {}

    // Nettoie une chaîne (trim + null-safe)
    public static String clean(String s) {
        return s == null ? null : s.trim();
    }

    // Vérifie si vide ou null
    public static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}

