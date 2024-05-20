<%@tag import="br.com.portal.model.ModelServicoContratado"%>
<%@tag import="br.com.portal.model.ModelContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanServicoContratado" class="br.com.portal.dao.DAOServicoContratado" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelServicoContratado> modelServicoContratados = beanServicoContratado.listaServicoContratadoSelect();
   ModelContrato modelContrato = (ModelContrato) request.getAttribute("modelContrato");
   Long idServicoContratadoRecuperado = 0L;
   
   if( modelContrato != null ) {
	   if(modelContrato.getId_servico_contratado() != null )
	      idServicoContratadoRecuperado = modelContrato.getId_servico_contratado();
   }

   for (ModelServicoContratado modelServicoContratado : modelServicoContratados) {
	 Long idSservicoContratado = modelServicoContratado.getId_servico_contratado();
	 String descServico = modelServicoContratado.getDesc_servico();

	 if( idServicoContratadoRecuperado == idSservicoContratado )
  	     selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + idSservicoContratado + " " + selected + ">" + descServico + "</option>");
}
   

%>
