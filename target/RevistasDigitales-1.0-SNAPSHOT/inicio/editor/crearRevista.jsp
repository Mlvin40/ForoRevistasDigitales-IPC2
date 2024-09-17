<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 16/9/24
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.mycompany.revistasdigitales.backend.usuarios.Usuario"%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<<html lang="es">
<head>
    <jsp:include page="/includes/resources.jsp"/>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Crear Revista</title>
  <!-- Incluye el enlace a Bootstrap CSS para estilos -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+Knujsl7/3pXOd1L5QzYINxUkQdH1SvbOJgh8xepS9i7J8l" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
  <h1>Crear Nueva Revista</h1>
  <form action="${pageContext.servletContext.contextPath}/crearRevista" method="post" enctype="multipart/form-data">
    <div class="mb-3">
      <label for="nombre" class="form-label">Nombre de la Revista</label>
      <input type="text" class="form-control" id="nombre" name="nombre" required>
    </div>
    <div class="mb-3">
      <label for="descripcion" class="form-label">Descripción</label>
      <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
    </div>
    <div class="mb-3">
      <label for="categoria" class="form-label">Categoría</label>
      <input type="text" class="form-control" id="categoria" name="categoria" required>
    </div>
    <div class="mb-3">
      <label for="contenido" class="form-label">Contenido</label>
      <textarea class="form-control" id="contenido" name="contenido" rows="5" required></textarea>
    </div>
    <!-- Fecha de Creación no está en el formulario ya que se maneja automáticamente en el servidor -->
    <div class="mb-3">
      <label for="archivoPDF" class="form-label">Archivo PDF</label>
      <input type="file" class="form-control" id="archivoPDF" name="archivoPDF" accept=".pdf" required>
    </div>
    <button type="submit" class="btn btn-primary">Crear Revista</button>
  </form>
</div>
<!-- Incluye Bootstrap JS para funcionalidades adicionales -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoYwQrw1Jhk4V9l3zCbuIWv9BlYlIA1bDfgrKYy2TcJaauP" crossorigin="anonymous"></script>
</body>
</html>
