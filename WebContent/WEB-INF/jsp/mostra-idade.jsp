<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
</head>
<body>
	<jsp:useBean id="contato" class="br.com.guacom.agenda.model.Contato" />
	Testando seus parâmetros <br /> 
	A idade é ${ param.idade }.
</body>
</html>