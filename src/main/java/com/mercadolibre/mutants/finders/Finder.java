package com.mercadolibre.mutants.finders;

import java.util.List;

public abstract class Finder {
    protected static final List<String> reference = List.of("AAAA", "CCCC", "GGGG", "TTTT");
    protected static final int LIMIT_CHARACTERS_SEQUENCE = 4;
    protected final int limit;
    protected boolean[][] shouldCheck;
    protected List<String> dna;

    public Finder(final int n, final int m, final List<String> dna) {
        this.dna = dna;
        this.shouldCheck = new boolean[n][m];
        this.limit = dna.size();
    }

    public abstract int check(final int xOrigin, final int yOrigin, final String word);

    protected boolean correctSequence(final String word) {
        return word.isEmpty() || reference.stream().anyMatch(current -> current.contains(word.toUpperCase()));
    }

}
