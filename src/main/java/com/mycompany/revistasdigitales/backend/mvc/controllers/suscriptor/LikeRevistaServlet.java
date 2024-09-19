package com.mycompany.revistasdigitales.backend.mvc.controllers.suscriptor;

import com.mycompany.revistasdigitales.backend.database.MeGustaDB;
import com.mycompany.revistasdigitales.backend.database.RevistaDB;
import com.mycompany.revistasdigitales.backend.usuarios.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/likeRevistaServlet")
public class LikeRevistaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String nombreRevista = request.getParameter("revistaNombre");

        // Obtener la sesión actual
        //HttpSession session = request.getSession(false);
        HttpSession session = request.getSession();
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        // Verificar que la sesión esté activa y que el usuario esté autenticado
//        if (session == null || session.getAttribute("nombreUsuario") == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }

        // Obtener el nombre del usuario autenticado
        String nombreUsuario = (String) session.getAttribute("nombreUsuario");

        // Verificar que el nombre de la revista no sea nulo o vacío
//        if (nombreRevista == null || nombreRevista.isEmpty()) {
//            request.setAttribute("error", "Datos inválidos.");
//            request.getRequestDispatcher("homeSuscriptor").forward(request, response);
//            return;
//        }

        // Dar like a la revista en la base de datos
        try {
            MeGustaDB meGustaDB = new MeGustaDB();
            meGustaDB.darLike(nombreRevista, nombreUsuario); // Registrar el like

            // Redirigir al home del suscriptor o mostrar un mensaje de éxito
            response.sendRedirect("homeSuscriptor");
        } catch (SQLException e) {
            throw new ServletException("Error al dar like a la revista", e);
        }
    }
}


