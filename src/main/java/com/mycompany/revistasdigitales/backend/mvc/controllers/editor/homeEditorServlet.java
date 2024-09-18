package com.mycompany.revistasdigitales.backend.mvc.controllers.editor;

import com.mycompany.revistasdigitales.backend.database.EditorDB;
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

@WebServlet(name = "homeEditorServlet", urlPatterns = {"/homeEditor"})
public class homeEditorServlet extends HttpServlet {
    // editorRevistas
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

        // Obtener el nombre del autor actual
        String idAutor = usuarioActual.getNombreUsuario(); // Suponiendo que "nombreUsuario" es el ID del autor

        // Consultar las revistas del autor actual
        EditorDB editorDB = new EditorDB();
        List<Revista> revistas = editorDB.obtenerRevistasPorAutor(idAutor);
        
        System.out.println("editor:" +revistas);
        
        // Pasar la lista de revistas al JSP
        request.setAttribute("revistas", revistas);
        
        // Redirigir a la vista de editorHome.jsp
        request.getRequestDispatcher("/inicio/editor/editorHome.jsp").forward(request, response);
    }
}
