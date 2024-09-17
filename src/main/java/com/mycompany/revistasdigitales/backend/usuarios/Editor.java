package com.mycompany.revistasdigitales.backend.usuarios;

public class Editor extends Usuario {

    public Editor(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, Rol.EDITOR);
    }
}
