<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 19/9/24
  Time: 8:25
  To change this template use File | Settings | File Templates.
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="/includes/resources.jsp" />
    <meta charset="UTF-8">
    <title>Actualizar Precio por Revista</title>
</head>
<body>

<jsp:include page="/includes/headerAdministrador.jsp"/>
<div class="container mt-4">
    <h1>Actualizar Precio por Revista</h1>

    <!-- Mensajes de éxito o error -->
    <c:if test="${not empty mensaje}">
        <div class="alert alert-info">
                ${mensaje}
        </div>
    </c:if>

    <!-- Mostrar la lista de revistas -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Fecha de Creación</th>
            <th>Precio Actual</th>
            <th>Actualizar Precio</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="revista" items="${revistas}">
            <tr>
                <td>${revista.nombre}</td>
                <td>${revista.descripcion}</td>
                <td>${revista.fechaCreacion}</td>
                <td>Q${revista.costo}</td>
                <td>
                    <!-- Formulario para actualizar el precio -->
                    <form action="${pageContext.servletContext.contextPath}/actualizarPrecioRevista" method="POST">
                        <input type="hidden" name="nombreRevista" value="${revista.nombre}" />
                        <input type="number" step="0.01" name="nuevoPrecio" class="form-control" value="${revista.costo}" required />
                        <button type="submit" class="btn btn-primary mt-2">Actualizar Precio</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>