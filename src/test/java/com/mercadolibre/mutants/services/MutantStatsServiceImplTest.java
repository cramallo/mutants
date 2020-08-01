package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.exceptions.InternalServerException;
import com.mercadolibre.mutants.models.MutantStats;
import com.mercadolibre.mutants.repositories.MutantStatsRepository;
import com.mercadolibre.mutants.services.impl.MutantStatsServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MutantStatsServiceImplTest {

    private MutantStatsServiceImpl service;

    @Test
    public void whenFailDatabaseThenThrowInternalServerException() {
        this.service = new MutantStatsServiceImpl(mockInvalidMutantsStatsRepository());
        assertThrows(InternalServerException.class, () -> service.saveMutantStats(false));
    }

    @Test
    public void whenSuccessThenReturnMutantStatsResponse() {
        this.service = new MutantStatsServiceImpl(mockMutantStatsRepository());
        assertEquals(new MutantStats(), service.saveMutantStats(false));
    }

    @Test
    public void whenSuccessAndFirstSaveThenReturnMutantStatsResponse() {
        this.service = new MutantStatsServiceImpl(mockMutantStatsFirstSave());
        assertEquals(new MutantStats(), service.saveMutantStats(false));
    }

    @Test
    public void whenGetLastStatsThenThrowInternalServerException() {
        this.service = new MutantStatsServiceImpl(mockInvalidMutantsStatsRepository());
        assertThrows(InternalServerException.class, () -> service.getLastStats());
    }

    @Test
    public void whenGetLastStatsThenReturnMutantStats() {
        this.service = new MutantStatsServiceImpl(mockMutantStatsRepository());
        assertEquals(new MutantStats(), service.getLastStats());
    }


    private MutantStatsRepository mockInvalidMutantsStatsRepository() {
        final MutantStatsRepository mutantStatsRepository = mock(MutantStatsRepository.class);
        when(mutantStatsRepository.findTopByOrderByIdDesc()).thenThrow(InternalServerException.class);
        return mutantStatsRepository;
    }

    private MutantStatsRepository mockMutantStatsRepository() {
        final MutantStatsRepository mutantStatsRepository = mock(MutantStatsRepository.class);
        when(mutantStatsRepository.findTopByOrderByIdDesc()).thenReturn(new MutantStats());
        when(mutantStatsRepository.save(any())).thenReturn(new MutantStats());
        return mutantStatsRepository;
    }

    private MutantStatsRepository mockMutantStatsFirstSave() {
        final MutantStatsRepository mutantStatsRepository = mock(MutantStatsRepository.class);
        when(mutantStatsRepository.findTopByOrderByIdDesc()).thenReturn(null);
        when(mutantStatsRepository.save(any())).thenReturn(new MutantStats());
        return mutantStatsRepository;
    }


}
