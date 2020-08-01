package com.mercadolibre.mutants.services.impl;

import com.mercadolibre.mutants.exceptions.InternalServerException;
import com.mercadolibre.mutants.models.MutantStats;
import com.mercadolibre.mutants.repositories.MutantStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MutantStatsService {
    private final MutantStatsRepository mutantStatsRepository;

    @Autowired
    public MutantStatsService(final MutantStatsRepository mutantStatsRepository) {
        this.mutantStatsRepository = mutantStatsRepository;
    }

    public MutantStats saveMutantStats(final boolean isMutant) {
        try {
            final MutantStats mutantStats = this.mutantStatsRepository.findTopByOrderByIdDesc();

            MutantStats newMutant = new MutantStats();

            if (mutantStats == null) {
                newMutant.setHumansCount(1);
                newMutant.setMutantsCount(isMutant ? 1 : 0);
            } else {
                newMutant.setHumansCount(mutantStats.getHumansCount() + 1);
                newMutant.setMutantsCount(isMutant ? mutantStats.getMutantsCount() + 1 : mutantStats.getMutantsCount());
            }
            newMutant.setMutantsRatio(newMutant.getMutantsCount() / (double) newMutant.getHumansCount());
            newMutant.setLastUpdate(LocalDateTime.now());
            return mutantStatsRepository.save(newMutant);
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

    public MutantStats getLastStats() {
        try {
            return this.mutantStatsRepository.findTopByOrderByIdDesc();
        } catch (Exception e) {
            throw new InternalServerException(e.getMessage());
        }
    }

}
