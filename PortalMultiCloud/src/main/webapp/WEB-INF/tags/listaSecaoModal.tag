<%@tag import="br.com.portal.modelPerfil.ModelSecao"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanSecao" class="br.com.portal.daoPerfil.DAOSecao" ></jsp:useBean>

<%
   List<ModelSecao> listaSecoes = beanSecao.getListaSecao();

   for (ModelSecao listasecao : listaSecoes) {
	 Long id_secao = listasecao.getId_secao();
	 String nome_secao = listasecao.getNome_secao();
	 
     out.println("<option value=" + id_secao + ">" + nome_secao + "</option>");
}
   

%>
