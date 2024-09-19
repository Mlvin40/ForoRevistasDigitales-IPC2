package com.mycompany.revistasdigitales.backend.revistas;

public class MeGusta {

    private String nombreRevista;
    private String nombreUsuario;
    private String fechaMeGusta;


    public MeGusta(String nombreRevista, String nombreUsuario, String fechaMeGusta) {
        this.nombreRevista = nombreRevista;
        this.nombreUsuario = nombreUsuario;
        this.fechaMeGusta = fechaMeGusta;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFechaMeGusta() {
        return fechaMeGusta;
    }

    public void setFechaMeGusta(String fechaMeGusta) {
        this.fechaMeGusta = fechaMeGusta;
    }
}
