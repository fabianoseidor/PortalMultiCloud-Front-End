<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="br.com.portal.model.consolidacao.ModelVlrFaturado"%> 


    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table class="table">
	  <thead>
	    <tr>
	      <th scope="col">PEP SAP     </th>
	      <th scope="col">Valor SAP   </th>
	      <th scope="col">PEP Portal  </th>
	      <th scope="col">Valor Portal</th>
		  <th scope="col">Diferença Desvio( SAP - Portal )</th>
	      <th scope="col">Status      </th>
	    </tr>
	  </thead>
      <tbody>
         <%
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "inline; filename=rel-valorSAP-valorPortal.xls");
            List<ModelVlrFaturado> listVlrFat = (List<ModelVlrFaturado>) session.getAttribute("listVlrFaturados"); 
            if( listVlrFat != null ) {
                for( ModelVlrFaturado list: listVlrFat ){
          %>          
          <tr>
                 <% if( !list.getStatus().trim().equals("OK") ) { %>
                        <td><%=list.getPepAFaturar()      %> </td>
                        <td><%=list.getVlrAFaturarView()  %> </td>
                        <td><%=list.getPepFaturado()      %> </td>
                        <td><%=list.getVlrFaturadoView()  %> </td>
                        <td><%=list.getVlrDesvioDiffView()%> </td>
                        <% if( list.getStatus().trim().equals("DIFF") ){ %>                       
					           <td> Divergência após aplicar Desvio Padrão</td>
					   <%}else if( list.getStatus().trim().equals("NOT_EXIST_FATURADO_NAO_TEM_NO_AFATURA") ) {%>
					          <td>PEP não encontrado no SAP</td>
					    <%}else if( list.getStatus().trim().equals("NOT_EXIST_AFATURA_NAO_TEM_NO_FATURADO") ) {%>
					           <td>PEP não encontrado no Portal</td>
					  
					     <%} %>
                <% } %>
          </tr>
          <%    }
            }%>
      </tbody>
  </table>

</body>
</html>