<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 19/9/24
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Suscribirse a una Revista</title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<body>
<jsp:include page="/includes/headerSuscriptor.jsp"/>

<main>
    <div class="container">
        <h1>Suscribirse a una Revista</h1>

        <c:choose>
            <c:when test="${not empty revistas}">
                <c:forEach items="${revistas}" var="revista">
                    <div class="card mb-3">
                        <div class="card-body">
                            <h5 class="card-title">${revista.nombre}</h5>
                            <p class="card-text"><strong>Descripción:</strong> ${revista.descripcion}</p>
                            <p class="card-text"><strong>Categoría:</strong> ${revista.categoria}</p>
                            <p class="card-text"><strong>Fecha de Creación:</strong> ${revista.fechaCreacion}</p>

                            <!-- Cada botón de suscribirse tendrá su propio formulario -->
                            <form action="suscribirRevistaServlet" method="post">
                                <input type="hidden" name="nombreRevista" value="${revista.nombre}"/>

                                <!-- Campo de selección de fecha -->
                                <div class="form-group">
                                    <label for="fechaSuscripcion${revista.nombre}">Seleccionar fecha de suscripción:</label>
                                    <input type="date" id="fechaSuscripcion${revista.nombre}" name="fechaSuscripcion" required class="form-control"/>
                                </div>

                                <!-- Botón de suscripción -->
                                <button type="submit" class="btn btn-primary mt-2">Suscribirse</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>No hay revistas disponibles para suscribirse.</p>
            </c:otherwise>
        </c:choose>
    </div>
</main>
</body>
</html>


