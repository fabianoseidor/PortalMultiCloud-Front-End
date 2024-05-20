<%@tag import="br.com.portal.model.ModelMonitoramento"%>
<%@tag import="br.com.portal.model.ModelAditivo"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanRecusoAditivo" class="br.com.portal.dao.DAOAditivoModalRecurso" ></jsp:useBean>

<%
   String selected = "";
   List<ModelMonitoramento> modelMonitoramentos = beanRecusoAditivo.listaMonitoramento();
 
   for (ModelMonitoramento modelMonitoramento : modelMonitoramentos) {
	 Long   idMonitoramento   = modelMonitoramento.getId_monitoramento();
	 String descMonitoramento = modelMonitoramento.getDesc_monitoramento();

	 out.println("<option value=" + idMonitoramento + " " + selected + ">" + descMonitoramento + "</option>");
  }
   

%>
