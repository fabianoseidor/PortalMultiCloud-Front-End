<%@tag import="br.com.portal.model.ModelServicoContratado"%>
<%@tag import="br.com.portal.model.ModelListAditivoProduto"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanServicoContratado" class="br.com.portal.dao.DAOServicoContratado" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelServicoContratado> modelServicoContratados = beanServicoContratado.listaServicoContratadoSelect();
   ModelListAditivoProduto listAditivoProduto = (ModelListAditivoProduto) request.getAttribute("modelListAditivoProduto");

   Long idServicoContratadoRecuperado = 0L;
   
   if( listAditivoProduto != null ) {
	   if(listAditivoProduto.getId_status_aditivo() != null )
	      idServicoContratadoRecuperado = listAditivoProduto.getId_status_aditivo();
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
