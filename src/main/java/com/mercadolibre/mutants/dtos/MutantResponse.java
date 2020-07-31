package com.mercadolibre.mutants.dtos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class MutantResponse {
    private boolean isMutant;
    private long id;
    private String dna;
}
