package com.mycompany.revistasdigitales.backend.mvc.controllers.suscriptor;

import com.mycompany.revistasdigitales.backend.database.UsuarioDB;
import com.mycompany.revistasdigitales.backend.usuarios.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PerfilAutorServlet", urlPatterns = {"/perfilAutor"})
public class PerfilAutorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del autor desde la solicitud
        String autorId = request.getParameter("autorId");

        // Verificar que el ID del autor no sea nulo o vacío
        if (autorId == null || autorId.trim().isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Obtener la información del autor desde la base de datos
        UsuarioDB usuarioDB = new UsuarioDB();
        Usuario autor = usuarioDB.obtenerUsuario(autorId);

        // Verificar si se obtuvo el autor correctamente
        if (autor == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        request.setAttribute("perfil", autor);

        // Redirigir a la vista perfilAutor.jsp
        request.getRequestDispatcher("portal/perfilAutor.jsp").forward(request, response);
    }
}
