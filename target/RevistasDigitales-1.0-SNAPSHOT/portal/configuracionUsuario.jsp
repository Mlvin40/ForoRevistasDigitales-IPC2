<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 18/9/24
  Time: 3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Editar Perfil</title>
  <jsp:include page="/includes/resources.jsp"/>
</head>
<body>

<main>
  <div class="container">
    <h2>Editar Perfil</h2>
    <form action="${pageContext.servletContext.contextPath}/configuracionUsuario" method="post" enctype="multipart/form-data">
      <div class="form-group">
        <label for="nombreUsuario">Nombre de Usuario:</label>
        <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" value="${perfil.nombreUsuario}" readonly>
      </div>

      <div class="form-group">
        <label for="fechaCreacion">Fecha de Creación:</label>
        <input type="text" class="form-control" id="fechaCreacion" name="fechaCreacion" value="${perfil.fechaCreacion}" readonly>
      </div>

      <div class="form-group">
        <label for="rol">Rol:</label>
        <input type="text" class="form-control" id="rol" name="rol" value="${perfil.rol}" readonly>
      </div>

      <div class="form-group">
        <label for="texto">Texto del Perfil:</label>
        <textarea class="form-control" id="texto" name="texto">${perfil.texto}</textarea>
      </div>

      <div class="form-group">
        <label for="fotoPerfil">Foto de Perfil:</label>
        <input type="file" class="form-control-file" id="fotoPerfil" name="fotoPerfil">
        <img src="${perfil.fotoPerfil}" alt="Foto de Perfil" style="max-width: 150px; margin-top: 10px;">
      </div>

      <input type="hidden" name="action" value="updateProfile">
      <button type="submit" class="btn btn-primary">Guardar Cambios</button>
    </form>
  </div>
</main>
</body>
</html>
