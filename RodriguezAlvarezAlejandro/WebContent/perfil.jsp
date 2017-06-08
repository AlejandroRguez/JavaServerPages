<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - Perfil del usuario</title>
</head>
<body>

	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	
	<h1>Perfil de usuario</h1>
	<hr />
	<br />
	<table id="perfil">
	<tr>
			<td>Nombre de usuario:</td>
			<td id="dato"><jsp:getProperty property="login" name="user" /></td>
		</tr>
		<tr>
			<td>Nombre:</td>
			<td id="dato"><jsp:getProperty property="name" name="user" /></td>
		</tr>
		<tr>
			<td>Apellidos:</td>
			<td id="dato"><jsp:getProperty property="surname" name="user" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td id="dato"><jsp:getProperty property="email" name="user" /></td>
		</tr>
	</table>
	<br />
	<a href="accesoModificarDatos">Modificar datos</a>
	<br />
	<footer>
		<hr />
		<br />
		<input type="button" class="icono_return" onclick="history.back()"
						name="volver_atras" value="&#8617;">
	</footer>
</body>
</html>