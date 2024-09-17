<%@ page import="com.mycompany.revistasdigitales.backend.usuarios.Usuario" %><%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 15/9/24
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
   
    <jsp:include page="/includes/resources.jsp"/>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio del Editor</title>
    
</head>
<body>
    <jsp:include page="/includes/headerEditor.jsp"/>


<div class="container mt-4">
    <!-- Encabezado con mensaje de bienvenida -->
    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Bienvenido, Editor</h1>
            <p class="lead">Aquí puedes gestionar tus revistas y editar tu perfil.</p>
        </div>
    </div>

    <!-- Mostrar el nombre del editor logueado -->
    <div class="row">
        <div class="col-md-12">
            <h3>Hola, 
                <span class="badge badge-primary">
                    <%= ((Usuario)session.getAttribute("usuario")).getNombreUsuario() %>
                </span>
            </h3>
        </div>
    </div>

    <!-- Mostrar la foto de perfil del editor -->
    <div class="row mt-4">
        <div class="col-md-4">

            <form action="subirFoto" method="POST" enctype="multipart/form-data" class="mt-2">
                <input type="file" name="fotoPerfil" class="form-control-file">
                <button type="submit" class="btn btn-primary btn-block mt-2">Cambiar Foto de Perfil</button>
            </form>
        </div>
    </div>

    <!-- Listado de revistas -->
    <div class="row mt-5">
        <div class="col-md-12">
            <h4>Mis Revistas</h4>
        </div>
        <div class="col-md-12">
            <div class="row">
                <c:forEach var="revista" items="${revistas}">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${revista.nombre}</h5>
                                <p class="card-text">${revista.descripcion}</p>
                                <a href="editarRevista?id=${revista.id}" class="btn btn-warning btn-block">Editar</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <!-- Botón para abrir el formulario de crear nueva revista -->
    <div class="row mt-5">
        <div class="col-md-12 text-right">
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#crearRevistaModal">
                Crear Nueva Revista
            </button>
        </div>
    </div>

    <!-- Modal para crear nueva revista -->
    <div class="modal fade" id="crearRevistaModal" tabindex="-1" role="dialog" aria-labelledby="crearRevistaModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="crearRevistaModalLabel">Crear Nueva Revista</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="crearRevista" method="POST">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nombreRevista">Nombre de la Revista</label>
                            <input type="text" class="form-control" id="nombreRevista" name="nombreRevista" required>
                        </div>
                        <div class="form-group">
                            <label for="descripcionRevista">Descripción</label>
                            <textarea class="form-control" id="descripcionRevista" name="descripcionRevista" rows="3" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="categoriaRevista">Categoría</label>
                            <input type="text" class="form-control" id="categoriaRevista" name="categoriaRevista" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-primary">Crear Revista</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
                            
</body>
</html>

