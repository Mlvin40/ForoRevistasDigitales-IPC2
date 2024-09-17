package com.mycompany.revistasdigitales.backend.database;

import com.mycompany.revistasdigitales.backend.revistas.Revista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RevistaDB {

    private Connection connection;
    public RevistaDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     CREATE TABLE revistas (
     nombre_revista VARCHAR(100) PRIMARY KEY,
     descripcion TEXT,
     categoria VARCHAR(100),
     fecha_creacion DATE NOT NULL,
     id_autor VARCHAR(50),
     FOREIGN KEY (id_autor) REFERENCES usuarios(nombre_usuario),
     url_pdf VARCHAR(200) not null
     );
     * @param revista
     * @return
     */

    public boolean crearRevista(Revista revista){
        if (existeRevista(revista)) {
            return false;
            // Significa que la revista ya existe en la base de datos
        }

        String consulta = "INSERT INTO revistas (nombre_revista, descripcion, contenido, categoria, fecha_creacion, id_autor, url_pdf) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {

            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, revista.getNombre());
            statement.setString(2, revista.getDescripcion());
            statement.setString(3, revista.getContenido());
            statement.setString(4, revista.getCategoria());
            statement.setString(5, revista.getFechaCreacion());
            statement.setString(6, revista.getAutor());
            statement.setString(7, revista.getArchivoPDF());

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            // Si ocurre una excepción, puedes manejarla y devolver false
            System.out.println("Error al crear revista: " + e.getMessage());
            return false;
        }
    }

    private boolean existeRevista(Revista revista){
        String consulta = "SELECT COUNT(*) FROM revistas WHERE nombre_revista = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, revista.getNombre());
            try (java.sql.ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Devuelve true si ya existe la revista
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar revista: " + e.getMessage());
            return false;
        }
        return false;
    }

    //Para obtener una revista de la base de datos, si devuelve null es porque no existe la revista
    public Revista obtenerRevista(String nombreRevista){
        String consulta = "SELECT * FROM revistas WHERE nombre_revista = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            try (java.sql.ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre_revista");
                    String descripcion = resultSet.getString("descripcion");
                    String contenido = resultSet.getString("contenido");
                    String categoria = resultSet.getString("categoria");
                    String fechaCreacion = resultSet.getString("fecha_creacion");
                    String autor = resultSet.getString("id_autor");
                    String archivoPDF = resultSet.getString("url_pdf");
                    return new Revista(nombre, descripcion, categoria, contenido, fechaCreacion, autor, archivoPDF);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar revista: " + e.getMessage());
        }
        return null;
    }
}
