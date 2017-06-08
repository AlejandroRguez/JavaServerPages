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
	<h2>Listado de viajes disponibles</h2>
	<table border="1" align="center">
	<c:choose>
	<c:when test="${!listaViajes.isEmpty()}">
			<tr>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
			</tr>
		<c:forEach var="entry" items="${listaViajes}" varStatus="i">
			<tr id="item_${i.index}">
				<td>${entry.departure.city}</td>
				<td>${entry.destination.city}</td>
				<td>${entry.availablePax}</td>
			</tr>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<pre id="info">No hay viajes disponibles en este momento</pre>
		</c:otherwise>
		</c:choose>
	</table>
	<footer>
		<hr />
		<br />
		<input type="button" class="icono_return" onclick="history.back()"
						name="volver_atras" value="&#8617;">
	</footer>	
</body>
</html>