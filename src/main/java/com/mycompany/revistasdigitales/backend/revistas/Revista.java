package com.mycompany.revistasdigitales.backend.revistas;

import java.time.LocalDate;

public class Revista {

    private String nombre;
    //la descripcion de la revista es el contenido de la revista
    private String descripcion;
    private String categoria;
    private String contenido;
    private String fechaCreacion;
    private String autor;
    private String archivoPDF;


    public Revista(String nombre, String descripcion, String categoria, String contenido, String fechaCreacion, String autor, String archivoPDF) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.archivoPDF = archivoPDF;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getArchivoPDF() {
        return archivoPDF;
    }

    public void setArchivoPDF(String archivoPDF) {
        this.archivoPDF = archivoPDF;
    }
}
