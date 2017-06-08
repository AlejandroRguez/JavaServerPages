<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - Listado de viajes</title>
</head>
<body>
	<jsp:useBean id="status" class="uo.sdi.common.StatusInfo" scope="request" />
	<h2> Viajes promovidos pendientes </h2>
	<table border="1" align="center">
	<c:choose>
	<c:when test="${!listaViajes1.isEmpty()}">
			<tr>
				<th> Estado </th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
				<th>Coste estimado</th>
				<th>Estado</th>
				<th>Solicitudes de plaza</th>
				<th>Cancelar</th>
				<th>Editar</th>
			</tr>
		<c:forEach var="entry" items="${listaViajes1}" varStatus="i">
			<tr id="item_${i.index}">
				<td>${entry.value}</td>
				<td>${entry.key.departure.city}</td>
				<td>${entry.key.destination.city}</td>
				<td>${entry.key.availablePax}</td>
				<td>${entry.key.estimatedCost}</td>
				<td>${entry.key.status}</td>
				<td><a href="verSolicitudes?id=${entry.key.id}">Ver Solicitudes</a></td>
				<td><a href="cancelarViaje?id=${entry.key.id}">Cancelar</a></td>
				<td><a href="accesoModificarViaje?id=${entry.key.id}">Editar</a></td>
			</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<pre id="info">No tiene viajes promovidos pendientes</pre>
		</c:otherwise>
		</c:choose>
	</table>
	<h2> Viajes promovidos hechos </h2>
	<table border="1" align="center">
	<c:choose>
	<c:when test="${!listaViajes2.isEmpty()}">
			<tr>
				<th> Estado </th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
				<th>Coste estimado</th>
			</tr>
		<c:forEach var="entry" items="${listaViajes2}" varStatus="i">
			<tr id="item_${i.index}">
				<td>${entry.value}</td>
				<td>${entry.key.departure.city}</td>
				<td>${entry.key.destination.city}</td>
				<td>${entry.key.availablePax}</td>
				<td>${entry.key.estimatedCost}</td>
			</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<pre id="info">Aún no ha realizado ningún viaje promovido por usted</pre>
		</c:otherwise>
		</c:choose>
	</table>
	<h2> Viajes en los que ha participado </h2>
	<table border="1" align="center">
	<c:choose>
	<c:when test="${!listaViajes3.isEmpty()}">
			<tr>
				<th> Estado </th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
				<th>Coste estimado</th>
			</tr>
		<c:forEach var="entry" items="${listaViajes3}" varStatus="i">
			<tr id="item_${i.index}">
				<td>${entry.value}</td>
				<td>${entry.key.departure.city}</td>
				<td>${entry.key.destination.city}</td>
				<td>${entry.key.availablePax}</td>
				<td>${entry.key.estimatedCost}</td>
			</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<pre id="info">Aún no ha participado en ningún viaje</pre>
		</c:otherwise>
		</c:choose>
	</table>		
	<h2> Viajes en los que ha solicitado plaza </h2>
	<table border="1" align="center">
	<c:choose>
	<c:when test="${!listaViajes4.isEmpty()}">
			<tr>
				<th> Estado </th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
				<th>Coste estimado</th>
				<th>Cancelar solicitud</th>
			</tr>
		<c:forEach var="entry" items="${listaViajes4}" varStatus="i">
			<tr id="item_${i.index}">
				<td>${entry.value}</td>
				<td>${entry.key.departure.city}</td>
				<td>${entry.key.destination.city}</td>
				<td>${entry.key.availablePax}</td>
				<td>${entry.key.estimatedCost}</td>
				<td><a href="cancelarSolicitud?id=${entry.key.id}">Cancelar solicitud</a></td>
			</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<pre id="info">No ha solicitado plaza en ningún viaje</pre>
		</c:otherwise>
		</c:choose>
	</table>
	<footer>
		<hr />
		<br />
		<c:choose>
		<c:when test="${status.value != null}">
			<pre id="status"><jsp:getProperty property="value" name="status" /></pre>
		</c:when>
		</c:choose>
		<hr />
		<br />
		<input type="button" class="icono_return" onclick="history.back()"
						name="volver_atras" value="&#8617;">
	</footer>
</body>
</html>