<%@tag import="br.com.portal.model.ModelStatusContrato"%>
<%@tag import="br.com.portal.model.ModelContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanStatusContrato" class="br.com.portal.dao.DAOStatusContrato" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelStatusContrato> modelStatusContratos = beanStatusContrato.listaStatusContratoCadastro();
   ModelContrato modelContrato = (ModelContrato) request.getAttribute("modelContrato");
   Long idStatusFontratoRecuperado = 0L;
   
   if( modelContrato != null ) {
	   if( modelContrato.getId_status_contrato() != null )
	       idStatusFontratoRecuperado = modelContrato.getId_status_contrato();
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
