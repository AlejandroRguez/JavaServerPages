<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - Registrar nuevo viaje</title>
</head>
<body>
<jsp:useBean id="status" class="uo.sdi.common.StatusInfo" scope="request" />
	<h1>Registro de nuevo viaje:</h1>
	<hr />
	<br />
	<form action="crearViaje" method="post">
		<h2>Salida:</h2>
		<table>
			<tr>
				<td>Dirección*</td>
				<td><input type="text" name="direccionSalida"
					id="direccionSalida" size="30"></td>
			</tr>
			<tr>
				<td>Ciudad*</td>
				<td><input type="text" name="ciudadSalida" id="ciudadSalida"
					size="30"></td>
			</tr>
			<tr>
				<td>Provincia*</td>
				<td><input type="text" name="provinciaSalida"
					id="provinciaSalida" size="30"></td>
			</tr>
			<tr>
				<td>País*</td>
				<td><input type="text" name="paisSalida" id="paisSalida"
					size="30"></td>
			</tr>
			<tr>
				<td>Código Postal*</td>
				<td><input type="text" name="cpSalida" id="cpSalida" size="30"></td>
			</tr>
			<tr>
				<td>Latitud GPS</td>
				<td><input type="text" name="latSalida" id="latSalida"
					size="30"></td>
			</tr>
			<tr>
				<td>Longitud GPS</td>
				<td><input type="text" name="lonSalida" id="lonSalida"
					size="30"></td>
			</tr>
			<tr>
				<td>Salida (DD/MM/YYYY HH:MM)*</td>
				<td><input type="text" name="fechaSalida" id="fechaSalida"
					size="30"></td>
			</tr>
		</table>
		<h2>Destino:</h2>
		<table>
			<tr>
				<td>Dirección*</td>
				<td><input type="text" name="direccionLlegada"
					id="direccionLlegada" size="30"></td>
			</tr>
			<tr>
				<td>Ciudad*</td>
				<td><input type="text" name="ciudadLlegada" id="ciudadLlegada"
					size="30"></td>
			</tr>
			<tr>
				<td>Provincia*</td>
				<td><input type="text" name="provinciaLlegada"
					id="provinciaLlegada" size="30"></td>
			</tr>
			<tr>
				<td>País*</td>
				<td><input type="text" name="paisLlegada" id="paisLlegada"
					size="30"></td>
			</tr>
			<tr>
				<td>Código Postal*</td>
				<td><input type="text" name="cpLlegada" id="cpLlegada"
					size="30"></td>
			</tr>
			<tr>
				<td>Latitud GPS</td>
				<td><input type="text" name="latLlegada" id="latLlegada"
					size="30"></td>
			</tr>
			<tr>
				<td>Longitud GPS</td>
				<td><input type="text" name="lonLlegada" id="lonLlegada"
					size="30"></td>
			</tr>
			<tr>
				<td>Llegada (DD/MM/YYYY HH:MM)*</td>
				<td><input type="text" name="fechaLlegada" id="fechaLlegada"
					size="30"></td>
			</tr>
		</table>
		<h2>Otros datos:</h2>
		<table>
			<tr>
				<td>Cierre de inscripciones (DD/MM/YYYY HH:MM)*</td>
				<td><input type="text" name="fechaCierre" id="fechaCierre"
					size="30"></td>
			</tr>
			<tr>
				<td>Coste*</td>
				<td><input type="text" name="coste" id="coste" size="30"></td>
			</tr>
			<tr>
				<td>Comentarios</td>
				<td><input type="text" name="comentarios" id="comentarios"
					size="30"></td>
			</tr>
			<tr>
				<td>Plazas totales*</td>
				<td><input type="text" name="plazasTotales" id="plazasTotales"
					size="30"></td>
			</tr>
			<tr>
				<td>Plazas disponibles*</td>
				<td><input type="text" name="plazasDisponibles"
					id="plazasDisponibles" size="30"></td>
			</tr>
		</table>
		<br />
		<input type="button" class="icono_return" onclick="history.back()"
						name="volver_atras" value="&#8617;"/><input type="submit" value="Hecho" />
	</form>
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