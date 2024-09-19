package com.mycompany.revistasdigitales.backend.mvc.controllers.editor;

import com.mycompany.revistasdigitales.backend.database.EditorDB;
import com.mycompany.revistasdigitales.backend.revistas.Revista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/actualizarRevistaServlet")
public class ActualizarRevistaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");
        boolean estadoComentar = request.getParameter("estado_comentar") != null;
        boolean estadoMeGusta = request.getParameter("estado_me_gusta") != null;
        boolean estadoSuscribirse = request.getParameter("estado_suscribirse") != null;

        // Aquí actualizas la revista con los valores obtenidos

        EditorDB editorDB = new EditorDB();
        Revista revista = editorDB.obtenerRevistaPorNombre(nombre);

        // Verificar que la revista exista
        if (revista != null) {
            revista.setDescripcion(descripcion);
            revista.setCategoria(categoria);
            revista.setEstadoComentar(estadoComentar);
            revista.setEstadoMeGusta(estadoMeGusta);
            revista.setEstadoSuscribirse(estadoSuscribirse);

            // Guardar los cambios en la base de datos
            editorDB.actualizarRevista(revista);
        }
        // Redirigir a la página de inicio del editor
        response.sendRedirect("homeEditor");
    }
}
