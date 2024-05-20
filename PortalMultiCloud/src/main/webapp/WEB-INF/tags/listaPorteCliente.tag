<%@tag import="br.com.portal.model.ModelCliente"%>
<%@tag import="br.com.portal.model.ModelPorteCliente"%>
<%@tag import="br.com.portal.model.ModelLogin"%>
<%@tag body-content="empty" %>
<jsp:useBean id="beanPorteCli" class="br.com.portal.dao.DAOPorteCliente" ></jsp:useBean>

<% 
   String selected = "";
   java.util.List<ModelPorteCliente> porteEmpClientes = beanPorteCli.getListaPorteEmpresaCliente();
   ModelCliente modelCliente = (ModelCliente) request.getAttribute("modelClienteManu");

   Long portCli = 0L;

   if( modelCliente != null ){
	   if(modelCliente.getId_porte_emp() != null )
	      portCli = modelCliente.getId_porte_emp();
   }
   
   for (ModelPorteCliente lisPoreCli : porteEmpClientes) {
		 Long idPorte       = lisPoreCli.getId_porte();
		 String porte_clie  = lisPoreCli.getPorte_empresa();
		 if( portCli == idPorte )
	  	     selected = "selected";
		 else selected = "";
		 
	     out.println("<option value=" + idPorte + " " + selected + ">" + porte_clie + "</option>");

	  
   }
%>