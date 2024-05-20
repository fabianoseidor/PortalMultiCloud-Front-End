<%@tag import="br.com.portal.modelPerfil.ModalPaginaPerfil"%>
<%@tag import="br.com.portal.modelPerfil.ModelSecao"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanSecao" class="br.com.portal.daoPerfil.DAOSecao" ></jsp:useBean>

<%
   String selected = "";
   List<ModelSecao> listaSecoes = beanSecao.getListaSecao();
   ModalPaginaPerfil modelSecao = (ModalPaginaPerfil) request.getAttribute("modalPaginaPerfil");
   
//  modelLogin
   
   Long idSecaoRecup = 0L;
   
   if( modelSecao != null ) {
	   if( modelSecao.getId_secao() != null ) idSecaoRecup = modelSecao.getId_secao();
   }

   for (ModelSecao listasecao : listaSecoes) {
	 Long id_secao = listasecao.getId_secao();
	 String nome_secao = listasecao.getNome_secao();
	 if( idSecaoRecup == id_secao )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + id_secao + " " + selected + ">" + nome_secao + "</option>");
}
   

%>
