package ua.training.admission.i18n;

import java.util.Locale;

/**
 * SupportedLocale
 */
public enum SupportedLocale {
    UA (new Locale("uk", "UA"), "ua"),
    EN (new Locale("en", "EN"), "en");

    private final Locale locale;
    private final String param;
    private static final SupportedLocale DEFAULT_LOCALE = EN;

    SupportedLocale(Locale locale, String param) {
        this.locale = locale;
        this.param = param;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getParam() {
        return param;
    }

    public static Locale getDefault() {
        return DEFAULT_LOCALE.getLocale();
    }

}