/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistasdigitales.backend.database;

import com.mycompany.revistasdigitales.backend.usuarios.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author melvin
 */
public class CarteraDB {

    private Connection connection;

    public CarteraDB() {
        try {
            this.connection = ConexionMySQL.conectar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * CREATE TABLE cartera_anunciantes (
     * id_cartera INT AUTO_INCREMENT PRIMARY KEY,
     * nombre_anunciante VARCHAR(50),
     * saldo DECIMAL(10, 2) DEFAULT 0,
     * FOREIGN KEY (nombre_anunciante) REFERENCES usuarios(nombre_usuario)
     * );
     * @param usuario
     */
    public void crearCartera(Usuario usuario) {
        
        String consulta = "INSERT INTO cartera_anunciantes (nombre_anunciante) VALUES (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setString(1, usuario.getNombreUsuario());
            statement.executeUpdate();
            System.out.println("Cartera creada con éxito");
        } catch (SQLException e) {
            System.out.println("Error al crear la cartera: " + e.getMessage());
        }
    }
}
