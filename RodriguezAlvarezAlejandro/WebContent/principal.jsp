<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - Página principal del usuario</title>
</head>
<body>

	<jsp:useBean id="status" class="uo.sdi.common.StatusInfo" scope="request" />
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	
	<h1>
		¡Hola,
		<jsp:getProperty property="name" name="user" />!
	</h1>
	<hr />
	<br />
	<section id="contenedor">
		<section id="metro">
			<h2>Gestión de usuario</h2>
			<a id="verPerfil" href="verPerfil">Mi perfil</a><br>
			 <a id="cerrarSesion" href="cerrarSesion">Cerrar sesión</a><br>
		</section>
		<section id="metro">
			<h2>Gestión de viajes</h2>
			<a id="listarViajesRegistrado" href="listarViajesRegistrado">Consultar	viajes disponibles</a><br> 
			<a id="listarViajesUsuario" href="listarViajesUsuario">Mis viajes</a><br>
			<a id="crearViaje" href="accesoCrearViaje">Crear viaje</a>
		</section>
	</section>
	<c:choose>
		<c:when test="${status.value != null}">
			<pre id="status"><jsp:getProperty property="value" name="status" /></pre>
		</c:when>
		</c:choose>
	<footer>
		<hr />
		<br />
		Es Vd el usuario número: ${contador}
	</footer>
</body>
</html>