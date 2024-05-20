<%@tag import="br.com.portal.model.ModelComercial"%>
<%@tag import="br.com.portal.model.ModelContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanComercial" class="br.com.portal.dao.DAOContratoRepository" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelComercial> modelComercia = beanComercial.selecaoComercial();
   ModelContrato modelContrato = (ModelContrato) request.getAttribute("modelContrato");
   Long IdComercial = 0L;
   
   if( modelContrato != null ){
	   if(modelContrato.getId_comercial() != null) 
		   IdComercial = modelContrato.getId_comercial();
   }

   for (ModelComercial mContrato : modelComercia) {
     Long id_comercial = mContrato.getId_comercial();
     String nome_comercial = mContrato.getNome_comercial();
	 if( IdComercial == id_comercial )
         selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + id_comercial + " " + selected + ">" + nome_comercial + "</option>");
   }
%>
