package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.services.impl.FindMutantsServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMutantsServiceImplTest {

    @Test
    public void whenMoreThanOneMutationThenReturnTrue() {
        FindMutantsService service = new FindMutantsServiceImpl();
        boolean isMutant = service.isMutant(List.of("AAAA", "ATTT", "ATTT", "ATTT"));
        assertEquals(true, isMutant);
    }

    @Test
    public void whenMoreThanOneMutationAndHorizontalAndDiagonalSequenceThenReturnTrue() {
        FindMutantsService service = new FindMutantsServiceImpl();
        boolean isMutant = service.isMutant(List.of("AAAA", "TAAT", "TTAT", "TTTA"));
        assertEquals(true, isMutant);
    }

    @Test
    public void whenMoreThanOneMutationAndDiagonalAndInvertedDiagonalSequenceThenReturnTrue() {
        FindMutantsService service = new FindMutantsServiceImpl();
        boolean isMutant = service.isMutant(List.of("AGGT", "TATT", "TTAT", "TTTA"));
        assertEquals(true, isMutant);
    }

    @Test
    public void whenNotCompleteSequenceThenReturnFalse() {
        FindMutantsService service = new FindMutantsServiceImpl();
        boolean isMutant = service.isMutant(List.of("AGGT", "AATT", "ATAT", "TTTG"));
        assertEquals(false, isMutant);
    }

    @Test
    public void whenEqualsOrLessThanOneMutationThenReturnFalse() {
        FindMutantsService service = new FindMutantsServiceImpl();
        boolean isMutant = service.isMutant(List.of("AA", "TT"));
        assertEquals(false, isMutant);
    }

}
