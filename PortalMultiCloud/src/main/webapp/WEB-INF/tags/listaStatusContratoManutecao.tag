<%@tag import="br.com.portal.model.ModelStatusContrato"%>
<%@tag import="br.com.portal.model.ModelContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanStatusContrato" class="br.com.portal.dao.DAOStatusContrato" ></jsp:useBean>

<%
   String selected    = "";
   String disabled    = "";
   
   java.util.List<ModelStatusContrato> modelStatusContratos = beanStatusContrato.listaStatusContratoManutencao();
   ModelContrato modelContrato = (ModelContrato) request.getAttribute("modelContrato");
   Long idStatusFontratoRecuperado = 0L;
   
   if( modelContrato != null ) {
	   if( modelContrato.getId_status_contrato() != null )
	       idStatusFontratoRecuperado = modelContrato.getId_status_contrato();
   }

   for (ModelStatusContrato statusContrato : modelStatusContratos) {
	 Long idStatusContrato = statusContrato.getId_status_contrato();
	 String nomestatusContrato = statusContrato.getStatus_contrato();

	 if(idStatusFontratoRecuperado == 5) disabled = "disabled";
	 else disabled = "";
	 
	 if( idStatusFontratoRecuperado == idStatusContrato ){
  	     selected = "selected";
  	     disabled = "";
	 }else selected = "";
	 
	 
     out.println("<option value=" + idStatusContrato + " " + disabled + " " + selected + ">" + nomestatusContrato + "</option>");
}
   

%>
