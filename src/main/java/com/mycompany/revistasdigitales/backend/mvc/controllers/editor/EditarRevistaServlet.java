package com.mycompany.revistasdigitales.backend.mvc.controllers.editor;

import com.mycompany.revistasdigitales.backend.database.EditorDB;
import com.mycompany.revistasdigitales.backend.database.RevistaDB;
import com.mycompany.revistasdigitales.backend.revistas.Revista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editarRevistaServlet")
public class EditarRevistaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro de la solicitud
        String nombreRevista = request.getParameter("nombreRevista");

        // Instancia de la clase que maneja la lógica de base de datos
        EditorDB editorDB = new EditorDB();

        // Buscar la revista en la base de datos
        Revista revista = editorDB.obtenerRevistaPorNombre(nombreRevista);

        if (revista != null) {
            // Pasar la revista al JSP como atributo
            request.setAttribute("revista", revista);
            request.getRequestDispatcher("/inicio/editor/editarRevista.jsp").forward(request, response);
        } else {
            // Si la revista no se encuentra, redirigir a una página de error o de lista de revistas
            response.sendRedirect("error.jsp");
        }
    }
}
