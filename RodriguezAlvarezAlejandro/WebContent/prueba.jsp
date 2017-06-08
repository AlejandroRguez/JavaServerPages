<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - PRUEBA</title>
</head>
<body>
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	<hr />
	<br />
	<h2>Hola, tu contraseña es <jsp:getProperty property="password" name="user" /></h2>
	<footer>
		<hr />
		<a id="principal" href="principal">Ir a principal</a>
		<br />
	</footer>
</body>
</html>