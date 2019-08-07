package ua.training.admission.i18n;

import java.util.Locale;

/**
 * SupportedLocale
 *
 * @author Oleksii Morenets
 */
public enum SupportedLocale {
    UA ("ua", new Locale("uk", "UA")),
    EN ("en", new Locale("en", "EN"));

    private final Locale locale;
    private final String key;
    private static final SupportedLocale DEFAULT_LOCALE = UA;

    SupportedLocale(String key, Locale locale) {
        this.locale = locale;
        this.key = key;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getKey() {
        return key;
    }

    public static Locale getDefault() {
        return DEFAULT_LOCALE.getLocale();
    }

}