<%-- 
    Document   : login
    Created on : 13 sept 2024, 23:56:07
    Author     : melvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/includes/resources.jsp"/>
    <title>Login</title>
</head>
<body>
<h2>Iniciar Sesión</h2>

<form action="${pageContext.servletContext.contextPath}/login" method="POST">
    <label for="nombre_usuario">Nombre de usuario:</label>
    <input type="text" name="nombre_usuario" required><br>
    <label for="contrasena">Contraseña:</label>
    <input type="password" name="contrasena" required><br>

    <input type="submit" value="Iniciar sesión">
</form>

<p>¿No tienes cuenta? <a href="registro.jsp">Regístrate aquí</a></p>

<!-- Mostrar mensaje de error si existe -->
<%
    String errorMensaje = (String) request.getAttribute("errorMensaje");
    if (errorMensaje != null) {
%>
<p style="color: red;"><%= errorMensaje %></p>
<%
    }
%>

</body>
</html>