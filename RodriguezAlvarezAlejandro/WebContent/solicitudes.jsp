<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - Listado de solicitudes</title>
</head>
<body>

	<c:set var="accepted" scope="page" value="ACCEPTED" />
	<c:set var="excluded" scope="page" value="EXCLUDED" />
	
	<jsp:useBean id="status" class="uo.sdi.common.StatusInfo" scope="request" />
	
	<h2>Solicitudes confirmadas </h2>
	<table border="1" align="center">
	<c:choose>
	<c:when test="${!seats.isEmpty()}">
			<tr>
				<th> Usuario </th>
				<th>Estado</th>
				<th>Accion</th>
			</tr>
		<c:forEach var="entry" items="${seats}" varStatus="i">
		<c:choose>
			<c:when test="${entry.value.status==accepted}">
			<tr id="item_${i.index}">
				<td>${entry.key.name}</td>
				<td>${entry.value.status}</td>
				<td><a href="rechazarSolicitud?userId=${entry.value.userId}&tripId=${entry.value.tripId}">Rechazar</a></td>
			</tr>
			</c:when>
			<c:when test="${entry.value.status==excluded}">
			<tr id="item_${i.index}">
				<td>${entry.key.name}</td>
				<td>${entry.value.status}</td>
				<td><a href="aceptarSolicitud?userId=${entry.value.userId}&tripId=${entry.value.tripId}">Aceptar</a></td>
			</tr>
			</c:when>
		</c:choose>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<pre id="info">Aún no has confirmado ninguna solicitud</pre>
		</c:otherwise>
		</c:choose>	
	</table>
	<h2> Solicitudes por confirmar </h2>
	<table border="1" align="center">
	<c:choose>
	<c:when test="${!apps.isEmpty()}">
			<tr>
				<th> Usuario </th>
				<th>Aceptar</th>
				<th>Rechazar</th>
			</tr>
		<c:forEach var="entry" items="${apps}" varStatus="i">
			<tr id="item_${i.index}">
				<td>${entry.key.name}</td>
				<td><a href="aceptarSolicitud?userId=${entry.key.id}&tripId=${entry.value.tripId}">Aceptar</a></td>
				<td><a href="rechazarSolicitud?userId=${entry.value.userId}&tripId=${entry.value.tripId}">Rechazar</a></td>
			</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<pre id="info">No tiene ninguna solicitud por confirmar</pre>
		</c:otherwise>
		</c:choose>	
	</table>
	<footer>
		<hr />
		<br />
		<input type="button" class="icono_return" onclick="history.back()"
						name="volver_atras" value="&#8617;">
		<c:choose>
		<c:when test="${status.value != null}">
			<pre id="status"><jsp:getProperty property="value" name="status" /></pre>
		</c:when>
		</c:choose>
	</footer>
</body>
</html>