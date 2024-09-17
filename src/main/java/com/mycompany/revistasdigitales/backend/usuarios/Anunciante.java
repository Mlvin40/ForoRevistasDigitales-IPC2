package com.mycompany.revistasdigitales.backend.usuarios;

public class Anunciante extends Usuario {

    public Anunciante(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, Rol.ANUNCIANTE);
    }
}
