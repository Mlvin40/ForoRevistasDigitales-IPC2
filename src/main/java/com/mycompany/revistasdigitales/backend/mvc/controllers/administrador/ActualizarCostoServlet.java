package com.mycompany.revistasdigitales.backend.mvc.controllers.administrador;

import com.mycompany.revistasdigitales.backend.database.RevistaDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ActualizarCostoServlet", urlPatterns = {"/actualizarCostoGlobal"})
public class ActualizarCostoServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener el nuevo precio del formulario
            double nuevoPrecio = Double.parseDouble(request.getParameter("nuevoPrecio"));

            if (nuevoPrecio < 0) {
                request.setAttribute("mensaje", "El precio no puede ser negativo.");
            }
            else {
                // Crear una instancia de PrecioGlobalDB y actualizar el precio
                RevistaDB revistaDB = new RevistaDB();
                revistaDB.actualizarPrecioRevistaGlobal(nuevoPrecio);

                // Agregar un mensaje de éxito
                request.setAttribute("mensaje", "Precio actualizado exitosamente.");
            }
        } catch (Exception e) {
            // Agregar un mensaje de error
            request.setAttribute("mensaje", "Error al actualizar el precio: " + e.getMessage());
        }

        request.getRequestDispatcher("/inicio/administrador/costoPorRevista.jsp").forward(request, response);
    }
}
