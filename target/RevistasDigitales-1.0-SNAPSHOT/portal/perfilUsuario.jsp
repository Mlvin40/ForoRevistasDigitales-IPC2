<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 18/9/24
  Time: 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil del Usuario</title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<body>
    <main>
        <div class="container">
            <h2>Perfil del Usuario</h2>
            <p><strong>Nombre de Usuario:</strong> ${perfil.nombreUsuario}</p>
            <p><strong>Sobre mi:</strong> ${perfil.texto}</p>
            <p><strong>Foto de Perfil:</strong> <img src="${perfil.fotoPerfil}" alt="Foto de Perfil" style="width: 300px; height: 300px;"></p>
            <p><strong>Rol:</strong> ${perfil.rol}</p>
            <p><strong>Fecha de Creación:</strong> ${perfil.fechaCreacion}</p>
        </div>
    </main>
</body>
</html>
