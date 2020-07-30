package com.mercadolibre.mutants.services.impl;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;
import com.mercadolibre.mutants.services.MutantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService implements MutantServiceInterface {

   // @Autowired
   // private CheckMutantService checkMutantService;

    public MutantResponse isMutant(MutantRequest mutantRequest) {
        CheckMutantService checkMutantService = new CheckMutantService();
        return checkMutantService.isDna(mutantRequest.getDna()) ? MutantResponse.builder().isMutant(true).build() : MutantResponse.builder().build();
    }
}
