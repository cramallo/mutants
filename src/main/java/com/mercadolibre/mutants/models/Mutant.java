package com.mercadolibre.mutants.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//@NoArgsConstructor
@Builder
@Entity
public class Mutant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NonNull
    private String dna;

    /*public Mutant(final String dna) {
        this.dna = dna;
    }*/
}
