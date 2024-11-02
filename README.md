# Primer parcial Desarrollo de Software - Detector de Mutantes

## Introduccion:
Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Mens. Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

Y te ha pedido que tenga la siguiente firma: <b> boolean isMutant(String[] dna);</b>
     
## Funcionamiento:
Se recibira como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical. 

Por ejemplo: La cadena 'CTAGTA' representa una fila de la matriz.

Al cargar la matriz, se emplea una función la cual verifica si en la matriz existen <b> más de una secuencia de cuatro letras iguales</b>, ya sea de forma oblicua, horizontal o vertical.

## Render:
Este proyecto fue deployado en Render: 

[https://parcialadnmutantes-1.onrender.com](https://parcialadnmutantes-1.onrender.com)

## Ejemplos para cargar ADN:

  Matriz mutante:
  
```json
{
    "dna": [
        "AAAAGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CACCGA",
        "TCACTG"
    ]
}
```

  Matriz NO mutante:
  
```json
  {
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATTT",
        "AGACGG",
        "GCGTCA",
        "TCACTG"
    ]
}
```
## Endpoints POST y GET

 <b>*POST /mutant*</b> - Recibe un JSON con la matriz de ADN a verificar.
```json
  {
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATTT",
        "AGACGG",
        "GCGTCA",
        "TCACTG"
    ]
}
```
<b> *GET /stats*<b> - Devuelve un JSON con la cantidad de mutantes y humanos verificados.
```json
{
     "countMutant": 30,
     "countHuman": 100,
     "ratio": 0.3,
     "totalDeRegistros": 130,
     "porcentajeDeMutantes": 30
}
```

