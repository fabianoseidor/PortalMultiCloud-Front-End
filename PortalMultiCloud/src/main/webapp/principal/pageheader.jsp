<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
   <div class="page-header">
       <div class="page-block">
           <div class="row align-items-center">
               <div class="col-md-8">
                   <div class="page-header-title">
                       <h5 class="m-b-10">Gestão de Contratos<%= session.getAttribute("nomeDatabase") %></h5>
                       <p class="m-b-0">Bem Vindo ao Portal MultiCloud</p>
                   </div>
               </div>
               <div class="col-md-4">
                   <ul class="breadcrumb-title">
                       <li class="breadcrumb-item">
                           <i class="fa fa-home"></i>
                       </li>
                       <li class="breadcrumb-item">
                       <!-- 
                       <a href="<%= request.getContextPath() %>/principal/principal.jsp">Portal MultiCloud</a>
                        -->
                       <a href="<%= request.getContextPath() %>/principal/PagPrincipal.jsp">Portal MultiCloud</a>
                          
                       </li>
                   </ul>
               </div>
           </div>
       </div>
   </div>
