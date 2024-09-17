package com.mycompany.revistasdigitales.backend.mvc.controllers.registro;
import com.mycompany.revistasdigitales.backend.database.CarteraDB;
import com.mycompany.revistasdigitales.backend.database.UsuarioDB;
import com.mycompany.revistasdigitales.backend.usuarios.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrarUsuarioServlet", urlPatterns = {"/registro"})
public class RegistrarUsuarioServlet extends HttpServlet {

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombre_usuario");
        String contrasena = request.getParameter("contrasena");
        String rol = request.getParameter("rol");
        
        Usuario usuario = null;
        boolean esAnunciante = false;
        
        switch (rol) {
            case "SUSCRIPTOR":
                usuario = new Suscriptor(nombreUsuario, contrasena);
                break;
            case "EDITOR":
                usuario = new Editor(nombreUsuario, contrasena);
                break;
            case "ANUNCIANTE":
                usuario = new Anunciante(nombreUsuario, contrasena);
                esAnunciante = true;

                break;
            case "ADMINISTRADOR":
                usuario = new Administrador(nombreUsuario, contrasena);
                break;
            default:
                //Error
                break;
        }

        UsuarioDB usuarioDB = new UsuarioDB();

        if(usuarioDB.registrarUsuario(usuario)){
            if(esAnunciante){
                CarteraDB carteraDB = new CarteraDB();
                carteraDB.crearCartera(usuario);
            }
            response.sendRedirect("index.jsp");
        }
        else{
            response.sendRedirect("errores/errorRegistro.jsp");
        }
    }
}



