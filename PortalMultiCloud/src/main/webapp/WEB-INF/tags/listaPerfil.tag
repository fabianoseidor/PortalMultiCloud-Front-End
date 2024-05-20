<%@tag import="br.com.portal.modelPerfil.ModalPerfil"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanPerfil" class="br.com.portal.daoPerfil.DAOPerfil" ></jsp:useBean>

<%
   List<ModalPerfil> listaPerfiis = beanPerfil.getListaPerfil();

   for (ModalPerfil listaPerfiil : listaPerfiis) {
	 Long id_perfil     = listaPerfiil.getId_perfil();
	 String nome_perfil = listaPerfiil.getNome_perfil();
	 
     out.println("<option value=" + id_perfil + ">" + nome_perfil + "</option>");
}
   

%>
