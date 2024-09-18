package com.mycompany.revistasdigitales.backend.database;

import com.mycompany.revistasdigitales.backend.revistas.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditorDB {
    private Connection connection;

    public EditorDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   
    
    public List<Revista> obtenerRevistasPorAutor(String idAutor) {
        List<Revista> revistas = new ArrayList<>();
        String consulta = "SELECT * FROM revistas WHERE id_autor = ?";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, idAutor);
            ResultSet resultSet = statement.executeQuery();

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revistas;
    }

    public Revista obtenerRevistaPorNombre(String nombreRevista) {
        String consulta = "SELECT * FROM revistas WHERE nombre_revista = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreRevista);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
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
                    return revista;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar revista: " + e.getMessage());
        }
        return null;
    }

    public void actualizarRevista(Revista revista) {
        String consulta = "UPDATE revistas SET descripcion = ?, categoria = ?, estado_comentar = ?, estado_megusta = ?, estado_suscribirse = ? WHERE nombre_revista = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, revista.getDescripcion());
            statement.setString(2, revista.getCategoria());
            statement.setBoolean(3, revista.isEstadoComentar());
            statement.setBoolean(4, revista.isEstadoMeGusta());
            statement.setBoolean(5, revista.isEstadoSuscribirse());
            statement.setString(6, revista.getNombre());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar revista: " + e.getMessage());
        }
    }
}
