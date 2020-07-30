package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;

public interface MutantServiceInterface {
    MutantResponse isMutant(MutantRequest mutantRequest);
}
