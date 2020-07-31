package com.mercadolibre.mutants.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@ApiModel
public class MutantStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @ApiModelProperty
    private long humansCount;

    @Column
    @ApiModelProperty
    private long mutantsCount;

    @Column
    @ApiModelProperty
    private Double mutantsRatio;

    @Column
    @ApiModelProperty
    private LocalDateTime lastUpdate;
}
