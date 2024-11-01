package com.example.parcialAdnMutantes.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DnaResponse {
    private boolean isMutant;

    public boolean isMutant (){
        return isMutant;
    }
}

