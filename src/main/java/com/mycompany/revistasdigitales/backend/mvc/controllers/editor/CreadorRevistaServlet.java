package com.mycompany.revistasdigitales.backend.mvc.controllers.editor;

import com.mycompany.revistasdigitales.backend.revistas.Revista;
import com.mycompany.revistasdigitales.backend.usuarios.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "CreadorRevistaServlet", urlPatterns = {"/crearRevista"})
public class CreadorRevistaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar datos del formulario
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");
        String contenido = request.getParameter("contenido");
        String fechaCreacion = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String autor = (String) request.getSession().getAttribute("usuario") != null
                ? ((Usuario) request.getSession().getAttribute("usuario")).getNombreUsuario()
                : "null";

        // Manejo del archivo PDF
        Part archivoPDFPart = request.getPart("archivoPDF");
        String archivoPDF = null;
        if (archivoPDFPart != null && archivoPDFPart.getSize() > 0) {
            String fileName = Paths.get(archivoPDFPart.getSubmittedFileName()).getFileName().toString(); // Seguridad
            // Guardar el archivo en un directorio temporal
            Path filePath = Paths.get(getServletContext().getRealPath("/") + "uploads" + File.separator + fileName);
            archivoPDFPart.write(filePath.toString());
            archivoPDF = fileName; // Guarda el nombre del archivo
        }
        // Crear objeto Revista
        
        // Redirigir a una página de éxito
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
