<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 19/9/24
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="/includes/resources.jsp"/>
    <meta charset="UTF-8">
    <title>Cartera del Usuario</title>
</head>
<body>

<jsp:include page="/includes/headerAnunciante.jsp"/>

<div class="container mt-4">
    <h2>Saldo de la Cartera</h2>

    <!-- Mostrar saldo actual -->
    <div class="alert alert-info">
        Saldo actual en la cartera: <strong>${saldoCartera}</strong>
    </div>

    <!-- Formulario para recargar la cartera -->
    <form action="cartera" method="post">
        <div class="form-group">
            <label for="cantidadRecarga">Cantidad a recargar:</label>
            <input type="number" step="0.01" name="cantidadRecarga" id="cantidadRecarga" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Recargar</button>
    </form>

    <!-- Mostrar mensajes de error si existen -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
</div>

</body>
</html>
