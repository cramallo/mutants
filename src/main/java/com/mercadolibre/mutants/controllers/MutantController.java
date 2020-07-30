package com.mercadolibre.mutants.controllers;

import com.mercadolibre.mutants.dtos.MutantRequest;
import com.mercadolibre.mutants.dtos.MutantResponse;
import com.mercadolibre.mutants.services.MutantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutants")
public class MutantController {

    @Autowired
    private MutantServiceInterface mutantService;

    @PostMapping
    public MutantResponse isMutant(@RequestBody final MutantRequest mutantRequest) {
        return mutantService.isMutant(mutantRequest);
    }

}
