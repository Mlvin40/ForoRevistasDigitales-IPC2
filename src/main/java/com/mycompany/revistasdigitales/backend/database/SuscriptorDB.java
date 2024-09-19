package com.mycompany.revistasdigitales.backend.database;

import com.mycompany.revistasdigitales.backend.revistas.Revista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuscriptorDB {

    private static Connection connection;

    public SuscriptorDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Revista> obtenerRevistasPorSuscriptor(String nombreUsuario) {
        List<Revista> revistas = new ArrayList<>();

        // Consulta que une las tablas "revistas" y "suscripciones" usando "nombre_usuario" y "nombre_revista"
        String consulta = "SELECT r.* FROM revistas r "
                + "JOIN suscripciones s ON r.nombre_revista = s.nombre_revista "
                + "WHERE s.nombre_usuario = ? AND s.estado = 'ACTIVA'";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            // Establecer el valor del nombre de usuario (suscriptor)
            statement.setString(1, nombreUsuario);

            try (ResultSet resultSet = statement.executeQuery()) {
                // Iterar sobre el resultado y agregar las revistas a la lista
                while (resultSet.next()) {
                    Revista revista = new Revista(
                            resultSet.getString("nombre_revista"),
                            resultSet.getString("descripcion"),
                            resultSet.getString("categoria"),
                            resultSet.getDate("fecha_creacion").toString(),
                            resultSet.getString("id_autor"),
                            resultSet.getDouble("costo"),
                            resultSet.getString("url_pdf"),
                            resultSet.getBoolean("estado_comentar"),
                            resultSet.getBoolean("estado_megusta"),
                            resultSet.getBoolean("estado_suscribirse")
                    );
                    revistas.add(revista);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener revistas por suscriptor: " + e.getMessage());
        }

        return revistas;
    }


    // Método para obtener las revistas a las que el usuario está suscrito
    public List<String> obtenerRevistasSuscritas(String nombreUsuario) {
        List<String> revistasSuscritas = new ArrayList<>();
        String consulta = "SELECT nombre_revista FROM suscripciones WHERE nombre_usuario = ?";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    revistasSuscritas.add(resultSet.getString("nombre_revista"));
                }
            }
        }
        catch (SQLException e) {
            System.out.println("Error al obtener revistas suscritas: " + e.getMessage());
        }
        return revistasSuscritas;
    }

    // Método para suscribirse a una revista
    public boolean suscribirUsuarioARevista(String nombreUsuario, String nombreRevista, String fechaSuscripcion) {
        String consulta = "INSERT INTO suscripciones (nombre_usuario, nombre_revista, fecha_suscripcion) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreUsuario);
            statement.setString(2, nombreRevista);
            statement.setString(3, fechaSuscripcion);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0;
        }catch (SQLException e) {
            System.out.println("Error al suscribirse a la revista: " + e.getMessage());
            return false;
        }
    }

    //metodo que se encarga de ver si el usuario ya esta suscrito a la revista
    public boolean verificarEstaSuscrito(String nombreUsuario, String nombreRevista) {
        String consulta = "SELECT * FROM suscripciones WHERE nombre_usuario = ? AND nombre_revista = ? ";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreUsuario);
            statement.setString(2, nombreRevista);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Retorna true si existe una suscripción activa
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar suscripción: " + e.getMessage());
            return false;
        }
    }

    public boolean verificarEstadoSuscripcion(String nombreRevista) {
        String consulta = "SELECT estado_suscribirse FROM revistas WHERE nombre_revista = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("estado_suscribirse"); // Verifica si las suscripciones están permitidas
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el estado de suscripciones: " + e.getMessage());
        }
        return false; // Retorna false si no encuentra la revista o hay un error
    }

}
