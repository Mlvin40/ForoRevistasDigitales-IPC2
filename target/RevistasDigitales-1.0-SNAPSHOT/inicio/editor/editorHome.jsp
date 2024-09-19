<%@page import="java.util.List"%>
<%@page import="com.mycompany.revistasdigitales.backend.revistas.Revista"%>
<%@ page import="com.mycompany.revistasdigitales.backend.usuarios.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 15/9/24
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio del Editor</title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<body>
<jsp:include page="/includes/headerEditor.jsp"/>

<main>
    <div class="container">
        <!-- Mostrar revistas -->
        <c:choose>
            <c:when test="${not empty revistas}">
                <div class="row">
                    <c:forEach items="${revistas}" var="revista">
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Nombre de la revista: ${revista.nombre != null ? revista.nombre : 'Sin nombre'}</h5>
                                    <p class="card-text">Descripción: ${revista.descripcion != null ? revista.descripcion : 'Sin descripción'}</p>
                                    <p><strong>Categoría:</strong> ${revista.categoria != null ? revista.categoria : 'Sin categoría'}</p>
                                    <p><strong>Fecha de Creación:</strong> ${revista.fechaCreacion != null ? revista.fechaCreacion : 'Desconocida'}</p>
                                    <p><strong>Costo:</strong> $${revista.costo}</p>
                                    <p><strong>Cantidad de Likes:</strong> ${revista.likes}</p> <!-- Mostrar cantidad de likes -->
                                    <a href="${revista.archivoPDF}" class="btn btn-primary" target="_blank">Ver PDF</a>

                                    <a href="editarRevistaServlet?nombreRevista=${revista.nombre}" class="btn btn-warning">Editar</a>

                                    <!-- Mostrar comentarios -->
                                    <div>
                                        <h6>Comentarios:</h6>
                                        <c:forEach items="${revista.comentarios}" var="comentario">
                                            <p><strong>${comentario.nombreUsuario}:</strong> ${comentario.comentario}</p>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>No hay revistas disponibles.</p>
            </c:otherwise>
        </c:choose>
    </div>
</main>
</body>
</html>