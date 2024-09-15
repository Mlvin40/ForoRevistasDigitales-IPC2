package com.mycompany.revistasdigitales.backend.registro;

import java.time.LocalDate;
import java.util.Date;

public class Usuario {
   private String nombreUsuario;
   private String contrasena;
   private String texto;
   private Rol rol;
   private LocalDate fechaCreacion;


    public Usuario(String nombreUsuario, String contrasena, String texto, Rol rol) {
         this.nombreUsuario = nombreUsuario;
         this.contrasena = guardarContrasena(contrasena);
         this.texto = texto;
         this.rol = rol;
         this.fechaCreacion = LocalDate.now();
    }

    private String guardarContrasena(String contrasena){
        Seguridad seguridad = new Seguridad();
        return seguridad.encriptarContrasena(contrasena);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
