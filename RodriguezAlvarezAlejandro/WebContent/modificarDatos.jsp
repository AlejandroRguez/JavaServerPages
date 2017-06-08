<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - Cambiar perfil</title>
<body>
	<section>
	<form action="modificarDatos" method="post">
		
		<jsp:useBean id="status" class="uo.sdi.common.StatusInfo" scope="request" />
		<jsp:useBean id = "user" class="uo.sdi.model.User" scope="session" />
		
		<h1>Modificación de su perfil</h1>
		<hr>
		<br>
		<table>
			<tr>
				<td>Nombre*</td>
				<td><input type="text" name="nombre" size="15" value = "<jsp:getProperty property="name" name="user" />"></td>
			</tr>
			<tr>
				<td>Apellidos*</td>
				<td><input type="text" name="apellidos" size="15"value = "<jsp:getProperty property="surname" name="user" />"></td>
			</tr>
			<tr>
				<td>E-mail*</td>
				<td><input type="text" name="email" size="15"value = "<jsp:getProperty property="email" name="user" />"></td>
			</tr>
			<tr>
				<td>Contraseña*</td>
				<td><input type="password" name="password" size="15"></td>
			</tr>
			<tr>
				<td>Nueva contraseña*</td>
				<td><input type="password" name="passwordNueva" size="15"></td>
			</tr>
			<tr>
				<td>Repita su contraseña*</td>
				<td><input type="password" name="passwordNueva2" size="15"></td>
			</tr>
			<tr>
			<td/>
				<td><input type="submit" value="Hecho" /></td>
			</tr>
		</table>
	</form>
	</section>
	<footer>
		<hr />
		<br />
		<p> Los campos marcados con * son obligatorios</p>
		<c:choose>
		<c:when test="${status.value != null}">
			<pre id="status"><jsp:getProperty property="value" name="status" /></pre>
		</c:when>
		</c:choose>
	</footer>
	<input type="button" class="icono_return" onclick="history.back()"
						name="volver_atras" value="&#8617;"/>
</body>
</html>