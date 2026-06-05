package com.example.concesionario.model;

import java.time.LocalDate;

/**
 * Clase que representa el modelo de un vehículo dentro del inventario del concesionario.
 * Almacena la información técnica y de fabricación básica de cada unidad.
 * @author Brian
 * @version 1.0
 */

public class Concesionario {
    //Atributos privados que definen las propiedades del vehículo
    private int numBastidor;
    private String marca;
    private LocalDate anno; //Utilizamos LocalDate para un manejo más preciso de las fechas
    private String userid;

    /**
     * Constructor completo para registrar un vehículo con todos sus datos.
     * @param numBastidor Número de idenrificación único de cada coche.
     * @param marca Marca de cada coche.
     * @param anno Fecha de fabricación de cada coche.
     */

    public Concesionario(int numBastidor, String marca, LocalDate anno) {
        this.numBastidor = numBastidor;
        this.marca = marca;
        this.anno = anno;
    }

    /**
     * Constructor sobrecargado sin el número de bastidor.
     * Útil para instanciar vehículos nuevos antes de que se les asigne un número de bastidor.
     * @param marca Marca de cada coche.
     * @param anno Fecha de fabricación de cada coche.
     */

    public Concesionario(String marca, LocalDate anno) {
        this.marca = marca;
        this.anno = anno;
    }

    /**
     * Obtiene el número de bastidor de cada coche.
     * @return
     */

    public int getNumBastidor() {
        return numBastidor;
    }

    /**
     * Actualiza el número de bastidor de cada coche.
     * @param numBastidor nuevo número de bastidor a asignar.
     */

    public void setNumBastidor(int numBastidor) {
        this.numBastidor = numBastidor;
    }

    /**
     * Obtiene la marca de cada coche.
     * @return Cadena de texto con la marca.
     */

    public String getMarca() {
        return marca;
    }

    /**
     * Modifica la marca de cada coche
     * @param marca Nueva marca del coche
     */

    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene la fecha de fabricación de cada coche.
     * @return Objeto LocalDate con la fecha de fabricación.
     */

    public LocalDate getAnno() {
        return anno;
    }

    /**
     * Establece la fecha de fabricación del coche.
     * @param anno Nueva fecha de fabricación en formato LocalDate
     */

    public void setAnno(LocalDate anno) {
        this.anno = anno;
    }


    /**
     * Obtiene el identificador del usuario del coche
     * @return Identificador del usuario del coche
     */
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
