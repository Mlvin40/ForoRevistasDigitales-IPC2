package com.mycompany.revistasdigitales.backend.mvc.controllers.registro;

import com.mycompany.revistasdigitales.backend.database.UsuarioDB;
import com.mycompany.revistasdigitales.backend.usuarios.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "PerfilUsuarioServlet", urlPatterns = {"/perfilUsuario"})
public class PerfilUsuarioServlet extends HttpServlet {

//    este servlet se encarga de mostrar la información del perfil del usuario en la vista perfilUsuario.jsp
//    perfil de la sesion actual

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //castear el usuario a la sesion actual
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        //Verificar si el usuario está en sesión
        if (usuarioActual == null) {
            response.sendRedirect("portal/login.jsp");
            return;
        }

        UsuarioDB usuarioDB = new UsuarioDB();

        //Obtener el perfil del usuario actual
        Usuario perfil = usuarioDB.obtenerUsuario(usuarioActual.getNombreUsuario());

        //Agregar el perfil a la solicitud
        request.setAttribute("perfil", perfil);

        //Redirigir a la vista perfilUsuario.jsp
        request.getRequestDispatcher("portal/perfilUsuario.jsp").forward(request, response);
    }
}
