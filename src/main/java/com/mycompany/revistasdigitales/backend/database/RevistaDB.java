package com.mycompany.revistasdigitales.backend.database;

import com.mycompany.revistasdigitales.backend.revistas.Revista;
import jakarta.servlet.jsp.tagext.TryCatchFinally;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
     * CREATE TABLE revistas ( nombre_revista VARCHAR(100) PRIMARY KEY,
     * descripcion TEXT, categoria VARCHAR(100), fecha_creacion DATE NOT NULL,
     * id_autor VARCHAR(50), FOREIGN KEY (id_autor) REFERENCES
     * usuarios(nombre_usuario), url_pdf VARCHAR(200) not null );
     *
     * @param revista
     * @return
     */
    public boolean crearRevista(Revista revista) {
        if (existeRevista(revista.getNombre())) {
            return false;
            // Significa que la revista ya existe en la base de datos
        }

        String consulta = "INSERT INTO revistas (nombre_revista, descripcion, categoria, fecha_creacion, id_autor, costo, url_pdf) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, revista.getNombre());
            statement.setString(2, revista.getDescripcion());
            statement.setString(3, revista.getCategoria());

            // Convertir la fecha de String a java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(revista.getFechaCreacion()); // Asegúrate que revista.getFechaCreacion() esté en formato "YYYY-MM-DD"
            
            statement.setDate(4, sqlDate);
            statement.setString(5, revista.getAutor()); 
            statement.setDouble(6, revista.getCosto());
            statement.setString(7, revista.getArchivoPDF());
            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            // Si ocurre una excepción, puedes manejarla y devolver false
            System.out.println("Error al crear revista: " + e.getMessage());
            return false;
        }
    }

    public boolean existeRevista(String nombreRevista) {
        String consulta = "SELECT COUNT(*) FROM revistas WHERE nombre_revista = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
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
    public Revista obtenerRevista(String nombreRevista) {
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
                    return new Revista(nombre, descripcion, categoria, fechaCreacion, autor, archivoPDF);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar revista: " + e.getMessage());
        }
        return null;
    }

    /**
     * CREATE TABLE precio_global_revistas( costo DECIMAL(10, 2) DEFAULT 0);
     */
    public double establecerPrecioRevista() {
        String consulta = "SELECT costo FROM precio_global_revistas";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("costo");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar precio de revistas: " + e.getMessage());
        }
        return 0;
    }

    public boolean actualizarPrecioRevista(double precio) {
        String consulta = "UPDATE precio_global_revistas SET costo = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setDouble(1, precio);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar precio de revistas: " + e.getMessage());
            return false;
        }
    }

}
