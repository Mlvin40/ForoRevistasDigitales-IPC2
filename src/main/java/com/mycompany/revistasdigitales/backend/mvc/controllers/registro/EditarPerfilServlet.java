/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author melvin
 */
@WebServlet("/editarPerfil")
public class EditarPerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        if (usuarioActual == null) {
            response.sendRedirect("portal/login.jsp");
            return;
        }

        // Recuperar la información del usuario desde la base de datos
        UsuarioDB usuarioDB = new UsuarioDB();
        Usuario perfil = usuarioDB.obtenerUsuario(usuarioActual.getNombreUsuario());

        // Pasar la información al JSP
        request.setAttribute("perfil", perfil);
        request.getRequestDispatcher("/portal/configuracionUsuario.jsp").forward(request, response);
    }
}
