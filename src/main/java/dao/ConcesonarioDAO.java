package dao;

import model.Concesionario;

import java.util.List;

public interface ConcesonarioDAO {
    boolean addCoche(Concesionario coche);
    boolean deleteCoche(int numBastidor);
    boolean updateCoche(Concesionario coche);
    List<Concesionario> getAllCoches();
}
