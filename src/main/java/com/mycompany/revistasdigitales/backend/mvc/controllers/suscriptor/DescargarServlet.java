package com.mycompany.revistasdigitales.backend.mvc.controllers.suscriptor;

import com.mycompany.revistasdigitales.backend.database.RevistaDB;
import com.mycompany.revistasdigitales.backend.revistas.Revista;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/descargarPDF")
public class DescargarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathContext = getServletContext().getRealPath("/");
        String pathLocal = request.getParameter("archivoPDF");
        String path = pathContext + pathLocal;
        System.out.println("Ruta completa del archivo PDF: " + path);

        //configurar la respuesta
        String fileName = pathLocal.substring(pathLocal.lastIndexOf("/") + 1);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try (BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(path))) {
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        } catch (FileNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "El archivo no fue encontrado en el servidor");
            e.printStackTrace();
        }
    }
}
