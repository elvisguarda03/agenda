<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- <%@ taglib uri="http://displaytag.sf.net" prefix="display"%> --%>
<%-- <%@ page import="java.util.List, java.util.Date, java.text.SimpleDateFormat, br.com.guacom.agenda.model.Contato"%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Contatos</title>
</head>
<body>
	<c:import url="cabecalho.jsp" />

	<%-- <jsp:useBean id="dao" class="br.com.guacom.agenda.dao.ContatoDao"></jsp:useBean> --%>

	<table border="1">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Email</th>
				<th>Endereço</th>
				<th>Data Nascimento</th>
				<th>Ações</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="c" items="${ contatos }" varStatus="id">
				<tr bgcolor="#${ id.count % 2 == 0 ? 'aaee88' : 'ffffff'}">
					<td>${ c.nome }</td>
					<td>
						<c:choose>
							<c:when test="${ not empty c.email }">
								<a href="mailto:${ c.email }">${ c.email }</a>
							</c:when>
							<c:otherwise>
								E-mail não preenchido.
							</c:otherwise>
						</c:choose>
					</td>
					<td>${ c.endereco }</td>
					<td><fmt:formatDate value="${ c.dataNascimento.time }"
							pattern="dd/MM/yyyy" /></td>
					<td>
						<a href="<c:url value="/editaContatoForm?id=${ c.id }" />">Editar</a>
						<a href="<c:url value="/removeContato?id=${ c.id }" />">Remover</a>
					</td>
				</tr>
			</c:forEach>

			<%-- 
		<%
			List<Contato> contatos = (List<Contato>) request.getAttribute("contatos");
			for (Contato c : contatos) {
			String dataNascimento = new SimpleDateFormat("dd/MM/yyyy").format((Date)c.getDataNascimento().getTime());
		
		<%
			}
		%>
		--%>
		</tbody>
	</table>

	<c:import url="rodape.jsp" />
</body>
</html>