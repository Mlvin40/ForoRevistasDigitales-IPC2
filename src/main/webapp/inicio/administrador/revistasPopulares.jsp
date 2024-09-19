<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 19/9/24
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <jsp:include page="/includes/resources.jsp" />
  <meta charset="UTF-8">
  <title>Reporte de Revistas Populares</title>
</head>
<body>
<jsp:include page="/includes/headerAdministrador.jsp"/>
<div class="container mt-4">
  <h1>Reporte de las 5 Revistas Más Populares</h1>

  <!-- Mensajes de error -->
  <c:if test="${not empty error}">
    <div class="alert alert-danger">
        ${error}
    </div>
  </c:if>

  <!-- Mostrar el reporte -->
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Nombre de la Revista</th>
      <th>Descripción</th>
      <th>Número de Likes</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="revista" items="${revistasPopulares}">
      <tr>
        <td>${revista.nombre}</td>
        <td>${revista.descripcion}</td>
        <td>${revista.likes}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>