
<%@tag import="br.com.portal.model.ModelCliente"%>
<%@tag import="br.com.portal.model.ModelStatusCliente"%>
<%@tag body-content="empty" %>
<jsp:useBean id="beanStatusCli" class="br.com.portal.dao.DAOStatusCliente" ></jsp:useBean>

<% 
   String selected = "";
   java.util.List<ModelStatusCliente> porteEmpClientes = beanStatusCli.getListaStatusCliente();
   ModelCliente modelCliente = (ModelCliente) request.getAttribute("modelClienteManu");
   Long idStatusCli = 0L;

   if( modelCliente != null ){
	   if( modelCliente.getId_status_emp() != null )
	       idStatusCli = modelCliente.getId_status_emp();
   }
   
   for (ModelStatusCliente lisStatusCli : porteEmpClientes) {
		 Long idStatus = lisStatusCli.getId_status();
		 String status = lisStatusCli.getStatus();
		 if( idStatusCli == idStatus )
	  	     selected = "selected";
		 else selected = "";
		 
	     out.println("<option value=" + idStatus + " " + selected + ">" + status + "</option>");

	  
   }
%>