package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;

public interface MutantService {
    MutantResponse isMutant(MutantRequest mutantRequest);
}
