package com.mercadolibre.mutants.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MutantRequest {
    private List<String> dna;
}
