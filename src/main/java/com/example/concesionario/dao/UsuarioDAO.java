package com.example.concesionario.dao;

import com.example.concesionario.model.Usuario;

public interface UsuarioDAO {
    Usuario login(String username, String password);
}
