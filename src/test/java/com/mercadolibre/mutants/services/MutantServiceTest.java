package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;
import com.mercadolibre.mutants.exceptions.BadRequestException;
import com.mercadolibre.mutants.exceptions.ForbiddenException;
import com.mercadolibre.mutants.exceptions.InternalServerException;
import com.mercadolibre.mutants.models.Mutant;
import com.mercadolibre.mutants.models.MutantStats;
import com.mercadolibre.mutants.repositories.MutantRepository;
import com.mercadolibre.mutants.services.impl.CheckMutantService;
import com.mercadolibre.mutants.services.impl.CheckValidDnaService;
import com.mercadolibre.mutants.services.impl.MutantService;
import com.mercadolibre.mutants.services.impl.MutantStatsService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MutantServiceTest {

    private MutantService service;

    @Test
    public void whenNotValidDnaThenThrowsBadRequestException() {
        this.service = new MutantService(mockInvalidDna(), mock(CheckMutantService.class), mock(MutantRepository.class), mock(MutantStatsService.class));
        assertThrows(BadRequestException.class, () -> this.service.isMutant(mockMutantRequest()));
    }

    @Test
    public void whenValidDnaSequenceAndNotMutantThenThrowsForbiddenException() {
        this.service = new MutantService(mockValidDna(), mockNotMutant(), mock(MutantRepository.class), mock(MutantStatsService.class));
        assertThrows(ForbiddenException.class, () -> this.service.isMutant(mockMutantRequest()));
    }

    @Test
    public void whenFailDatabaseSaveThenThrowsInternalServerException() {
        this.service = new MutantService(mockValidDna(), mockMutant(), mockFailedRepository(), mockMutantStatsService());
        assertThrows(InternalServerException.class, () -> this.service.isMutant(mockMutantRequest()));
    }

    @Test
    public void whenSuccessThenReturnMutantResponse() {
        this.service = new MutantService(mockValidDna(), mockMutant(), mockSuccessRepository(), mockMutantStatsService());
        final MutantResponse mutantResponseReference = MutantResponse.builder().isMutant(true).build();
        assertEquals(mutantResponseReference, this.service.isMutant(mockMutantRequest()));
    }

    @Test
    public void whenNullStatsThenThrowInternalServerError() {
        this.service = new MutantService(mockValidDna(), mockMutant(), mockSuccessRepository(), mock(MutantStatsService.class));
        assertThrows(InternalServerException.class, () -> this.service.isMutant(mockMutantRequest()));
    }

    private CheckValidDnaService mockInvalidDna() {
        final CheckValidDnaService checkValidDnaService = mock(CheckValidDnaService.class);
        when(checkValidDnaService.isValidDna(any())).thenReturn(false);
        return checkValidDnaService;
    }

    private CheckValidDnaService mockValidDna() {
        final CheckValidDnaService checkValidDnaService = mock(CheckValidDnaService.class);
        when(checkValidDnaService.isValidDna(any())).thenReturn(true);
        return checkValidDnaService;
    }

    private CheckMutantService mockNotMutant() {
        final CheckMutantService checkMutantService = mock(CheckMutantService.class);
        when(checkMutantService.isMutant(any())).thenReturn(false);
        return checkMutantService;
    }

    private CheckMutantService mockMutant() {
        final CheckMutantService checkMutantService = mock(CheckMutantService.class);
        when(checkMutantService.isMutant(any())).thenReturn(true);
        return checkMutantService;
    }

    private MutantRepository mockFailedRepository() {
        final MutantRepository mutantRepository = mock(MutantRepository.class);
        when(mutantRepository.save(any())).thenThrow(InternalServerException.class);
        return mutantRepository;
    }

    private MutantRepository mockSuccessRepository() {
        final MutantRepository mutantRepository = mock(MutantRepository.class);
        when(mutantRepository.save(any())).thenReturn(Mutant.builder().build());
        return mutantRepository;
    }

    private MutantRequest mockMutantRequest() {
        final MutantRequest mutantRequest = new MutantRequest();
        mutantRequest.setDna(List.of(""));
        return mutantRequest;
    }

    private MutantStatsService mockMutantStatsService() {
        final MutantStatsService mutantStatsService = mock(MutantStatsService.class);
        when(mutantStatsService.saveMutantStats(anyBoolean())).thenReturn(new MutantStats());
        return mutantStatsService;
    }
}
