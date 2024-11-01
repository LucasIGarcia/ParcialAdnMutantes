
package com.example.parcialAdnMutantes.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;

@SuperBuilder
@Data
@Entity
public class Dna implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String dna;
    private boolean isMutant;
}
