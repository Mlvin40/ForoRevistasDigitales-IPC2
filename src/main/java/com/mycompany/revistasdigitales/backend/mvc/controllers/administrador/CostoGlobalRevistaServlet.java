package com.mycompany.revistasdigitales.backend.mvc.controllers.administrador;

import com.mycompany.revistasdigitales.backend.database.RevistaDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/costoGlobalRevista")
public class CostoGlobalRevistaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crear una instancia de PrecioGlobalDB para obtener el precio actual
        RevistaDB revistaDB = new RevistaDB();
        double precioActual = revistaDB.establecerPrecioRevistaGlobal();

        // Establecer el precio actual en el atributo de solicitud
        request.setAttribute("precioActual", precioActual);

        // Redirigir a la vista JSP
        request.getRequestDispatcher("/inicio/administrador/costoGlobalRevista.jsp").forward(request, response);
    }
}