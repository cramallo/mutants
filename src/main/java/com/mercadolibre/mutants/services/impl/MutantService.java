package com.mercadolibre.mutants.services.impl;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;
import com.mercadolibre.mutants.exceptions.BadRequestException;
import com.mercadolibre.mutants.exceptions.ForbiddenException;
import com.mercadolibre.mutants.exceptions.InternalServerException;
import com.mercadolibre.mutants.helpers.MutantsHelper;
import com.mercadolibre.mutants.models.Mutant;
import com.mercadolibre.mutants.models.MutantStats;
import com.mercadolibre.mutants.repositories.MutantRepository;
import com.mercadolibre.mutants.services.MutantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService implements MutantServiceInterface {

    private final CheckValidDnaService checkValidDnaService;
    private final CheckMutantService checkMutantService;
    private final MutantStatsService mutantStatsService;
    private final MutantRepository mutantRepository;

    @Autowired
    public MutantService(final CheckValidDnaService checkValidDnaService, final CheckMutantService checkMutantService,
                         final MutantRepository mutantRepository, final MutantStatsService mutantStatsService) {
        this.checkValidDnaService = checkValidDnaService;
        this.checkMutantService = checkMutantService;
        this.mutantRepository = mutantRepository;
        this.mutantStatsService = mutantStatsService;
    }

    public MutantResponse isMutant(final MutantRequest mutantRequest) {
        if (!this.checkValidDnaService.isValidDna(mutantRequest.getDna())) {
            throw new BadRequestException("Invalid DNA sequence");
        }
        final boolean isMutant = checkMutantService.isMutant(mutantRequest.getDna());
        MutantStats mutantStats = mutantStatsService.saveMutantStats(isMutant);
        if (isMutant) {
            return saveMutant(mutantStats, mutantRequest);
        }
        throw new ForbiddenException("Not mutant DNA");
    }

    private MutantResponse saveMutant(final MutantStats mutantStats, final MutantRequest mutantRequest) {
        if (mutantStats == null) {
            throw new InternalServerException("Could not save stats");
        }
        Mutant mutant = Mutant.builder().dna(MutantsHelper.listToString(mutantRequest.getDna())).build();
        try {
            Mutant mutantDao = this.mutantRepository.save(mutant);
            return MutantResponse.builder().isMutant(true)
                    .id(mutantDao.getId())
                    .dna(mutantDao.getDna())
                    .build();
        } catch (Exception exception) {
            throw new InternalServerException(exception.getMessage());
        }
    }
}


