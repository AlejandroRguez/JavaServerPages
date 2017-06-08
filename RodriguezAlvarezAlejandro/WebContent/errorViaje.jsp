<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="estilo.css" />
<title>ShareMyTrip - ERROR</title>
</head>
<body>
	<jsp:useBean id="status" class="uo.sdi.common.StatusInfo" scope="request" />
	<hr />
	<br />
	<c:choose>
		<c:when test="${status.value != null}">
			<pre id="status"><jsp:getProperty property="value" name="status" /></pre>
		</c:when>
		</c:choose>
	<footer>
		<hr />
		<br />
			<input type="button" class="icono_return" onclick="history.back()"
						name="volver_atras" value="&#8617;">
	</footer>
</body>
</html>