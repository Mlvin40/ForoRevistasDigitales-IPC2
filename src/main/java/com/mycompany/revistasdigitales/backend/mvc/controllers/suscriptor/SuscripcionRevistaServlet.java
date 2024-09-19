package com.mycompany.revistasdigitales.backend.mvc.controllers.suscriptor;

import com.mycompany.revistasdigitales.backend.database.SuscriptorDB;
import com.mycompany.revistasdigitales.backend.usuarios.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "SuscripcionRevistaServlet", urlPatterns = {"/suscribirRevistaServlet"})
public class SuscripcionRevistaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el usuario actual desde la sesión
        HttpSession session = request.getSession();
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        // Verificar si el usuario está en sesión
        if (usuarioActual == null) {
            response.sendRedirect("portal/login.jsp");
            return;
        }

        // Obtener el nombre de la revista desde el formulario
        String nombreUsuario = usuarioActual.getNombreUsuario();
        String nombreRevista = request.getParameter("nombreRevista");
        String fechaSuscripcion = request.getParameter("fechaSuscripcion");


        if (nombreRevista == null || nombreRevista.isEmpty()) {
            request.setAttribute("mensaje", "Nombre de la revista no válido.");
            return;
        }

        SuscriptorDB suscriptorDB = new SuscriptorDB();

        // Verificar si el usuario ya está suscrito a la revista
        boolean yaSuscrito = suscriptorDB.verificarEstaSuscrito(nombreUsuario, nombreRevista);

        if (yaSuscrito) {
            request.setAttribute("mensaje", "Ya estás suscrito a esta revista.");
        } else {
            // Verificar si la revista permite suscripciones
            SuscriptorDB suscritorDB = new SuscriptorDB();
            boolean suscripcionPermitida = suscritorDB.verificarEstadoSuscripcion(nombreRevista); // Consulta si la revista permite suscripciones

            if (!suscripcionPermitida) {
                request.setAttribute("mensaje", "Esta revista no permite suscripciones.");
            } else {
                boolean suscripcionExitosa = suscriptorDB.suscribirUsuarioARevista(nombreUsuario, nombreRevista, fechaSuscripcion);
                if (suscripcionExitosa) {
                    request.setAttribute("mensaje", "Suscripción realizada con éxito.");
                } else {
                    request.setAttribute("mensaje", "Error al suscribirse a la revista. Por favor, intenta nuevamente.");
                }
            }
        }

        //se envie el mensaje capturado a la vista
        request.getRequestDispatcher("/inicio/suscriptor/mensajeSuscripcion.jsp").forward(request, response);
    }
}




