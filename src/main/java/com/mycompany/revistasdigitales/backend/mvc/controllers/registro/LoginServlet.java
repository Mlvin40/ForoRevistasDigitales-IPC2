package com.mycompany.revistasdigitales.backend.mvc.controllers.registro;

import com.mycompany.revistasdigitales.backend.database.UsuarioDB;
import com.mycompany.revistasdigitales.backend.usuarios.Rol;
import com.mycompany.revistasdigitales.backend.usuarios.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombre_usuario");
        String contrasena = request.getParameter("contrasena");

        UsuarioDB usuarioDB = new UsuarioDB();
        Usuario usuario = usuarioDB.iniciarSesion(nombreUsuario, contrasena);

        if (usuario != null) {
            // Guardar el usuario en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            // Redirigir a la pagina de inicio correspondiente
            redirigirInicio(request, response, usuario);

        } else {
            // Si el login falla, pasar el mensaje de error al JSP y reenviar la solicitud
            request.setAttribute("errorMensaje", "Credenciales incorrectas, por favor intenta nuevamente.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("portal/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void redirigirInicio(HttpServletRequest request, HttpServletResponse response, Usuario usuario) throws ServletException, IOException {
        Rol rol = usuario.getRol(); // Asumiendo que getRol devuelve un enum RolUsuario
        System.out.println("Rol del usuario: " + rol.name()); // Para depurar, imprime el valor del enum

        switch (rol) {
            case SUSCRIPTOR:
                response.sendRedirect("inicio/suscriptor/suscriptorHome.jsp");
                break;
            case EDITOR:
                //response.sendRedirect("inicio/editor/editorHome.jsp");
                response.sendRedirect("homeEditor");
                break;
            case ANUNCIANTE:
                response.sendRedirect("inicio/anunciante/anuncianteHome.jsp");
                break;
            case ADMINISTRADOR:
                response.sendRedirect("inicio/administrador/adminHome.jsp");
                break;
            default:
                System.out.println("Rol desconocido: " + rol);
                //response.sendRedirect("portal/login.jsp?error=Rol desconocido");
                break;
        }
    }
}
