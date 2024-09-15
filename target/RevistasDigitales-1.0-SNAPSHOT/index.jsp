<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 13/9/24
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Portal</title>
    <style>
        /* Agregar un poco de estilo para los botones y el layout */
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
       
        .button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            font-size: 16px;
            cursor: pointer;
            text-decoration: none;
            color: #fff;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h1>Bienvenido al Sistema</h1>
<p>Selecciona una opción para continuar:</p>

<!-- Botón para registrar un nuevo usuario -->
<form action="portal/registro.jsp" method="get">
    <button class="button" type="submit">Registrar Nuevo Usuario</button>
</form>

<!-- Botón para iniciar sesión -->
<form action="portal/login.jsp" method="get">
    <button class="button" type="submit">Iniciar Sesión</button>
</form>
</body>
</html>