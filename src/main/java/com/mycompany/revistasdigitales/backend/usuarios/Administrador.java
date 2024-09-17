package com.mycompany.revistasdigitales.backend.usuarios;

public class Administrador extends Usuario {

    public Administrador(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, Rol.ADMINISTRADOR);
    }
}