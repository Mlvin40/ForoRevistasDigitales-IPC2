<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 19/9/24
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <jsp:include page="/includes/resources.jsp" />
  <meta charset="UTF-8">
  <title>Revistas Más Comentadas</title>
</head>
<body>

<jsp:include page="/includes/headerAdministrador.jsp"/>
<div class="container mt-4">
  <h1>Revistas Más Comentadas</h1>

  <!-- Mensajes de éxito o error -->
  <c:if test="${not empty mensaje}">
    <div class="alert alert-info">
        ${mensaje}
    </div>
  </c:if>

  <!-- Mostrar la lista de revistas más comentadas -->
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Nombre</th>
      <th>Comentarios Totales</th>
      <th>Comentarios</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="revista" items="${revistas}">
      <tr>
        <td>${revista.nombre}</td>
        <td>${revista.numeroComentarios}</td>
        <td>
          <ul>
            <!-- Iterar sobre los comentarios de la revista -->
            <c:forEach var="comentario" items="${revista.comentarios}">
              <li><strong>${comentario.nombreUsuario}:</strong> ${comentario.comentario}</li>
            </c:forEach>
          </ul>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
