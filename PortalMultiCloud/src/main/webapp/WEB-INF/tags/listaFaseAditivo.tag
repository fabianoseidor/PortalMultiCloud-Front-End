<%@tag import="br.com.portal.model.ModelFaseContrato"%>
<%@tag import="br.com.portal.model.ModelAditivo"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanFaseContrato" class="br.com.portal.dao.DAOFaseContrato" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelFaseContrato> modelFaseContratos = beanFaseContrato.listaFaseContrato();
   ModelAditivo modelAditivo = (ModelAditivo) request.getAttribute("modelAditivo");
   Long idFaseFontratoRecuperado = 0L;
   
   if( modelAditivo != null ) {
	   if( modelAditivo.getId_fase_contrato() != null ) 
	       idFaseFontratoRecuperado = modelAditivo.getId_fase_contrato();
   }
   for (ModelFaseContrato faseContrato : modelFaseContratos) {
	 Long idFaseContrato = faseContrato.getId_fase_contrato();
	 String nomeFaseContrato = faseContrato.getFase_contrato();

	 if( idFaseFontratoRecuperado == idFaseContrato )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idFaseContrato + " " + selected + ">" + nomeFaseContrato + "</option>");
}
   

%>
