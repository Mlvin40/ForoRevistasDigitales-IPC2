package com.mycompany.revistasdigitales.backend.registro;

public class Suscriptor extends Usuario{

    public Suscriptor(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena, "", Rol.SUSCRIPTOR);
    }
}
