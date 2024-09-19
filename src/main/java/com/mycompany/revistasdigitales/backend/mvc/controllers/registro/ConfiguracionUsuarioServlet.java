package com.mycompany.revistasdigitales.backend.mvc.controllers.registro;

import com.mycompany.revistasdigitales.backend.database.UsuarioDB;
import com.mycompany.revistasdigitales.backend.usuarios.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.nio.file.Paths;

@WebServlet(name = "ConfiguracionUsuarioServlet", urlPatterns = {"/configuracionUsuario"})
@MultipartConfig
public class ConfiguracionUsuarioServlet extends HttpServlet {

    private static final String FOTOS_PERFIL_FOLDER = "imagenes_folder";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

        if (usuarioActual == null) {
            response.sendRedirect("portal/login.jsp");
            return;
        }

        // Recuperar datos del formulario
        String texto = request.getParameter("texto");

        // Manejo del archivo de foto de perfil
        Part fotoPerfilPart = request.getPart("fotoPerfil");
        String fotoPerfilFilename = null;
        String urlPath = null;

        if (fotoPerfilPart != null && fotoPerfilPart.getSize() > 0) {
            // Obtener el nombre seguro del archivo
            String fileName = Paths.get(fotoPerfilPart.getSubmittedFileName()).getFileName().toString();

            // Obtener el path absoluto de la carpeta "imagenes_folder"
            String fotosPerfilPath = getServletContext().getRealPath("/") + FOTOS_PERFIL_FOLDER;
            File fotosPerfilDir = new File(fotosPerfilPath);

            // Verificar si la carpeta existe; si no, crearla
            if (!fotosPerfilDir.exists()) {
                fotosPerfilDir.mkdirs(); // Crear la carpeta si no existe
            }

            // Construir el path completo donde se guardará el archivo
            File file = new File(fotosPerfilDir, fileName);
            String fullFilePath = file.getAbsolutePath();

            // Guardar el archivo en la carpeta "imagenes_folder"
            fotoPerfilPart.write(fullFilePath);

            // Generar la URL relativa del archivo para guardar en la base de datos
            fotoPerfilFilename = FOTOS_PERFIL_FOLDER + "/" + fileName;

            // Verificar que el archivo fue guardado correctamente
            if (file.exists()) {
                System.out.println("Archivo guardado en: " + fullFilePath);
            } else {
                System.out.println("Error: el archivo no se guardó correctamente.");
            }
        }

        // Actualizar el perfil del usuario en la base de datos
        UsuarioDB usuarioDB = new UsuarioDB();
        usuarioDB.actualizarUsuario(usuarioActual.getNombreUsuario(), texto, fotoPerfilFilename);

        // Actualizar la información en la sesión
        Usuario perfilActualizado = usuarioDB.obtenerUsuario(usuarioActual.getNombreUsuario());
        session.setAttribute("usuario", perfilActualizado);

        // Redirigir de nuevo a la vista de perfil
        response.sendRedirect("perfilUsuario");
    }
    
}
