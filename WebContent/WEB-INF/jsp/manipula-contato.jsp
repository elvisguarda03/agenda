<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="guacom" %>

<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="<c:url value="/static/js/jquery.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/static/js/jquery-ui.js"/>"></script>
	<link type="text/css" href="<c:url value="/static/css/jquery.css" />" rel="stylesheet" />
</head>
<body>
	<c:import url="cabecalho.jsp" />

	<c:choose>
		<c:when test="${ empty contato }">
			<form action="adicionaContato" method="post">
				Nome: <input type="text" name="nome" /><br /> 
				E-mail: <input type="text" name="email" /><br /> 
				Endereço: <input type="text" name="endereco" /><br /> 
				Data Nascimento: <guacom:campoData id="dataNascimento" name="dataNascimento"/><br /> 
				
				<input type="submit" value="Gravar" />
			</form>
		</c:when>
		<c:otherwise>
			<fmt:formatDate value="${ contato.dataNascimento.time }" pattern="dd/MM/yyyy" var="dataNascimento" />
			
			<form action="alteraContato" method="post">
				Nome: <input type="text" name="nome" value="${ contato.nome }" /><br /> 
				E-mail: <input type="text" name="email" value="${ contato.email }" /><br /> 
				Endereço: <input type="text" name="endereco" value="${ contato.endereco }" /><br /> 
				Data Nascimento: <guacom:campoData id="dataNascimento" name="dataNascimento" value="${ dataNascimento }"/><br /> 
				
				<input type="hidden" value="${ contato.id }" name="id" />
				
				<input type="submit" value="Gravar" />
			</form>
		</c:otherwise>
	</c:choose>
	
	<c:import url="rodape.jsp" />
</body>
</html>