package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.services.impl.CheckMutantService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckMutantServiceTest {

    @Test
    public void whenMoreThanOneMutationThenReturnTrue() {
        CheckMutantService service = new CheckMutantService();
        boolean isMutant = service.isMutant(List.of("AAAA", "ATTT", "ATTT", "ATTT"));
        assertEquals(true, isMutant);
    }

    @Test
    public void whenMoreThanOneMutationAndHorizontalAndDiagonalSequenceThenReturnTrue() {
        CheckMutantService service = new CheckMutantService();
        boolean isMutant = service.isMutant(List.of("AAAA", "TAAT", "TTAT", "TTTA"));
        assertEquals(true, isMutant);
    }

    @Test
    public void whenMoreThanOneMutationAndVerticalAndInvertedDiagonalSequenceThenReturnTrue() {
        CheckMutantService service = new CheckMutantService();
        boolean isMutant = service.isMutant(List.of("AGGT", "TATT", "TTAT", "TTTA"));
        assertEquals(true, isMutant);
    }

    @Test
    public void whenNotCompleteSequenceThenReturnFalse() {
        CheckMutantService service = new CheckMutantService();
        boolean isMutant = service.isMutant(List.of("AGGT", "AATT", "ATAT", "TTTG"));
        assertEquals(false, isMutant);
    }

    @Test
    public void whenEqualsOrLessThanOneMutationThenReturnFalse() {
        CheckMutantService service = new CheckMutantService();
        boolean isMutant = service.isMutant(List.of("AA", "TT"));
        assertEquals(false, isMutant);
    }

}
