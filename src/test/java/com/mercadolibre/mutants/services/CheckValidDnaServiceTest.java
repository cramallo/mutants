package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.services.impl.CheckValidDnaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckValidDnaServiceTest {

    @InjectMocks
    private CheckValidDnaService service;

    @BeforeEach
    public void setup() {
        this.service = new CheckValidDnaService();
    }

    @Test
    public void whenValidSequenceDnaThenReturnTrue() {
        final List<String> dna = List.of("aaaaaa", "gggggg", "tttttt", "cccccc", "aaaaaa", "gggggg");
        final boolean isValid = service.isValidDna(dna);
        assertTrue(isValid);
    }

    @Test
    void whenNullDnaSequenceThenReturnFalse() {
        final List<String> dna = null;
        final boolean isValid = service.isValidDna(dna);
        assertFalse(isValid);
    }

    @Test
    void whenEmptyDnaSequenceListThenReturnFalse() {
        final List<String> dna = List.of("");
        final boolean isValid = service.isValidDna(dna);
        assertFalse(isValid);
    }

    @Test
    void whenInvalidDnaSequenceThenReturnFalse() {
        final List<String> dna = List.of("abcd", "abcd", "abcd", "abcd");
        final boolean isValid = service.isValidDna(dna);
        assertFalse(isValid);
    }

    @Test
    void whenNotSquareMatrixThenReturnFalse() {
        final List<String> dna = List.of("abcdabcd", "abcd", "abcd", "abcd", "abcd", "abcd");
        final boolean isValid = service.isValidDna(dna);
        assertFalse(isValid);
    }

}
