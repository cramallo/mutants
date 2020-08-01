package com.mercadolibre.mutants.services;

import java.util.List;

public interface FindMutantsService {
    boolean isMutant(final List<String> dna);
}
