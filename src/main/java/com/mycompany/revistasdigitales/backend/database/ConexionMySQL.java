/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistasdigitales.backend.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author melvin
 */
public class ConexionMySQL {

    private static final String URL_MYSQL = "jdbc:mysql://localhost:3306/foro_revistas";
    private static final String USER = "root";
    private static final String PASSWORD = "123";

    private static Connection connection;

    public static Connection conectar() throws SQLException {
        try {
            // Registro del driver explícitamente
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
            //System.out.println("Conexión a la base de datos exitosa");
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado", e);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Error al conectar con la base de datos", ex);
        }
    }
}
