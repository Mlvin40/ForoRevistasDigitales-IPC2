package com.mycompany.revistasdigitales.backend.mvc.controllers.editor;

import com.mycompany.revistasdigitales.backend.database.RevistaDB;
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
@MultipartConfig
public class CreadorRevistaServlet extends HttpServlet {

    // Carpeta donde se guardarán los archivos PDF de las revistas
    private static final String REVISTAS_FOLDER = "revistas_folder";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperar datos del formulario
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");
        String fechaCreacion = request.getParameter("fechaCreacion");
        String autor = request.getParameter("autor");

        // Manejo del archivo PDF
        Part archivoPDFPart = request.getPart("archivoPDF");
        String archivoPDF = null;
        String urlPath = null;

        //Verificar si la revista ya existe
        RevistaDB revistaDB = new RevistaDB();
        if(revistaDB.existeRevista(nombre)){
            // Redirigir a una página de error
            //response.sendRedirect(request.getContextPath() + "/inicio/editor/errorRevistaExistente.jsp");
            return;
        }

        if (archivoPDFPart != null && archivoPDFPart.getSize() > 0) {
            // Obtener el nombre seguro del archivo
            String fileName = Paths.get(archivoPDFPart.getSubmittedFileName()).getFileName().toString();

            // Obtener el path absoluto de la carpeta "revistas"
            String revistasPath = getServletContext().getRealPath("/") + REVISTAS_FOLDER;
            File revistasDir = new File(revistasPath);

            // Verificar si la carpeta existe; si no, crearla
            if (!revistasDir.exists()) {
                revistasDir.mkdirs(); // Crear la carpeta si no existe
            }

            // Construir el path completo donde se guardará el archivo
            File file = new File(revistasDir, fileName);
            String fullFilePath = file.getAbsolutePath();

            // Guardar el archivo en la carpeta "revistas"
            archivoPDFPart.write(fullFilePath);

            // Generar la URL relativa del archivo para guardar en la base de datos
            urlPath = REVISTAS_FOLDER + "/" + fileName;

            // Verificar que el archivo fue guardado correctamente
            if (file.exists()) {
                System.out.println("Archivo guardado en: " + fullFilePath);
            } else {
                System.out.println("Error: el archivo no se guardó correctamente.");
            }
        }

        Revista revista = new Revista(nombre, descripcion, categoria, fechaCreacion, autor, urlPath);
        revistaDB.crearRevista(revista);
        response.sendRedirect("homeEditor");
        System.out.println("Revista creada: " + revista.getNombre());
    }
}
