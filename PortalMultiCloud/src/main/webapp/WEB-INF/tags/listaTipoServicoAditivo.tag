<%@tag import="br.com.portal.model.ModelRecursoAditivo"%>
<%@tag import="br.com.portal.model.ModelTipoServico"%>
<%@tag import="java.util.List"%>
<%@tag import="br.com.portal.dao.DAOTipoServico"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanTipoServ" class="br.com.portal.dao.DAOTipoServico" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelTipoServico> listaTipoServs = beanTipoServ.getListaTipoServicos();
   ModelRecursoAditivo modelRecursoAditivo = (ModelRecursoAditivo) request.getAttribute("modelRecursoAditivo");
   
//  modelLogin
   
   Long idTipoServRecup = 0L;
   
   if( modelRecursoAditivo != null ) {
	   if( modelRecursoAditivo.getId_tipo_servico() != null ) idTipoServRecup = modelRecursoAditivo.getId_tipo_servico();
   }

   for (ModelTipoServico listaTipoServ : listaTipoServs) {
	 Long idTipoServ = listaTipoServ.getId_tipo_servico();
	 String TipoServ = listaTipoServ.getTipo_servico();
	 if( idTipoServRecup == idTipoServ )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idTipoServ + " " + selected + ">" + TipoServ + "</option>");
}
   

%>
