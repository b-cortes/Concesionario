package model;

import java.time.LocalDate;

public class Concesionario {
    private int numBastidor;
    private String marca;
    private LocalDate anno;

    public Concesionario(int numBastidor, String marca, LocalDate anno) {
        this.numBastidor = numBastidor;
        this.marca = marca;
        this.anno = anno;
    }

    // Constructor sin ID para cuando lo insertamos nuevo
    public Concesionario(String marca, LocalDate anno) {
        this.marca = marca;
        this.anno = anno;
    }

    // Getters y Setters

    public int getNumBastidor() {
        return numBastidor;
    }

    public void setNumBastidor(int numBastidor) {
        this.numBastidor = numBastidor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getAnno() {
        return anno;
    }

    public void setAnno(LocalDate anno) {
        this.anno = anno;
    }
}
