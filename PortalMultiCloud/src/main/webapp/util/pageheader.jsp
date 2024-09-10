<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
   <div class="page-header">
       <div class="page-block">
           <div class="row align-items-center">
               <div class="col-md-12">
                   <div class="page-header-title" >
					  <div  class="row">
						  <div class="col-sm-12">
						  
							  <div class="row">
								  <div class="col-lg-4">
								  	<h5 class="m-b-1">Seidor MultiCloud - Portal de GMUD</h5>
								  </div>
								  <div  class="col-lg-8">
								    <h5 class="text-right" style="color: white;">
								        <a href="<%= session.getAttribute("urlAPIInicioPortalLogin")%>" class="waves-effect waves-dark" style="margin-top: 10px">               
                                            <span style="color: white;" class="pcoded-micon"><i class="ti-home"></i></span>
                                            <span style="color: white;" class="pcoded-mtext" data-i18n="nav.dash.main"><strong >home</strong> </span>
                                            <span style="color: white;" class="pcoded-mcaret"></span>
                                        </a>
								        <strong style="padding-left: 150px">Usuário: </strong><%= session.getAttribute("nomeUsuario") %>
								    </h5>
								  </div>
							  </div>
							  
						  </div>
					  </div>
                   </div>
               </div>
           </div>
       </div>
   </div>
   
