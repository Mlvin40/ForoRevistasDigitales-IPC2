package com.mycompany.revistasdigitales.backend.revistas;

import com.mycompany.revistasdigitales.backend.database.RevistaDB;
import com.mycompany.revistasdigitales.backend.database.UsuarioDB;

import java.time.LocalDate;

public class Revista {

    private String nombre;
    //la descripcion de la revista es el contenido de la revista
    private String descripcion;
    private String categoria;
    private String fechaCreacion;
    private String autor;
    private double costo;
    private String archivoPDF;
    private boolean estadoComentar;
    private boolean estadoMeGusta;
    private boolean estadoSuscribirse;


    //Constructor para crear una revista
    public Revista(String nombre, String descripcion, String categoria, String fechaCreacion, String autor, String archivoPDF) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.costo = establecerCosto();
        this.archivoPDF = archivoPDF;
    }

    //Constructor para recuperar una revista de la base de datos


    public Revista(String nombre, String descripcion, String categoria, String fechaCreacion, String autor, double costo, String archivoPDF, boolean estadoComentar, boolean estadoMeGusta, boolean estadoSuscribirse) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.costo = costo;
        this.archivoPDF = archivoPDF;
        this.estadoComentar = estadoComentar;
        this.estadoMeGusta = estadoMeGusta;
        this.estadoSuscribirse = estadoSuscribirse;
    }

    private double establecerCosto(){
        RevistaDB revistaDB = new RevistaDB();
        return revistaDB.establecerPrecioRevista();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getArchivoPDF() {
        return archivoPDF;
    }

    public void setArchivoPDF(String archivoPDF) {
        this.archivoPDF = archivoPDF;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isEstadoComentar() {
        return estadoComentar;
    }

    public void setEstadoComentar(boolean estadoComentar) {
        this.estadoComentar = estadoComentar;
    }

    public boolean isEstadoMeGusta() {
        return estadoMeGusta;
    }

    public void setEstadoMeGusta(boolean estadoMeGusta) {
        this.estadoMeGusta = estadoMeGusta;
    }

    public boolean isEstadoSuscribirse() {
        return estadoSuscribirse;
    }

    public void setEstadoSuscribirse(boolean estadoSuscribirse) {
        this.estadoSuscribirse = estadoSuscribirse;
    }
}
