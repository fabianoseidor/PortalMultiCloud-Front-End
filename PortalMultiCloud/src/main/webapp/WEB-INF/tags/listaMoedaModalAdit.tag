<%@tag import="br.com.portal.model.ModelMoeda"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanMoeda" class="br.com.portal.dao.DAOAditivoModalRecurso" ></jsp:useBean>

<%
   java.util.List<ModelMoeda> modelMoedas = beanMoeda.selecaoMoeda();

   for (ModelMoeda modelMoeda : modelMoedas) {
     Long id_moeda = modelMoeda.getId_moeda();
     String moeda = modelMoeda.getMoeda();

     out.println("<option value=" + id_moeda +  ">" + moeda + "</option>");
   }
%>
