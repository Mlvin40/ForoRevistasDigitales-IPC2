package com.mycompany.revistasdigitales.backend.mvc.controllers;

import com.mycompany.revistasdigitales.backend.database.UsuarioDB;
import com.mycompany.revistasdigitales.backend.registro.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombre_usuario");
        String contrasena = request.getParameter("contrasena");

        UsuarioDB usuarioDB = new UsuarioDB();
        Usuario usuario = usuarioDB.iniciarSesion(nombreUsuario, contrasena);

        if (usuario != null) {
            // Guardar el usuario en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);

            // Redirigir a la página principal
            response.sendRedirect("index.jsp");
        } else {
            // Si el login falla, pasar el mensaje de error al JSP y reenviar la solicitud
            request.setAttribute("errorMensaje", "Credenciales incorrectas, por favor intenta nuevamente.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
