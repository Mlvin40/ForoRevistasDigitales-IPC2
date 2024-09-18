<%--
  Created by IntelliJ IDEA.
  User: melvin
  Date: 17/9/24
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>La revista ya existe</h1>
    <p>La revista que intentas crear ya existe en el sistema.</p>
    <p>Por favor, intenta con otro nombre.</p>
    <a href="${pageContext.servletContext.contextPath}/inicio/editor/crearRevista.jsp">Volver a intentar</a>

</body>
</html>
