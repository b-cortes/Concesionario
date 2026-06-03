package com.example.concesionario.dao;


import com.example.concesionario.model.Usuario;

public interface UsuarioDAO {
    Usuario login(String user, String password);
    public void insertar (Usuario usuario);


}
