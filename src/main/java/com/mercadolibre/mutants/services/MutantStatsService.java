package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.models.MutantStats;

public interface MutantStatsService {
    MutantStats saveMutantStats(boolean isMutant);
    MutantStats getLastStats();
}
