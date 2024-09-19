package com.mycompany.revistasdigitales.backend.mvc.controllers.suscriptor;

import com.mycompany.revistasdigitales.backend.database.RevistaDB;
import com.mycompany.revistasdigitales.backend.database.SuscriptorDB;
import com.mycompany.revistasdigitales.backend.revistas.Revista;
import com.mycompany.revistasdigitales.backend.usuarios.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MostradorRevistasDisponiblesServlet", urlPatterns = {"/suscribirseRevista"})
public class MostradorRevistasDisponiblesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        if (usuarioActual == null) {
            response.sendRedirect("portal/login.jsp");
            return;
        }

        String nombreUsuario = usuarioActual.getNombreUsuario();


        SuscriptorDB suscriptorDB = new SuscriptorDB();
        RevistaDB revistaDB = new RevistaDB();

        //obtiene las revistas a las que esta suscrito el usuario
        List<String> revistasSuscritas = suscriptorDB.obtenerRevistasSuscritas(nombreUsuario);

        //Obtien las revistas disponibles para suscribirse
        List<Revista> revistasDisponibles = revistaDB.obtenerRevistasDisponibles(revistasSuscritas);

        // Pasar la lista de revistas a la vista
        request.setAttribute("revistas", revistasDisponibles);

        // Redirigir a la vista de suscripción
        request.getRequestDispatcher("/inicio/suscriptor/suscribirseRevista.jsp").forward(request, response);
    }
}
