package com.mycompany.revistasdigitales.backend.mvc.controllers.administrador;

import com.mycompany.revistasdigitales.backend.database.RevistaDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ActualizadorPrecioRevistaServlet", urlPatterns = {"/actualizarPrecioRevista"})
public class ActualizadorPrecioRevistaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Obtener los datos del formulario
            String nombreRevista = request.getParameter("nombreRevista");
            double nuevoPrecio = Double.parseDouble(request.getParameter("nuevoPrecio"));

            // Actualizar el precio en la base de datos

            if(nuevoPrecio < 0){
                request.setAttribute("mensaje", "El precio no puede ser negativo.");
                response.sendRedirect(request.getContextPath() + "/listaRevistas");
                return;
            }

            RevistaDB revistaDB = new RevistaDB();
            revistaDB.actualizarPrecioRevista(nombreRevista, nuevoPrecio);

            // Agregar un mensaje de éxito
            request.setAttribute("mensaje", "Precio actualizado exitosamente.");


        // Redirigir de vuelta a la lista de revistas
        response.sendRedirect(request.getContextPath() + "/listaRevistas");
    }
}


