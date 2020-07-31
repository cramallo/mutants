package com.mercadolibre.mutants.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class MutantStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private long humansCount;

    @Column
    private long mutantsCount;

    @Column
    private Double mutantsRatio;

    @Column
    private LocalDateTime lastUpdate;
}
