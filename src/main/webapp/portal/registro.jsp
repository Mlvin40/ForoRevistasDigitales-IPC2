<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 13/9/24
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuario</title>
</head>
<body>
<h2>Registro de Usuario</h2>
<form action="${pageContext.servletContext.contextPath}/RegistrarUsuarioServlet" method="post">
    <label for="nombre_usuario">Nombre de Usuario:</label>
    <input type="text" id="nombre_usuario" name="nombre_usuario" required>

    <label for="contraseña">Contraseña:</label>
    <input type="password" id="contraseña" name="contraseña" required>

    <label for="rol">Rol:</label>
    <select id="rol" name="rol">
        <option value="ADMINISTRADOR">Administrador</option>
        <option value="EDITOR">Editor</option>
        <option value="SUSCRIPTOR">Suscriptor</option>
        <option value="ANUNCIANTE">Anunciante</option>
    </select>

    <input type="submit" value="Registrar">
</form>
</body>
</html>


