package com.mercadolibre.mutants.services.impl;

import com.mercadolibre.mutants.finders.DiagonalFinder;
import com.mercadolibre.mutants.finders.InvertedDiagonalFinder;
import com.mercadolibre.mutants.finders.VerticalFinder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckMutantService {

    private static final List<String> REFERENCE = List.of("AAAA", "CCCC", "GGGG", "TTTT");
    private static final int MIN_SEQUENCES = 2;

    public boolean isMutant(final List<String> dna) {
        final int limit = dna.size();
        DiagonalFinder diagonalFinder = new DiagonalFinder(limit, limit, dna);
        VerticalFinder verticalFinder = new VerticalFinder(limit, limit, dna);
        InvertedDiagonalFinder invertedDiagonalFinder = new InvertedDiagonalFinder(6, 6, dna);

        int mutants = 0;
        int x = 0;
        while (x < limit && mutants < MIN_SEQUENCES) {
            mutants += checkHorizontal(dna.get(x));
            for (int j = 0; j < limit; j++) {
                mutants += diagonalFinder.check(x, j, String.valueOf(dna.get(x).charAt(j)));
                mutants += verticalFinder.check(x, j, String.valueOf(dna.get(x).charAt(j)));
                mutants += invertedDiagonalFinder.check(x, j, String.valueOf(dna.get(x).charAt(j)));
            }
            x++;
        }
        return mutants > 1;
    }

    private long checkHorizontal(final String word) {
        return REFERENCE.stream().filter(sequence -> word.contains(sequence)).count();
    }

}
