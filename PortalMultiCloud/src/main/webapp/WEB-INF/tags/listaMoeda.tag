<%@tag import="br.com.portal.model.ModelMoeda"%>
<%@tag import="br.com.portal.model.ModelContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanMoeda" class="br.com.portal.dao.DAOAditivoModalRecurso" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelMoeda> modelMoedas = beanMoeda.selecaoMoeda();
   ModelContrato modelContrato = (ModelContrato) request.getAttribute("modelContrato");
   Long idMoedaRecuperado = 0L;
   
   if( modelContrato != null ){
	   if(modelContrato.getId_moeda() != null) 
		   idMoedaRecuperado = modelContrato.getId_moeda();
   }

   for (ModelMoeda modelMoeda : modelMoedas) {
     Long id_moeda = modelMoeda.getId_moeda();
     String moeda = modelMoeda.getMoeda();
	 if( idMoedaRecuperado == id_moeda )
         selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + id_moeda + " " + selected + ">" + moeda + "</option>");
   }
%>
