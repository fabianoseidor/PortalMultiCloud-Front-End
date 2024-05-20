<%@tag import="br.com.portal.model.ModelTipoAditivo"%>
<%@tag import="br.com.portal.model.ModelListAditivoProduto"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanTipoAditivo" class="br.com.portal.dao.DAOAditivoModalRecurso" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelTipoAditivo> modelTipoAditivos = beanTipoAditivo.listaTipoAditivoSelect();
   ModelListAditivoProduto listAditivoProduto    = (ModelListAditivoProduto) request.getAttribute("modelListAditivoProduto");
   Long idtipoAditivoRecuperado = 0L;
   
   if( listAditivoProduto != null ) {
	   if(listAditivoProduto.getId_tipo_aditivo() != null )
		   idtipoAditivoRecuperado = listAditivoProduto.getId_tipo_aditivo();
   }

   for (ModelTipoAditivo modelTipoAditivo : modelTipoAditivos) {
	 Long idTipoAditivo     = modelTipoAditivo.getId_tipo_aditivo();
	 String descTipoAditivo = modelTipoAditivo.getDesc_tipo_ditivo();

	 if( idtipoAditivoRecuperado == idTipoAditivo )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idTipoAditivo + " " + selected + ">" + descTipoAditivo + "</option>");
}
   

%>
