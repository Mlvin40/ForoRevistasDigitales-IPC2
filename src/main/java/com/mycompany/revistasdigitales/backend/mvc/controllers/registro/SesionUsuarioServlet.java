package com.mycompany.revistasdigitales.backend.mvc.controllers.registro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cerrarSesion")
public class SesionUsuarioServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Invalidar la sesión del usuario actual
        request.getSession().invalidate();

        // Redirigir al portal principal (index.jsp)
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
