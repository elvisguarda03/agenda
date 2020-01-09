<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 
Criação de atributo para que o nome do campo e o id sejam dinâmicos. Através dessas diretivas 
a tag receberá dois parâmetros ambos obrigatórios.
--%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="value" required="false" %>

<script type="text/javascript">
	$(document).ready(function () {
		$('#${id}').datepicker({ dateFormat: 'dd/mm/yy' });
	});
</script>

<input id="${ id }" name="${ name }" value="${ not empty value ? value : '' }" type="text" />