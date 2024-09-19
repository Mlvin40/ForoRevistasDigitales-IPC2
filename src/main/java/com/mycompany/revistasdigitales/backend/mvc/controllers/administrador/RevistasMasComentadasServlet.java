package com.mycompany.revistasdigitales.backend.mvc.controllers.administrador;

import com.mycompany.revistasdigitales.backend.reportes.RevistasPopulares;
import com.mycompany.revistasdigitales.backend.revistas.Revista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RevistasMasComentadasServlet", urlPatterns = {"/revistasMasComentadas"})
public class RevistasMasComentadasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // este metodo debe de obtener las 5 revistas con mas comentarios ordenadas de mayor a menor
            RevistasPopulares revistasPopulares = new RevistasPopulares();
            List<Revista> revistas = revistasPopulares.revistasConComentarios();

            request.setAttribute("revistas", revistas);
            // Redirigir a la vista JSP
            request.getRequestDispatcher("/inicio/administrador/revistasMasComentadas.jsp").forward(request, response);
    }
}
