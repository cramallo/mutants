package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;
import com.mercadolibre.mutants.exceptions.BadRequestException;
import com.mercadolibre.mutants.exceptions.ForbiddenException;
import com.mercadolibre.mutants.exceptions.InternalServerException;
import com.mercadolibre.mutants.models.Mutant;
import com.mercadolibre.mutants.models.MutantStats;
import com.mercadolibre.mutants.repositories.MutantRepository;
import com.mercadolibre.mutants.services.impl.FindMutantsServiceImpl;
import com.mercadolibre.mutants.services.impl.DnaFormatValidatorService;
import com.mercadolibre.mutants.services.impl.MutantServiceImpl;
import com.mercadolibre.mutants.services.impl.MutantStatsServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MutantServiceImplTest {

    private MutantServiceImpl service;

    @Test
    public void whenNotValidDnaThenThrowsBadRequestException() {
        this.service = new MutantServiceImpl(mockInvalidDna(), mock(FindMutantsServiceImpl.class), mock(MutantRepository.class), mock(MutantStatsServiceImpl.class));
        assertThrows(BadRequestException.class, () -> this.service.isMutant(mockMutantRequest()));
    }

    @Test
    public void whenValidDnaSequenceAndNotMutantThenThrowsForbiddenException() {
        this.service = new MutantServiceImpl(mockValidDna(), mockNotMutant(), mock(MutantRepository.class), mock(MutantStatsServiceImpl.class));
        assertThrows(ForbiddenException.class, () -> this.service.isMutant(mockMutantRequest()));
    }

    @Test
    public void whenFailDatabaseSaveThenThrowsInternalServerException() {
        this.service = new MutantServiceImpl(mockValidDna(), mockMutant(), mockFailedRepository(), mockMutantStatsService());
        assertThrows(InternalServerException.class, () -> this.service.isMutant(mockMutantRequest()));
    }

    @Test
    public void whenSuccessThenReturnMutantResponse() {
        this.service = new MutantServiceImpl(mockValidDna(), mockMutant(), mockSuccessRepository(), mockMutantStatsService());
        final MutantResponse mutantResponseReference = MutantResponse.builder().isMutant(true).build();
        assertEquals(mutantResponseReference, this.service.isMutant(mockMutantRequest()));
    }

    @Test
    public void whenNullStatsThenThrowInternalServerError() {
        this.service = new MutantServiceImpl(mockValidDna(), mockMutant(), mockSuccessRepository(), mock(MutantStatsServiceImpl.class));
        assertThrows(InternalServerException.class, () -> this.service.isMutant(mockMutantRequest()));
    }

    private DnaFormatValidatorService mockInvalidDna() {
        final DnaFormatValidatorService dnaFormatValidatorService = mock(DnaFormatValidatorService.class);
        when(dnaFormatValidatorService.isValidDna(any())).thenReturn(false);
        return dnaFormatValidatorService;
    }

    private DnaFormatValidatorService mockValidDna() {
        final DnaFormatValidatorService dnaFormatValidatorService = mock(DnaFormatValidatorService.class);
        when(dnaFormatValidatorService.isValidDna(any())).thenReturn(true);
        return dnaFormatValidatorService;
    }

    private FindMutantsServiceImpl mockNotMutant() {
        final FindMutantsServiceImpl findMutantsServiceImpl = mock(FindMutantsServiceImpl.class);
        when(findMutantsServiceImpl.isMutant(any())).thenReturn(false);
        return findMutantsServiceImpl;
    }

    private FindMutantsServiceImpl mockMutant() {
        final FindMutantsServiceImpl findMutantsServiceImpl = mock(FindMutantsServiceImpl.class);
        when(findMutantsServiceImpl.isMutant(any())).thenReturn(true);
        return findMutantsServiceImpl;
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

    private MutantStatsServiceImpl mockMutantStatsService() {
        final MutantStatsServiceImpl mutantStatsServiceImpl = mock(MutantStatsServiceImpl.class);
        when(mutantStatsServiceImpl.saveMutantStats(anyBoolean())).thenReturn(new MutantStats());
        return mutantStatsServiceImpl;
    }
}
