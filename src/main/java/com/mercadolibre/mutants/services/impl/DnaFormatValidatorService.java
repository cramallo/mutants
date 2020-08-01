package com.mercadolibre.mutants.services.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class DnaFormatValidatorService {

    public boolean isValidDna(final List<String> dna) {
        return isCorrectDnaSequence(dna) && isSquareMatrix(dna);
    }

    private boolean isCorrectDnaSequence(final List<String> dna) {
        return dna != null && !dna.isEmpty() && dna.stream().allMatch(word -> Pattern.matches("[ACGT]+", word.toUpperCase()));
    }

    private boolean isSquareMatrix(final List<String> dna) {
        int rows = dna.size();
        return dna.stream().allMatch(columns -> columns.length() == rows);
    }

}
