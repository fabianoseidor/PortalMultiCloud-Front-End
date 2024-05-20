<%@tag import="br.com.portal.model.ModelStatusContrato"%>
<%@tag import="br.com.portal.model.ModelListAditivoProduto"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanStatusContrato" class="br.com.portal.dao.DAOStatusContrato" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelStatusContrato> modelStatusContratos = beanStatusContrato.listaStatusContrato();
   ModelListAditivoProduto listAditivoProduto = (ModelListAditivoProduto) request.getAttribute("modelListAditivoProduto");
   Long idStatusFontratoRecuperado = 0L;
   
   if( listAditivoProduto != null ) {
	   if( listAditivoProduto.getId_status_aditivo() != null )
	       idStatusFontratoRecuperado = listAditivoProduto.getId_status_aditivo();
   }

   for (ModelStatusContrato statusContrato : modelStatusContratos) {
	 Long idStatusContrato = statusContrato.getId_status_contrato();
	 String nomestatusContrato = statusContrato.getStatus_contrato();

	 if( idStatusFontratoRecuperado == idStatusContrato )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idStatusContrato + " " + selected + ">" + nomestatusContrato + "</option>");
}
   

%>
