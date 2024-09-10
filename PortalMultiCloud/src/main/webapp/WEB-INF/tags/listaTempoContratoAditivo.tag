<%@tag import="br.com.portal.model.ModelTempoContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanContrato" class="br.com.portal.dao.DAOContratoRepository" ></jsp:useBean>

<%
   List<ModelTempoContrato> modelTempoContratos = beanContrato.getListaTempoContrato();

   for (ModelTempoContrato TempoContrato : modelTempoContratos) {
	 Long idTempoContrato = TempoContrato.getId_tempo_contrato();
	 String descTempo     = TempoContrato.getDesc_tempo();
	 
     out.println("<option value=" + idTempoContrato + ">" + descTempo + "</option>");
}
   

%>
