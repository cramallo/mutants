package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    public MutantResponse isMutant(MutantRequest mutantRequest) {
        return MutantResponse.builder().isMutant(true).build();
    }
}
