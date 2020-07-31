package com.mercadolibre.mutants.finders;

import java.util.List;

public class DiagonalFinder extends Finder {

    public DiagonalFinder(final int n, final int m, final List<String> dna) {
        super(n, m, dna);
    }

    @Override
    public int check(final int xOrigin, final int yOrigin, final String word) {
        if (shouldNotCheck(xOrigin, yOrigin) || outOfLimits(xOrigin, yOrigin)) {
            return 0;
        }
        String aux = word;

        int xCurrent = xOrigin + 1;
        int yCurrent = yOrigin + 1;

        while (correctSequence(aux) && xCurrent < xOrigin + LIMIT_CHARACTERS_SEQUENCE && yCurrent < yOrigin + LIMIT_CHARACTERS_SEQUENCE) {
            char current = dna.get(xCurrent).charAt(yCurrent);
            aux = aux.concat(String.valueOf(current));
            xCurrent++;
            yCurrent++;
        }

        if (correctSequence(aux) && aux.length() == LIMIT_CHARACTERS_SEQUENCE) {
            shouldCheck[xOrigin + 1][yOrigin + 1] = true;
            return 1;
        }

        shouldCheck[xCurrent - 1][yCurrent - 1] = true;

        return 0;
    }

    private boolean shouldNotCheck(int xOrigin, int yOrigin) {
        return xOrigin > 0 && yOrigin > 0 && !shouldCheck[xOrigin][yOrigin] && !shouldCheck[xOrigin][yOrigin];
    }

    private boolean outOfLimits(int xOrigin, int yOrigin) {
        return xOrigin + LIMIT_CHARACTERS_SEQUENCE > dna.size() || yOrigin + LIMIT_CHARACTERS_SEQUENCE > dna.size();
    }

}
