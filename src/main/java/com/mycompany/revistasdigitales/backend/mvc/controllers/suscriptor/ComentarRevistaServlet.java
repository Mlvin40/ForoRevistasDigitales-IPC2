package com.mycompany.revistasdigitales.backend.mvc.controllers.suscriptor;

import com.mycompany.revistasdigitales.backend.database.ComentarioDB;
import com.mycompany.revistasdigitales.backend.usuarios.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

@WebServlet("/comentarRevistaServlet")
public class ComentarRevistaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String nombreRevista = request.getParameter("revistaId");
        String comentarioTexto = request.getParameter("comentarioTexto");
        //HttpSession session = request.getSession(false);

        HttpSession session = request.getSession();
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        if (session == null) {
            response.sendRedirect("portal/login.jsp");
            return;
        }

        //ver que el comentario no este vacio
        if (comentarioTexto == null || comentarioTexto.trim().isEmpty()) {
            response.sendRedirect("homeSuscriptor");
            return;
        }

        //almacenar el comentario en la base de datos
        ComentarioDB comentarioDB = new ComentarioDB();
        comentarioDB.guardarComentario(nombreRevista, usuarioActual.getNombreUsuario(), comentarioTexto);
        response.sendRedirect("homeSuscriptor");
    }
}

