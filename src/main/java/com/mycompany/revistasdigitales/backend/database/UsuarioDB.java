package com.mycompany.revistasdigitales.backend.database;

import com.mycompany.revistasdigitales.backend.registro.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDB {

    private Connection connection;

    public UsuarioDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean registrarUsuario(Usuario usuario) {
        if (existeUsuario(usuario.getNombreUsuario())) {
            return false;
            //Significa que el usuario ya existe en la base de datos
        }

        String consulta = "INSERT INTO usuario (nombre_usuario, contrasena, texto, rol) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getContrasena());
            statement.setString(3, usuario.getTexto());
            statement.setString(4, usuario.getRol().toString());

            // Si la inserción fue exitosa, executeUpdate devuelve el número de filas afectadas
            int filasAfectadas = statement.executeUpdate();

            // Si se afectó al menos una fila, devuelve true, significa que la inserción fue exitosa
            return filasAfectadas > 0;
        } catch (SQLException e) {
            // Si ocurre una excepción, puedes manejarla y devolver false
            System.out.println("Error al registrar usuario");
            return false;
        }
    }

    public boolean existeUsuario(String nombreUsuario) {
        try {
            String consulta = "SELECT COUNT(*) FROM usuario WHERE nombre_usuario = ?";
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, nombreUsuario);
            return statement.executeQuery().getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
