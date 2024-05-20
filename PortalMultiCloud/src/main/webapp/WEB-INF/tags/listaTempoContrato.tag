<%@tag import="br.com.portal.model.ModelTempoContrato"%>
<%@tag import="br.com.portal.model.ModelContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanContrato" class="br.com.portal.dao.DAOContratoRepository" ></jsp:useBean>

<%
   String selected = "";
   List<ModelTempoContrato> modelTempoContratos = beanContrato.getListaTempoContrato();
   ModelContrato modelContrato = (ModelContrato) request.getAttribute("modelContrato");
   Long idTempoContratoRecuperado = 0L;
   
   if( modelContrato != null ){
	   if(modelContrato.getId_tempo_contrato() != null) 
		   idTempoContratoRecuperado = modelContrato.getId_tempo_contrato();
   }

   for (ModelTempoContrato TempoContrato : modelTempoContratos) {
	 Long idTempoContrato = TempoContrato.getId_tempo_contrato();
	 String descTempo     = TempoContrato.getDesc_tempo();
	 if( idTempoContratoRecuperado == idTempoContrato )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idTempoContrato + " " + selected + ">" + descTempo + "</option>");
}
   

%>
