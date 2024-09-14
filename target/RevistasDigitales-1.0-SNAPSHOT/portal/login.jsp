<%-- 
    Document   : login
    Created on : 13 sept 2024, 23:56:07
    Author     : melvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Iniciar Sesión</title>
    </head>
    <body>
        <h1>Iniciar Sesión</h1>
        <!-- Formulario de inicio de sesión -->
        <form action="login" method="post">
            <label for="username">Nombre de Usuario:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required><br>
            <input type="submit" value="Iniciar Sesión">
        </form>
    </body>
</html>