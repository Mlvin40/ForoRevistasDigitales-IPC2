<%@ page import="com.mycompany.revistasdigitales.backend.revistas.Revista" %><%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 18/9/24
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="/includes/resources.jsp"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Revista</title>
    
</head>
<body>
    <jsp:include page="/includes/headerEditor.jsp"/>
    
    <h1>Editar Revista</h1>

    <form action="actualizarRevistaServlet" method="POST">
        <!-- Campo Nombre: Solo mostrar, no editable -->
        <div>
            <label for="nombre">Nombre de la Revista:</label>
            <input type="text" id="nombre" name="nombre" value="${revista.nombre}" readonly>
        </div>

        <!-- Campo Descripción: Editable -->
        <div>
            <label for="descripcion">Descripción:</label>
            <textarea id="descripcion" name="descripcion">${revista.descripcion}</textarea>
        </div>

        <!-- Campo Categoría: Editable -->
        <div>
            <label for="categoria">Categoría:</label>
            <input type="text" id="categoria" name="categoria" value="${revista.categoria}">
        </div>

        <!-- Campo Estado Comentar: Checkbox editable -->
        <div>
            <label for="estado_comentar">Estado Comentar:</label>
            <input type="checkbox" id="estado_comentar" name="estado_comentar" ${revista.estadoComentar ? "checked" : ""}>
        </div>

        <!-- Campo Estado Me Gusta: Checkbox editable -->
        <div>
            <label for="estado_me_gusta">Estado Me Gusta:</label>
            <input type="checkbox" id="estado_me_gusta" name="estado_me_gusta" ${revista.estadoMeGusta ? "checked" : ""}>
        </div>

        <!-- Campo Estado Suscribirse: Checkbox editable -->
        <div>
            <label for="estado_suscribirse">Estado Suscribirse:</label>
            <input type="checkbox" id="estado_suscribirse" name="estado_suscribirse" ${revista.estadoSuscribirse ? "checked" : ""}>
        </div>

        <!-- Botón de Guardar -->
        <div>
            <button type="submit">Guardar</button>
        </div>
    </form>
</body>
</html>