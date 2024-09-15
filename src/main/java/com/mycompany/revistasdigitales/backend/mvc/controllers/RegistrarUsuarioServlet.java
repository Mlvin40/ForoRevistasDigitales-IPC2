package com.mycompany.revistasdigitales.backend.mvc.controllers;

import com.mycompany.revistasdigitales.backend.database.UsuarioDB;
import com.mycompany.revistasdigitales.backend.registro.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");
        String rol = request.getParameter("rol");

        Usuario usuario;

        switch (rol) {
            case "Suscriptor":
                usuario = new Suscriptor(nombreUsuario, contrasena);
                break;
            case "Editor":
                usuario = new Editor(nombreUsuario, contrasena);
                break;
            case "Anunciante":
                usuario = new Anunciante(nombreUsuario, contrasena);
                break;
            case "Administrador":
                usuario = new Administrador(nombreUsuario, contrasena);
                break;
            default:
                //Error
                break;
        }

        UsuarioDB usuarioDB = new UsuarioDB();
        boolean registrado = usuarioDB.registrarUsuario(usuario);

        if (registrado) {
            response.sendRedirect("inicio.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}



