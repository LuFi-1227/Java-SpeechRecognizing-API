package com.application.users.domain;

import java.util.regex.Pattern;

public record Phone(String value) {
    private static final Pattern WHATSAPP_BR = Pattern.compile("^55[1-9]{2}9?\\d{8}$");                             
                                                                                                                        
   public Phone {
        value = normalize(value);
        if (!isValid(value)) {
            throw new IllegalArgumentException("Telefone inválido: " + value);
        }
    }

    public static String normalize(String raw) {
        if (raw == null) return "";
        String digits = raw.replaceAll("\\D", "");
        if (digits.length() == 10 || digits.length() == 11) {
            digits = "55" + digits;
        }
        return digits;
    }

    public static boolean isValid(String raw) {
        String normalized = normalize(raw);
        return WHATSAPP_BR.matcher(normalized).matches();
    }         
}
