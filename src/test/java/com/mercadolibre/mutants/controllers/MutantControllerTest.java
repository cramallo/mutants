package com.mercadolibre.mutants.controllers;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;
import com.mercadolibre.mutants.models.MutantStats;
import com.mercadolibre.mutants.services.MutantService;
import com.mercadolibre.mutants.services.MutantStatsService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MutantControllerTest {

    @Test
    public void whenSuccessIsMutantThenReturnMutantResponse() {
        final MutantController mutantController = new MutantController(mockMutantService(), null);
        assertEquals(MutantResponse.builder().build(), mutantController.isMutant(mock(MutantRequest.class)));
    }

    @Test
    public void whenSuccessGetStatsThenReturnMutantStats() {
        final MutantController mutantController = new MutantController(null, mockMutantStatsService());
        assertEquals(new MutantStats(), mutantController.getLastStats());
    }

    private MutantService mockMutantService() {
        final MutantService mutantService = mock(MutantService.class);
        when(mutantService.isMutant(any())).thenReturn(MutantResponse.builder().build());
        return mutantService;
    }

    private MutantStatsService mockMutantStatsService() {
        final MutantStatsService mutantStatsService = mock(MutantStatsService.class);
        when(mutantStatsService.getLastStats()).thenReturn(new MutantStats());
        return mutantStatsService;
    }

}
