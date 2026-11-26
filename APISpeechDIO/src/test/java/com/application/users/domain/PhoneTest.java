package com.application.users.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PhoneTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "5511999998888",
        "(11) 99999-8888",
        "11999998888",
        "1188887777",
        "+55 (21) 98765-4321"
    })
    @DisplayName("Deve aceitar e normalizar números válidos de telefone celular e fixo do Brasil")
    void shouldAcceptValidPhoneNumbers(String rawPhone) {
        assertTrue(Phone.isValid(rawPhone));
        assertDoesNotThrow(() -> new Phone(rawPhone));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "123",
        "abcdefg",
        "",
        "5500999998888", // DDD 00 inválido
        "99999999999999999"
    })
    @DisplayName("Deve rejeitar telefones com formato inválido")
    void shouldRejectInvalidPhoneNumbers(String rawPhone) {
        assertFalse(Phone.isValid(rawPhone));
        assertThrows(IllegalArgumentException.class, () -> new Phone(rawPhone));
    }

    @Test
    @DisplayName("Deve normalizar telefone adicionando código do país (DDI 55) e removendo caracteres especiais")
    void shouldNormalizePhoneNumber() {
        var phone = new Phone("(11) 98888-7777");
        assertEquals("5511988887777", phone.value());
    }
}
