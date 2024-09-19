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

@WebServlet(name = "ReportePopularidadServlet", urlPatterns = {"/reportePopularidad"})
public class ReportePopularidadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Crear una instancia de una clase de acceso a datos para las revistas
            RevistasPopulares revistaDB = new RevistasPopulares();
            List<Revista> revistasPopulares = revistaDB.revistasConLikes();

            // Establecer los datos en el atributo de solicitud
            request.setAttribute("revistasPopulares", revistasPopulares);

            // Redirigir a la vista JSP
            request.getRequestDispatcher("/inicio/administrador/revistasPopulares.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores
            request.setAttribute("error", "Error al obtener los datos: " + e.getMessage());
            request.getRequestDispatcher("/inicio/administrador/revistasPopulares.jsp").forward(request, response);
        }
    }
}