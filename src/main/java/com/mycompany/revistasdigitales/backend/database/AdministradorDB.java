package com.mycompany.revistasdigitales.backend.database;

import com.mycompany.revistasdigitales.backend.revistas.Revista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdministradorDB {
    private Connection connection;

    public AdministradorDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Revista> obtenerTodasLasRevistas() {
        List<Revista> revistas = new ArrayList<>();
        String consulta = "SELECT * FROM revistas";

        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
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
}
