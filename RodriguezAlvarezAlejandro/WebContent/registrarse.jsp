<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - Registro de Usuario</title>
<body>
	<section>
	<form action="registrarse" method="post">
		<jsp:useBean id="status" class="uo.sdi.common.StatusInfo" scope="request" />
		<h1>Registro de nuevo usuario</h1>
		<hr>
		<br>
		<table>
			<tr>
				<td>Su nombre de usuario*</td>
				<td><input type="text" name="login" size="15"></td>
			</tr>
			<tr>
				<td>Nombre*</td>
				<td><input type="text" name="nombre" size="15"></td>
			</tr>
			<tr>
				<td>Apellidos*</td>
				<td><input type="text" name="apellidos" size="15"></td>
			</tr>
			<tr>
				<td>E-mail*</td>
				<td><input type="text" name="email" size="15"></td>
			</tr>
			<tr>
				<td>Su contraseña*</td>
				<td><input type="password" name="password" size="15"></td>
			</tr>
			<tr>
				<td>Repita su contraseña*</td>
				<td><input type="password" name="password2" size="15"></td>
			</tr>
			<tr>
			<td/>
				<td><input type="button" class="icono_return" onclick="history.back()"
						name="volver_atras" value="&#8617;"><input type="submit" value="¡Adelante!" /></td>
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
</body>
</html>