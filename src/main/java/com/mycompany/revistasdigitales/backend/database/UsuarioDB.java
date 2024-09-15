package com.mycompany.revistasdigitales.backend.database;

import com.mycompany.revistasdigitales.backend.registro.Rol;
import com.mycompany.revistasdigitales.backend.registro.Seguridad;
import com.mycompany.revistasdigitales.backend.registro.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            // Significa que el usuario ya existe en la base de datos
        }

        String consulta = "INSERT INTO usuarios (nombre_usuario, contraseña, perfil, rol) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, usuario.getNombreUsuario());
            statement.setString(2, usuario.getContrasena());
            statement.setString(3, usuario.getTexto()); // Se usa String aquí
            statement.setString(4, usuario.getRol().toString());
            
            // Si la inserción fue exitosa, executeUpdate devuelve el número de filas afectadas
            int filasAfectadas = statement.executeUpdate();

            // Si se afectó al menos una fila, devuelve true, significa que la inserción fue exitosa
            return filasAfectadas > 0;
        } catch (SQLException e) {
            // Si ocurre una excepción, puedes manejarla y devolver false
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean existeUsuario(String nombreUsuario) {
        String consulta = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1); // Obtiene el conteo de usuarios
                    return count > 0; // Devuelve true si hay uno o más usuarios
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false; // Devuelve false si no hay usuarios o se produjo un error
    }

    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        Seguridad seguridad = new Seguridad();
        
        String consulta = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, nombreUsuario);
            //statement.setString(2, contrasena);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("nombre_usuario");
                    String password = resultSet.getString("contraseña");
                    String texto = resultSet.getString("perfil");
                    String rol = resultSet.getString("rol");

                    System.out.println(contrasena);
                    System.out.println(password);
                    // Si la contraseña coincide con el hash almacenado, devuelve un objeto Usuario
                    if(seguridad.verificarContrasena(contrasena, password)){
                        return new Usuario(nombre, contrasena, texto, Rol.valueOf(rol));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Si no se encontró un usuario con las credenciales dadas, devuelve null
        return null;
    }
}
