package com.mycompany.revistasdigitales.backend.mvc.controllers.administrador;

import com.mycompany.revistasdigitales.backend.database.AdministradorDB;
import com.mycompany.revistasdigitales.backend.database.RevistaDB;
import com.mycompany.revistasdigitales.backend.revistas.Revista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListaRevistasServlet", urlPatterns = {"/listaRevistas"})
public class ListaRevistasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la lista de revistas
        AdministradorDB administradorDB = new AdministradorDB();
        List<Revista> revistas = administradorDB.obtenerTodasLasRevistas();

        // Pasar la lista de revistas a la vista JSP
        request.setAttribute("revistas", revistas);

        // Redirigir a la vista JSP
        request.getRequestDispatcher("/inicio/administrador/costoPorRevista.jsp").forward(request, response);
    }
}
