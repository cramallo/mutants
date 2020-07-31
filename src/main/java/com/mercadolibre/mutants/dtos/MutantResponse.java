package com.mercadolibre.mutants.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
@ApiModel
public class MutantResponse {
    @ApiModelProperty
    private boolean isMutant;
    @ApiModelProperty
    private long id;
    @ApiModelProperty
    private String dna;
}
