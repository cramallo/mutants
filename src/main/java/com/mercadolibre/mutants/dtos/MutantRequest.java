package com.mercadolibre.mutants.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class MutantRequest {
    private List<String> dna;
}
