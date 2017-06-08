<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - Perfil del promotor</title>
</head>
<body>

		<jsp:useBean id="info" class="uo.sdi.common.UserInfo" scope="request" />
	
	<h1>Perfil de usuario</h1>
	<hr />
	<br />
	<table id="perfil">
	<tr>
			<td>Nombre de usuario:</td>
			<td id="dato"><jsp:getProperty property="login" name="info" /></td>
		</tr>
		<tr>
			<td>Nombre:</td>
			<td id="dato"><jsp:getProperty property="name" name="info" /></td>
		</tr>
		<tr>
			<td>Apellidos:</td>
			<td id="dato"><jsp:getProperty property="surname" name="info" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td id="dato"><jsp:getProperty property="email" name="info" /></td>
		</tr>
		<tr>
			<td>Viajes promovidos:</td>
			<td id="dato"><jsp:getProperty property="numViajes" name="info" /></td>
		</tr>
	</table>
	<br />
	<footer>
		<hr />
		<br />
		<input type="button" class="icono_return" onclick="history.back()"
						name="volver_atras" value="&#8617;">
	</footer>
</body>
</html>