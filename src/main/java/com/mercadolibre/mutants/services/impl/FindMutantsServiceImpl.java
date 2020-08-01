package com.mercadolibre.mutants.services.impl;

import com.mercadolibre.mutants.finders.DiagonalFinder;
import com.mercadolibre.mutants.finders.InvertedDiagonalFinder;
import com.mercadolibre.mutants.finders.VerticalFinder;
import com.mercadolibre.mutants.services.FindMutantsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindMutantsServiceImpl implements FindMutantsService {

    private static final List<String> REFERENCE = List.of("AAAA", "CCCC", "GGGG", "TTTT");
    private static final int MIN_SEQUENCES = 2;

    public boolean isMutant(final List<String> dna) {
        final int limit = dna.size();
        DiagonalFinder diagonalFinder = new DiagonalFinder(limit, limit, dna);
        VerticalFinder verticalFinder = new VerticalFinder(limit, limit, dna);
        InvertedDiagonalFinder invertedDiagonalFinder = new InvertedDiagonalFinder(6, 6, dna);

        int mutants = 0;
        for (int row = 0; row < limit && mutants < MIN_SEQUENCES; row++) {
            mutants += checkHorizontal(dna.get(row));
            for (int col = 0; col < limit && mutants < MIN_SEQUENCES; col++) {
                mutants += diagonalFinder.check(row, col, String.valueOf(dna.get(row).charAt(col)));
                mutants += verticalFinder.check(row, col, String.valueOf(dna.get(row).charAt(col)));
                mutants += invertedDiagonalFinder.check(row, col, String.valueOf(dna.get(row).charAt(col)));
            }
        }

        return mutants > 1;
    }

    private long checkHorizontal(final String word) {
        return REFERENCE.stream().filter(sequence -> word.toUpperCase().contains(sequence)).count();
    }

}
