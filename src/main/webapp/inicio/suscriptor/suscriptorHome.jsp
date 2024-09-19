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
    <title>Inicio del Suscriptor</title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<body>
<jsp:include page="/includes/headerSuscriptor.jsp"/>

<main>
    <div class="container">
        <!-- Mostrar revistas suscritas -->
        <c:choose>
            <c:when test="${not empty revistas}">
                <div class="row">
                    <c:forEach items="${revistas}" var="revista">
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        Nombre de la revista:
                                            ${revista.nombre != null ? revista.nombre : 'Sin nombre'}
                                    </h5>
                                    <p class="card-text">
                                        Descripción:
                                            ${revista.descripcion != null ? revista.descripcion : 'Sin descripción'}
                                    </p>
                                    <p>
                                        <strong>Categoría:</strong>
                                            ${revista.categoria != null ? revista.categoria : 'Sin categoría'}
                                    </p>
                                    <p>
                                        <strong>Fecha de Creación:</strong>
                                            ${revista.fechaCreacion != null ? revista.fechaCreacion : 'Desconocida'}
                                    </p>
                                    <p>
                                        <strong>Cantidad de Likes:</strong>
                                            ${revista.likes}
                                    </p>

                                    <!-- Botón para dar like -->

                                    <form action="${pageContext.servletContext.contextPath}/likeRevistaServlet" method="POST" style="display:inline-block;">
                                        <input type="hidden" name="revistaNombre" value="${revista.nombre}" />
                                        <button type="submit" class="btn btn-success">👍 Like</button>
                                    </form>

                                    <!-- Ver PDF -->
                                    <a href="${revista.archivoPDF}" class="btn btn-primary" target="_blank">Ver PDF</a>

                                    <!-- Descargar PDF -->
                                    <form action="${pageContext.servletContext.contextPath}/descargarPDF" method="get" style="display:inline-block;">
                                        <input type="hidden" name="archivoPDF" value="${revista.archivoPDF}" />
                                        <button type="submit" class="btn btn-secondary">Descargar PDF</button>
                                    </form>

                                    <!-- Ver perfil del autor -->
                                    <p><strong>Autor:</strong>
                                        <a href="${pageContext.servletContext.contextPath}/perfilAutor?autorId=${revista.autor}" class="btn btn-link">
                                                ${revista.autor}
                                        </a>
                                    </p>

                                    <!-- Mostrar comentarios -->
                                    <div>
                                        <h6>Comentarios:</h6>
                                        <c:forEach items="${revista.comentarios}" var="comentario">
                                            <p><strong>${comentario.nombreUsuario}:</strong> ${comentario.comentario}</p>
                                        </c:forEach>
                                    </div>

                                    <!-- Formulario para agregar comentario -->
                                    <form action="${pageContext.servletContext.contextPath}/comentarRevistaServlet" method="POST">
                                        <input type="hidden" name="revistaId" value="${revista.nombre}" />
                                        <textarea name="comentarioTexto" rows="2" class="form-control" placeholder="Escribe tu comentario"></textarea>
                                        <button type="submit" class="btn btn-info mt-2">Comentar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <p>No estás suscrito a ninguna revista.</p>
            </c:otherwise>
        </c:choose>
    </div>
</main>
</body>
</html>