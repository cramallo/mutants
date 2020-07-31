package com.mercadolibre.mutants.finders;

import java.util.List;

public class VerticalFinder extends Finder {

    public VerticalFinder(final int n, final int m, final List<String> dna) {
        super(n, m, dna);
    }

    @Override
    public int check(final int xOrigin, final int yOrigin, final String word) {
        if ((xOrigin > 0 && !shouldCheck[xOrigin][yOrigin]) || (xOrigin + LIMIT_CHARACTERS_SEQUENCE > dna.size())) {
            return 0;
        }

        String aux = word;
        int xCurrent = xOrigin;

        while (correctSequence(aux) && xCurrent + 1 < xOrigin + LIMIT_CHARACTERS_SEQUENCE) {
            xCurrent++;
            String currentWord = String.valueOf(dna.get(xCurrent).charAt(yOrigin));
            aux = aux.concat(currentWord);
        }

        if (correctSequence(aux) && aux.length() == LIMIT_CHARACTERS_SEQUENCE) {
            shouldCheck[xOrigin + 1][yOrigin] = true;
            return 1;
        }

        shouldCheck[xCurrent][yOrigin] = true;

        return 0;
    }

}
