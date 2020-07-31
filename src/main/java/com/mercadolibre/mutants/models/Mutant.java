package com.mercadolibre.mutants.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@EqualsAndHashCode
@Builder
@Data
@Entity
public class Mutant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String dna;
}
