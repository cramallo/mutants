package com.mercadolibre.mutants.controllers;

import com.mercadolibre.mutants.constants.SwaggerMessages;
import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;
import com.mercadolibre.mutants.models.MutantStats;
import com.mercadolibre.mutants.services.MutantServiceInterface;
import com.mercadolibre.mutants.services.impl.MutantStatsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mutants")
@Api
public class MutantController {

    private final MutantServiceInterface mutantService;
    private final MutantStatsService mutantStatsService;

    @Autowired
    public MutantController(final MutantServiceInterface mutantService, final MutantStatsService mutantStatsService) {
        this.mutantService = mutantService;
        this.mutantStatsService = mutantStatsService;
    }

    @PostMapping
    @ApiOperation(value = "Return created mutant", response = MutantResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerMessages.successMutant),
            @ApiResponse(code = 400, message = SwaggerMessages.badRequest),
            @ApiResponse(code = 403, message = SwaggerMessages.forbidden),
            @ApiResponse(code = 404, message = SwaggerMessages.notFound),
            @ApiResponse(code = 500, message = SwaggerMessages.internalServerError)
    })
    public MutantResponse isMutant(@RequestBody final MutantRequest mutantRequest) {
        return mutantService.isMutant(mutantRequest);
    }

    @GetMapping("/stats")
    @ApiOperation(value = "Return statics about DNA requests", response = MutantStats.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SwaggerMessages.successFound),
            @ApiResponse(code = 400, message = SwaggerMessages.badRequest),
            @ApiResponse(code = 404, message = SwaggerMessages.notFound),
            @ApiResponse(code = 500, message = SwaggerMessages.internalServerError)
    })
    public MutantStats getLastStats() {
        return mutantStatsService.getLastStats();
    }

}
