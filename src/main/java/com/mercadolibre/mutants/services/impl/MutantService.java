package com.mercadolibre.mutants.services.impl;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;
import com.mercadolibre.mutants.exceptions.BadRequestException;
import com.mercadolibre.mutants.exceptions.InternalServerException;
import com.mercadolibre.mutants.models.Mutant;
import com.mercadolibre.mutants.repositories.MutantRepository;
import com.mercadolibre.mutants.services.MutantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MutantService implements MutantServiceInterface {

    private final CheckValidDnaService checkValidDnaService;
    private final CheckMutantService checkMutantService;
    private final MutantRepository mutantRepository;

    @Autowired
    public MutantService(final CheckValidDnaService checkValidDnaService, final CheckMutantService checkMutantService,
                         final MutantRepository mutantRepository) {
        this.checkValidDnaService = checkValidDnaService;
        this.checkMutantService = checkMutantService;
        this.mutantRepository = mutantRepository;
    }

    public MutantResponse isMutant(final MutantRequest mutantRequest) {

        if (this.checkValidDnaService.isValidDna(mutantRequest.getDna())) {

            if (checkMutantService.isMutant(mutantRequest.getDna())) {

                Mutant mutant = Mutant.builder().dna(mutantRequest.getDna().stream().map(Object::toString).collect(Collectors.joining(",")))
                        .build();
                try {
                    this.mutantRepository.save(mutant);
                } catch(Exception exception) {
                    throw new InternalServerException(exception.getMessage());
                }

                return MutantResponse.builder().isMutant(true).build();
            }

            return MutantResponse.builder().build();
        }

        throw new BadRequestException("Invalid DNA sequence");
    }

}
