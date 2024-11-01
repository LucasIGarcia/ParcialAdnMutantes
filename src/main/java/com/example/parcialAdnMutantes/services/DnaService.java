package com.example.parcialAdnMutantes.services;
import com.example.parcialAdnMutantes.entities.Dna;
import com.example.parcialAdnMutantes.exception.InvalidSequence;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.parcialAdnMutantes.repositories.DnaRepository;

import java.util.Optional;

@SuperBuilder
@Service
public class DnaService {

    private final DnaRepository dnaRepository;
    private static final int SEQUENCE_LENGTH = 4;

//INYECCION DE DEPENDECNIAS
    @Autowired
    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }


    public static boolean isMutant(String[] dna) {
        int n = dna.length;
        int sequenceCount = 0;

        checkSequence(dna);

    //LLAMAR AL METODO FILAS
        sequenceCount += checkFilas(dna, n);
        if (sequenceCount>1) return true;

    //LLAMAR AL METODO COLUMNAS
        sequenceCount += checkColumnas(dna, n);
        if (sequenceCount>1) return true;

    //LLAMAR AL METODO DIAGONALES
        sequenceCount += checkDiagonales(dna, n);
        return sequenceCount>1;
    }

    //METODO PARA VALIDAR LAS SECUENCIAS
    private static void checkSequence(String[] dna){
        int n=dna.length;

        for (String row:dna){
            //LONGITUD CORRECTA
            if (row.length()!=n){
                throw new InvalidSequence("Tamaño de la secuencia NO VALIDA.");
            }
            //CARACTERES CORRECTOS
            if (!row.matches("[ATCG]+")){
                throw new InvalidSequence("Caracteres no validos en la secuencia.");
            }
        }
    }

    //METODO FILAS
    private static int checkFilas(String[] dna, int n){
            int sequenceCount = 0;

            for(int i =0; i < n; i++) {
                int count = 1;
                for (int j = 1; j < n; j++){
                    if (dna[i].charAt(j) == dna[i].charAt(j-1)){
                        count++;
                        if (count == SEQUENCE_LENGTH){
                            sequenceCount++;
                            if (sequenceCount > 1) return sequenceCount;
                        }
                    } else {count =1;}


                }
            } return sequenceCount;
    }

    //METODO COLUMNAS
    private static int checkColumnas(String[] dna, int n){
        int sequenceCount = 0;

        for (int j = 0; j < n; j++){
            int count = 1;

            for(int i =1; i <n; i++) {
                if (dna[i].charAt(j) == dna[i-1].charAt(j)){
                    count++;
                    if (count == SEQUENCE_LENGTH){
                        sequenceCount++;
                        if (sequenceCount > 1) return sequenceCount;
                    }
                } else {count =1;}
            }
        } return sequenceCount;
    }

    //METODOS DIAGONALES
    private static int checkDiagonales(String[] dna, int n){
        int sequenceCount = 0;

        //DIAGONALES IZQ a DER
        for(int i=0; i<= n - SEQUENCE_LENGTH; i++){
            for(int j = 0; j <=n -SEQUENCE_LENGTH; j++){
                if(checkDiag(dna, n, i, j, 1, 1)){
                    sequenceCount++;
                    if(sequenceCount>1) return sequenceCount;
                }
            }
        }
        //DIAGONALES DER a IZQ
        for(int i=0; i<= n -SEQUENCE_LENGTH; i++){
            for(int j = SEQUENCE_LENGTH -1; j<n ; j++){
                if(checkDiag(dna, n, i, j, 1, -1)){
                    sequenceCount++;
                    if(sequenceCount>1) return sequenceCount;
                }
            }
        }

    return sequenceCount;
    }

    private static boolean checkDiag(String[] dna,int n, int x, int y, int dx, int dy){
        char first = dna[x].charAt(y);
        for(int i = 1; i < SEQUENCE_LENGTH; i++){
                if( x + i * dx >= n || y + i * dy >= n || y + i * dy < 0 || dna[ x + i * dx].charAt( y + i * dy) != first) {
                    return false;
                }
        } return true;
    }

    //METODO PARA VERIFICAR SI YA EXISTE LA SECUENCIA EN LA BASE DE DATOS

    public boolean analizarAdnBD(String[] dna){
        String dnaSequence = String.join(",", dna);

        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);

        if (existingDna.isPresent()){
            return existingDna.get().isMutant();
        }else {
            boolean isMutant = isMutant(dna);
            Dna dna1 = Dna.builder().dna(dnaSequence).isMutant(isMutant).build();
            dnaRepository.save(dna1);
            return isMutant;
        }

    }

}
