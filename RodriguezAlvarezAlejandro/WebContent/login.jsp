<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - Inicie sesión</title>
</head>
<body>
	<jsp:useBean id="status" class="uo.sdi.common.StatusInfo" scope="request" />
	<h1>Inicio de sesión</h1>
	<hr />
	<br />
	<section>
		<form action="validarse" method="post">
			<table>
				<tr>
					<td align="right">Nombre de usuario</td>
					<td><input type="text" name="login" size="15"></td>
				</tr>
				<tr>
					<td align="right">Contraseña</td>
					<td><input type="password" name="password" size="15"></td>
				</tr>
				<tr id="submit">
					<td/>
					<td><input type="submit" value="Enviar" /></td>
				</tr>
			</table>
		</form>
		<a id="registrarse" href="registrarse.jsp">Registrarse como nuevo
			usuario</a><br /> <a id="listarViajesPublico" href="listarViajesPublico">Lista de
			viajes</a><br />
	</section>
	<footer>
		<hr />
		<br />
		<c:choose>
		<c:when test="${status.value != null}">
			<pre id="status"><jsp:getProperty property="value" name="status" /></pre>
		</c:when>
		</c:choose>
	</footer>
</body>
</html>