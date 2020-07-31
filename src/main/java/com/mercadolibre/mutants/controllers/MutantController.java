package com.mercadolibre.mutants.controllers;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;
import com.mercadolibre.mutants.models.MutantStats;
import com.mercadolibre.mutants.services.MutantServiceInterface;
import com.mercadolibre.mutants.services.impl.MutantStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutants")
public class MutantController {

    private final MutantServiceInterface mutantService;
    private final MutantStatsService mutantStatsService;

    @Autowired
    public MutantController(final MutantServiceInterface mutantService, final MutantStatsService mutantStatsService) {
        this.mutantService = mutantService;
        this.mutantStatsService = mutantStatsService;
    }


    @PostMapping
    public MutantResponse isMutant(@RequestBody final MutantRequest mutantRequest) {
        return mutantService.isMutant(mutantRequest);
    }

    @GetMapping("/stats")
    public MutantStats getLastStats() {
        return mutantStatsService.getLastStats();
    }

}
