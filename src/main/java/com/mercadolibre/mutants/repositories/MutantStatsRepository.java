package com.mercadolibre.mutants.repositories;

import com.mercadolibre.mutants.models.MutantStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantStatsRepository extends JpaRepository<MutantStats, Long> {
    MutantStats findTopByOrderByIdDesc();
}
