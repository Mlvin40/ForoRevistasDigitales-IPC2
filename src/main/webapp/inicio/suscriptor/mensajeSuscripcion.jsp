<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 19/9/24
  Time: 1:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado de Suscripción</title>
    <style>
        /* Estilos básicos para el mensaje */
        .mensaje {
            margin: 20px auto;
            padding: 10px;
            border-radius: 5px;
            max-width: 500px;
            text-align: center;
            font-size: 18px;
        }
        .mensaje.exito {
            background-color: #dff0d8;
            color: #3c763d;
            border: 1px solid #3c763d;
        }
        .mensaje.error {
            background-color: #f2dede;
            color: #a94442;
            border: 1px solid #a94442;
        }
        .boton {
            display: inline-block;
            padding: 10px 15px;
            margin-top: 20px;
            background-color: #337ab7;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .boton:hover {
            background-color: #286090;
        }
    </style>
</head>
<body>

<div class="mensaje <%= (request.getAttribute("mensaje").toString().contains("éxito")) ? "exito" : "error" %>">
    <p><%= request.getAttribute("mensaje") %></p>
</div>

<div style="text-align: center;">
    <a href="${pageContext.servletContext.contextPath}/homeSuscriptor">Volver al Inicio</a>
</div>

</body>
</html>




