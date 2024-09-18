<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 16/9/24
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.mycompany.revistasdigitales.backend.usuarios.Usuario"%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <jsp:include page="/includes/resources.jsp"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Crear Revista</title>
        
        <jsp:include page="/includes/headerEditor.jsp"/>
    </head>
    <body>
        <div class="container mt-5">
            <h1>Crear Nueva Revista</h1>
            <form action="${pageContext.servletContext.contextPath}/crearRevista" method="post" enctype="multipart/form-data">
                <!-- Campo para Nombre de la Revista -->
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre de la Revista</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                </div>
                <!-- Campo para Descripción de la Revista -->
                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción</label>
                    <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
                </div>
                <!-- Campo para Categoría de la Revista -->
                <div class="mb-3">
                    <label for="categoria" class="form-label">Categoría</label>
                    <input type="text" class="form-control" id="categoria" name="categoria" required>
                </div>
                <!-- Campo para la Fecha de Creación -->
                <div class="mb-3">
                    <label for="fechaCreacion" class="form-label">Fecha de Creación</label>
                    <input type="date" class="form-control" id="fechaCreacion" name="fechaCreacion" required>
                </div>
                <!-- Campo no editable para el Autor (usuario logueado) -->
                <div class="mb-3">
                    <label for="autor" class="form-label">Autor</label>
                    <input type="text" class="form-control" id="autor" name="autor"
                           <%
                               String nombreUsuario = "Usuario no disponible";
                               if (session.getAttribute("usuario") != null) {
                                   Usuario usuario = (Usuario) session.getAttribute("usuario");
                                   nombreUsuario = usuario.getNombreUsuario();
                               }
                           %>

                           <input type="text" class="form-control" id="autor" name="autor"
                           value="<%= nombreUsuario%>" readonly>
                </div>
                <!-- Campo para subir el archivo PDF -->
                <div class="mb-3">
                    <label for="archivoPDF" class="form-label">Archivo PDF</label>
                    <input type="file" class="form-control" id="archivoPDF" name="archivoPDF" accept=".pdf" required>
                </div>
                <!-- Botón para enviar el formulario -->
                <button type="submit" class="btn btn-primary">Crear Revista</button>
            </form>
        </div>
    </body>
</html>
