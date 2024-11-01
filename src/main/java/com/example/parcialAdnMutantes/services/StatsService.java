package com.example.parcialAdnMutantes.services;

import com.example.parcialAdnMutantes.dto.StatsResponse;
import com.example.parcialAdnMutantes.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatsService {

    private DnaRepository dnaRepository;

    //INYECCION DE DEPENDECNIAS
    @Autowired
    public StatsService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public StatsResponse getStats(){
        long countTotal = dnaRepository.count();
        long countMutant = dnaRepository.countByIsMutant(true);
        long countHuman = dnaRepository.countByIsMutant(false);
        double ratio = countHuman == 0 ? 0 : (double) countMutant / countHuman;

//PORCENTAJE DE MUTANTES
        float percentageMutant = countTotal == 0 ? 0 : ((float) countMutant / countTotal) * 100;

    return new StatsResponse(countMutant, countHuman,ratio, countTotal, percentageMutant);
    }
}
