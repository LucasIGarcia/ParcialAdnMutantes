package com.example.parcialAdnMutantes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatsResponse {

    private Long countMutant;
    private Long countHuman;
    private double ratio;
    private Long totalDeRegistros;
    private float porcentajeDeMutantes;


}
