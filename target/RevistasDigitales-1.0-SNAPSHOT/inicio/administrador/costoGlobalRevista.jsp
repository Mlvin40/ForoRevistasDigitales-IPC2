<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 19/9/24
  Time: 7:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <jsp:include page="/includes/resources.jsp" />
  <meta charset="UTF-8">
  <title>Actualizar Precio Global de Revistas</title>
</head>
<body>
 <jsp:include page="/includes/headerAdministrador.jsp"/>
<div class="container mt-4">
  <h1>Precio Global de Revistas</h1>
  <!-- Mostrar el precio actual -->
  <div class="mb-4">
    <h2>Precio Actual: Q${precioActual}</h2>
  </div>

  <!-- Mensajes de éxito o error -->
  <c:if test="${not empty mensaje}">
    <div class="alert alert-info">
        ${mensaje}
    </div>
  </c:if>

  <!-- Formulario para actualizar el precio -->
  <form action="${pageContext.servletContext.contextPath}/actualizarCostoGlobal" method="POST">
    <div class="form-group">
      <label for="nuevoPrecio">Nuevo Precio:</label>
      <input type="number" step="0.01" name="nuevoPrecio" id="nuevoPrecio" class="form-control" value="${precioActual}" required />
    </div>
    <button type="submit" class="btn btn-primary">Actualizar Precio</button>
  </form>
</div>
</body>
</html>
