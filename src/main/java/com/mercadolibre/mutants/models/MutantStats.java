package com.mercadolibre.mutants.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class MutantStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long humansCount;
    private long mutantsCount;
    private Double mutantsRatio;
    private LocalDateTime lastUpdate;
}
