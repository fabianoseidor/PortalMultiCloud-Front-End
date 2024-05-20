<%@tag import="br.com.portal.model.ModelSuporteB1"%>
<%@tag import="br.com.portal.model.ModelContrato"%>
<%@tag import="java.util.List"%>
<%@tag body-content="empty" %>

<jsp:useBean id="beanSuporteB1" class="br.com.portal.dao.DAOContratoRepository" ></jsp:useBean>

<%
   String selected = "";
   java.util.List<ModelSuporteB1> modelComercia = beanSuporteB1.selecaoSuporteB1();
   ModelContrato modelContrato = (ModelContrato) request.getAttribute("modelContrato");
   Long IdSuporteB1 = 0L;
   
   if( modelContrato != null ){
	   if(modelContrato.getId_suporte_b1() != null) 
		   IdSuporteB1 = modelContrato.getId_suporte_b1();
   }

   for (ModelSuporteB1 mComercia : modelComercia) {
     Long id_suporte_b1 = mComercia.getId_suporte_b1();
     String nome_suporte = mComercia.getNome_suporte();
	 if( IdSuporteB1 == id_suporte_b1 )
         selected = "selected";
	 else selected = "";
	 
     out.println("<option value=" + id_suporte_b1 + " " + selected + ">" + nome_suporte + "</option>");
   }
%>
