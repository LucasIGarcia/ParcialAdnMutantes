package com.example.parcialAdnMutantes.controllers;


import com.example.parcialAdnMutantes.dto.DnaRequest;
import com.example.parcialAdnMutantes.dto.DnaResponse;
import com.example.parcialAdnMutantes.exception.InvalidSequence;
import com.example.parcialAdnMutantes.services.DnaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/mutant/")
public class DnaController {
    private DnaService dnaService;

//INYECCION DE DEPENDECIAS
    @Autowired
    public DnaController(DnaService dnaService){
        this.dnaService = dnaService;
    }

//METODO POST
    @PostMapping
    public ResponseEntity<DnaResponse> checkMutant (@Valid @RequestBody DnaRequest dnaRequest){
        boolean isMutant = dnaService.analizarAdnBD(dnaRequest.getDna());
        DnaResponse dnaResponse = new DnaResponse(isMutant);

        if (isMutant){
            return ResponseEntity.ok(dnaResponse);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dnaResponse);
        }
    }

    @ExceptionHandler(InvalidSequence.class)
    public ResponseEntity<String> handleInvalidDna(InvalidSequence e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


}
