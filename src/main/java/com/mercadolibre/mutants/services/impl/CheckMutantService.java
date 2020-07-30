package com.mercadolibre.mutants.services.impl;

import org.springframework.stereotype.Service;

import java.util.List;

public class CheckMutantService {
    private final static List<String> reference = List.of("AAAA", "CCCC", "GGGG", "TTTT");
    private static final int LIMIT_CHARACTERS_SEQUENCE = 4;
    private static final int MIN_SEQUENCES = 2;

    private boolean[][] shouldCheckVertical = new boolean[6][6];
    private boolean[][] shouldCheckDiagonal = new boolean[6][6];
    private boolean[][] shouldCheckInvertedDiagonal = new boolean[6][6];

    public CheckMutantService() {

    }

    List<String> currentDna;

    int mutants = 0;

    public boolean isDna(final List<String> dna) {
        currentDna = dna;
        int x = 0;
        while (x < currentDna.size() && mutants < MIN_SEQUENCES) {
            mutants += checkHorizontal(currentDna.get(x));
            for (int j = 0; j < currentDna.size(); j++) {
                checkDna(x, j, String.valueOf(currentDna.get(x).charAt(j)));
            }
            x++;
        }
        return mutants > 1;
    }

    private void checkDna(final int i, final int j, final String value) {
        mutants += checkVertical(i, j, value);
        mutants += checkDiagonal(i, j, value);
        mutants += checkInvertedDiagonal(i, j, value);
    }

    private long checkHorizontal(final String word) {
        return reference.stream().filter(nose -> word.contains(nose)).count();
    }

    private int checkDiagonal(final int xOrigin, final int yOrigin, final String word) {

        if ((xOrigin > 0 && yOrigin > 0 && !shouldCheckDiagonal[xOrigin][yOrigin]) || (xOrigin + LIMIT_CHARACTERS_SEQUENCE > currentDna.size() || yOrigin + LIMIT_CHARACTERS_SEQUENCE > currentDna.size())) {
            return 0;
        }
        String aux = word;

        int xCurrent = xOrigin + 1;
        int yCurrent = yOrigin + 1;

        while (correctSequence(aux) && xCurrent < xOrigin + LIMIT_CHARACTERS_SEQUENCE && yCurrent < yOrigin + LIMIT_CHARACTERS_SEQUENCE) {
            char current = currentDna.get(xCurrent).charAt(yCurrent);
            aux = aux.concat(String.valueOf(current));
            xCurrent++;
            yCurrent++;
        }

        if (correctSequence(aux) && aux.length() == LIMIT_CHARACTERS_SEQUENCE) {
            shouldCheckDiagonal[xOrigin + 1][yOrigin + 1] = true;
            return 1;
        }

        shouldCheckDiagonal[xCurrent - 1][yCurrent - 1] = true;

        return 0;
    }

    private int checkInvertedDiagonal(final int xOrigin, final int yOrigin, final String word) {

        if ((xOrigin > 0 && yOrigin < 5 && !shouldCheckInvertedDiagonal[xOrigin][yOrigin])
                || (xOrigin + LIMIT_CHARACTERS_SEQUENCE > currentDna.size() || yOrigin - (LIMIT_CHARACTERS_SEQUENCE - 1) < 0)) {
            return 0;
        }

        String aux = word;

        int xCurrent = xOrigin + 1;
        int yCurrent = yOrigin - 1;

        while (correctSequence(aux) && xCurrent < xOrigin + LIMIT_CHARACTERS_SEQUENCE && yCurrent > yOrigin - LIMIT_CHARACTERS_SEQUENCE) {
            char current = currentDna.get(xCurrent).charAt(yCurrent);
            aux = aux.concat(String.valueOf(current));
            xCurrent++;
            yCurrent--;
        }

        if (correctSequence(aux) && aux.length() == LIMIT_CHARACTERS_SEQUENCE) {
            shouldCheckInvertedDiagonal[xOrigin + 1][yOrigin - 1] = true;
            return 1;
        }

        shouldCheckInvertedDiagonal[xCurrent - 1][yCurrent + 1] = true;

        return 0;
    }

    private int checkVertical(final int xOrigin, final int y, final String word) {

        if ((xOrigin > 0 && !shouldCheckVertical[xOrigin][y]) || (xOrigin + LIMIT_CHARACTERS_SEQUENCE > currentDna.size())) {
            return 0;
        }

        String aux = word;
        int xCurrent = xOrigin;

        while (correctSequence(aux) && xCurrent + 1 < xOrigin + LIMIT_CHARACTERS_SEQUENCE) {
            xCurrent++;
            String currentWord = String.valueOf(currentDna.get(xCurrent).charAt(y));
            aux = aux.concat(currentWord);
        }

        if (correctSequence(aux) && aux.length() == LIMIT_CHARACTERS_SEQUENCE) {
            shouldCheckVertical[xOrigin + 1][y] = true;
            return 1;
        }

        shouldCheckVertical[xCurrent][y] = true;

        return 0;
    }

    public boolean correctSequence(final String word) {
        return word.isEmpty() || reference.stream().anyMatch(current -> current.contains(word));
    }
}
