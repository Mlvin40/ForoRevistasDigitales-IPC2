<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 18/9/24
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
    <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 link-body-emphasis text-decoration-none">
        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
    </a>

    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
        <li><a href="${pageContext.servletContext.contextPath}/homeSuscriptor" class="nav-link" aria-current="page">Inicio</a></li>
        <li><a href="${pageContext.servletContext.contextPath}/suscribirseRevista" class="nav-link" aria-current="page">Revistas Totales</a></li>
        <li><a href="#" class="nav-link px-2 link-body-emphasis">Mis Revistas</a></li>

    </ul>

    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
        <input type="search" class="form-control" placeholder="Search..." aria-label="Search">
    </form>

    <div class="dropdown text-end">
        <a href="#" class="d-block link-body-emphasis text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
        </a>
        <ul class="dropdown-menu text-small" style="">
            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/perfilUsuario">Profile</a></li>
            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/editarPerfil">Settings</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/cerrarSesion">Sign out</a></li>
        </ul>
    </div>
</div>