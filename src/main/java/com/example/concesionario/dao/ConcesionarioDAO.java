package com.example.concesionario.dao;

import com.example.concesionario.model.Concesionario;

import java.util.List;

public interface ConcesionarioDAO {
    boolean addCoche(Concesionario coche);
    boolean deleteCoche(int numBastidor);
    boolean updateCoche(Concesionario coche);
    List<Concesionario> getAllCoches();
}
