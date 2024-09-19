package com.mycompany.revistasdigitales.backend.mvc.controllers.suscriptor;

import com.mycompany.revistasdigitales.backend.database.ComentarioDB;
import com.mycompany.revistasdigitales.backend.database.MeGustaDB;
import com.mycompany.revistasdigitales.backend.database.SuscriptorDB;
import com.mycompany.revistasdigitales.backend.revistas.Comentario;
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

@WebServlet(name = "HomeSuscriptorServlet", urlPatterns = {"/homeSuscriptor"})
public class HomeSuscriptorServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el usuario actual desde la sesión
        HttpSession session = request.getSession();
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        // Verificar si el usuario está en sesión
        if (usuarioActual == null) {
            response.sendRedirect("portal/login.jsp");
            return;
        }

//           Verificar que sea un usuario suscriptor
//        if (!usuarioActual.getRol().equals("SUSCRIPTOR")) {
//            response.sendRedirect("portal/noAutorizado.jsp");
//            return;
//        }

        String nombreSuscriptor = usuarioActual.getNombreUsuario();

        //Consultar las revistas a las que está suscrito el usuario actual
        SuscriptorDB suscriptorDB = new SuscriptorDB();
        List<Revista> revistasSuscrito = suscriptorDB.obtenerRevistasPorSuscriptor(nombreSuscriptor);

        ComentarioDB comentarioDB = new ComentarioDB();
        MeGustaDB meGustaDB = new MeGustaDB();


        for (Revista revista : revistasSuscrito) {
            // Obtener la cantidad de likes
            int cantidadLikes = meGustaDB.obtenerCantidadMeGustaPorRevista(revista.getNombre());
            revista.setLikes(cantidadLikes);

            // Obtener los comentarios
            List<Comentario> comentarios = comentarioDB.obtenerComentariosPorRevista(revista.getNombre());
            revista.setComentarios(comentarios);
        }

        // Pasar la lista de revistas al JSP
        request.setAttribute("revistas", revistasSuscrito);

        // Redirigir a la vista de suscriptorHome.jsp
        request.getRequestDispatcher("/inicio/suscriptor/suscriptorHome.jsp").forward(request, response);
    }
}
