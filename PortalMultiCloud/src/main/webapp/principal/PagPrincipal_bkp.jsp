<%@page import="br.com.portal.model.ModelPagPrincipal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>  
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.com.portal.modelPerfil.ModelPerfilLogado"%>     
<%@page import="java.util.List"%>
<%@page import="java.util.Optional"%>
    
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>
    <style>

	.wrimagecard{	
		margin-top: 100;
	    margin-bottom: 1.0rem;
	    text-align: center;
	    position: relative;
	    background: #fff;
	  --  box-shadow: 12px 15px 20px 0px rgba(46,61,73,0.15);
	    border-radius: 10px;
	    transition: all 0.3s ease;
	}
	.wrimagecard .fa{
		position: relative;
	    font-size: 35px;
	}
	.wrimagecard-topimage_header{
	padding: 100px;
	}
		
	
    a.wrimagecard:hover, .wrimagecard-topimage:hover, i.wrimagecard:hover{
	     box-shadow: 2px 4px 8px 0px rgba(46,61,73,0.2);
	     background: #FAFAFA;
	    transform: scale(1.01);
	    
	}	
	
	.wrimagecard-topimage a {
	    width: 100%;
	    height: 100%;
	    display: block;
	}
	.wrimagecard-topimage_title {
	    padding: 2px 2px;
	    height: 8px;
	    padding-bottom: 01.0rem;
	    position: relative;
	    text-align: center;
	}
	.wrimagecard-topimage a {
	    border-bottom: none;
	    text-decoration: none;
	    color: #525c65;
	    transition: color 0.3s ease;
	}

   </style>
   
  <body>
  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
  
    <div class="pcoded-overlay-box"></div>
    <div class="pcoded-container navbar-wrapper">
      <!-- Chamada para o Menu de Barras -->
      <jsp:include page="navbarheader.jsp"></jsp:include>
      
      <div class="pcoded-main-container">
          <div class="pcoded-wrapper">
          
                    <div class="pcoded-inner-content">
                        <div class="main-body">
                            <div class="page-wrapper">
                                <div class="page-body">
								    <!-- ########################### -->
									<!--          Cadastro           -->
									<!-- ########################### -->
								    <p class="h5"><strong>Cadastro</strong></p>
								    <div class="row my-12 text-primary">
                                         <% List<ModelPerfilLogado> perfilUserLogados = (List<ModelPerfilLogado>) session.getAttribute("perfilUserLogados"); 
                                            if( perfilUserLogados != null ) {
                                                Optional<ModelPerfilLogado> perfilLogado = perfilUserLogados.stream().filter( x -> "Cadastro".equals(x.getNome_secao()) ).findFirst();
                                                if( perfilLogado.isPresent() ) { 
                                                    perfilLogado = perfilUserLogados.stream().filter( x -> "cadastroCliente".equals(x.getDesc_pagina() ) ).findFirst();
                                        %>	        <!-- Menu Cadastro de Cliente -->
								                    <div class="col-md-6" style="max-width: 15rem;">
								                       <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
								                         <div class="card-block">
										                    <div class="text-c-purple text-right">
										<%                  if( perfilLogado.isPresent() ) {  %>
				                                                <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
				                        <%                  } else { %>
				                                                <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
				                        <%                  }        %>                             
				                                            </div>
										<%                  if( perfilLogado.isPresent() ) {  %>
										                        <a href="<%= request.getContextPath() %>/principal/cliente.jsp" class="waves-effect waves-dark">
				                        <%                  } else { %>
				                                                <a href="#" style="pointer-events: none;" > 
				                        <%                  }        %>                             
										                         <h6><i class="fa fa-group fa-3x text-primary"></i></h6>
										                         <h6 class="card-title text-dark">Cliente</h6>
										                    </a>
								                         </div>
								                       </div>
								                    </div>
					                    <%          perfilLogado = perfilUserLogados.stream().filter( x -> "cadastroContrato".equals(x.getDesc_pagina() ) ).findFirst(); %>
					                                <!-- Menu Cadastro de Contrato -->
							                        <div class="col-md-6" style="max-width: 15rem;">
					                                   <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
					                                     <div class="card-block">
					                                        <div class="text-c-purple text-right">
										<%                  if( perfilLogado.isPresent() ) {  %>
				                                                <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
				                        <%                  } else { %>
				                                                <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
				                        <%                  }        %>                             
		                                                    </div>
										<%                  if( perfilLogado.isPresent() ) {  %>
					                                           <a href="<%= request.getContextPath() %>/cadastro/cadastroContrato.jsp" class="waves-effect waves-dark">
				                        <%                  } else { %>
				                                                <a href="#" style="pointer-events: none;" > 
				                        <%                  }        %>                             
					                                             <h6><i class="fa fa-handshake-o fa-3x text-primary"></i></h6>
					                                             <h6 class="card-title text-dark">Contrato</h6>
					                                          </a>
					                                     </div>
					                                   </div>  
					                                </div>	
								        <%          
								                }  else{
			                                        %>  
											           <div class="col-md-6" style="max-width: 15rem;">
											             <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											                <div class="card-block">
													           <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                                <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                           </div>
													           <a href="#" style="pointer-events: none;" > 
													                <h6><i class="fa fa-group fa-3x text-primary" ></i></h6>
													                <h6 class="card-title text-dark">Cliente</h6>
													           </a>
											                </div>								                
											             </div>
				                                       </div>
				                                       
												       <div class="col-md-6" style="max-width: 15rem;">
												         <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
												            <div class="card-block">
												                <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
						                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
						                                       </div>
						                                       <a href="#" style="pointer-events: none;" >
												                  <h6><i class="fa fa-handshake-o fa-3x text-primary"></i></h6>
												                  <h6 class="card-title text-dark">Contrato</h6>
												               </a> 
												            </div>
												         </div>
												       </div>								
		                                        <%  } 
                                            }
                                            %>
								    </div>

								    <!-- ########################### -->
									<!--         Manutenção          -->
									<!-- ########################### -->
									<br><br>
								    <p class="h5"><strong>Manutenção</strong></p>
								    <div class="row my-12 text-primary">
                                         <% if( perfilUserLogados != null ) {
                                                Optional<ModelPerfilLogado> perfilLogado = perfilUserLogados.stream().filter( x -> "Manutenção".equals(x.getNome_secao()) ).findFirst();
                                                if( perfilLogado.isPresent() ) { 
                                                    perfilLogado = perfilUserLogados.stream().filter( x -> "mautencaoCliente".equals(x.getDesc_pagina() ) ).findFirst();
                                        %>	        <!-- Menu Manutenção de cliente -->
												    <div class="col-md-4" style="max-width: 15rem;">
												        <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
												            <div class="card-block">
										                         <div class="text-c-purple text-right">
										<%                       if( perfilLogado.isPresent() ) {  %>
				                                                     <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
				                        <%                       } else { %>
				                                                     <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
				                        <%                       }        %>                             
				                                                 </div>
										<%                       if( perfilLogado.isPresent() ) {  %>
                                                                    <a href="<%= request.getContextPath() %>/ServletManutencaoCliente?acao=listarCliente" class="waves-effect waves-dark"> 
				                        <%                       } else { %>
				                                                    <a href="#" style="pointer-events: none;" > 
				                        <%                       }        %>                             
												                    <h6><i class="fa fa-group fa-3x text-primary"></i></h6>
												                    <h6 class="card-title text-dark">Clientes</h6>
                                                                 </a>
												            </div>
												        </div>
												    </div>
					                    <%          perfilLogado = perfilUserLogados.stream().filter( x -> "mautencaoContrato".equals(x.getDesc_pagina() ) ).findFirst();  %>
					                                <!-- Menu Manutenção de Cadastro Contrato -->
												    <div class="col-md-4" style="max-width: 15rem;">
												        <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
												            <div class="card-block">
										                         <div class="text-c-purple text-right">
										<%                       if( perfilLogado.isPresent() ) {  %>
				                                                     <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
				                        <%                       } else { %>
				                                                     <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
				                        <%                       }        %>                             
				                                                 </div>
										<%                       if( perfilLogado.isPresent() ) {  %>
                                                                    <a href="<%= request.getContextPath() %>/principal/contrato.jsp" class="waves-effect waves-dark">
				                        <%                       } else { %>
				                                                    <a href="#" style="pointer-events: none;" > 
				                        <%                       }        %>                             
												                    <h6><i class="fa fa-handshake-o fa-3x text-primary"></i></h6>
												                    <h6 class="card-title text-dark">Contratos</h6>
                                                                 </a>
												            </div>
												        </div>
												    </div>
					                    <%          perfilLogado = perfilUserLogados.stream().filter( x -> "mautencaoRascunho".equals(x.getDesc_pagina() ) ).findFirst();  %>
					                                <!-- Menu Manutenção de Cadastro Contrato -->
												    <div class="col-md-4" style="max-width: 15rem;">
												       <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
												           <div class="card-block">
										                         <div class="text-c-purple text-right">
										<%                       if( perfilLogado.isPresent() ) {  %>
				                                                     <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
				                        <%                       } else { %>
				                                                     <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
				                        <%                       }        %>                             
				                                                 </div>
										<%                       if( perfilLogado.isPresent() ) {  %>
                                                                    <a href="<%= request.getContextPath() %>/principal/rascunho.jsp" class="waves-effect waves-dark">
				                        <%                       } else { %>
				                                                    <a href="#" style="pointer-events: none;" > 
				                        <%                       }        %>                             
												                    <h6><i class="fa fa-edit fa-3x text-primary"></i></h6>
												                    <h6 class="card-title text-dark">Rascunhos</h6>
                                                                 </a>
												           </div>
												       </div>
												    </div>
								        <%      } else{ %>
	                                             <div class="col-md-4" style="max-width: 15rem;">
										             <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
										                <div class="card-block">
												           <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
				                                                <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
				                                           </div>
												           <a href="#" style="pointer-events: none;" > 
												                <h6><i class="fa fa-group fa-3x text-primary" ></i></h6>
												                <h6 class="card-title text-dark">Cliente</h6>
												           </a>
										                </div>								                
										             </div>
			                                       </div>
			                                       
											       <div class="col-md-4" style="max-width: 15rem;">
											         <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											            <div class="card-block">
											                <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                       </div>
					                                       <a href="#" style="pointer-events: none;" >
											                  <h6><i class="fa fa-handshake-o fa-3x text-primary"></i></h6>
											                  <h6 class="card-title text-dark">Contrato</h6>
											               </a> 
											            </div>
											         </div>
											       </div>								

											       <div class="col-md-4" style="max-width: 15rem;">
											         <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											            <div class="card-block">
											                <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                       </div>
					                                       <a href="#" style="pointer-events: none;" >
											                  <h6><i class="fa fa-handshake-o fa-3x text-primary"></i></h6>
											                  <h6 class="card-title text-dark">Rascunhos</h6>
											               </a> 
											            </div>
											         </div>
											       </div>								
	                                    <%      } 
                                            }
                                         
                                        %>
								    </div>

								    <!-- ########################### -->
									<!--           Usuários          -->
									<!-- ########################### -->
									<br><br>
								    <p class="h5"><strong>Usuários</strong></p>
								    <div class="row my-12 text-primary">
                                         <% if( perfilUserLogados != null ) {
                                                Optional<ModelPerfilLogado> perfilLogado = perfilUserLogados.stream().filter( x -> "Configuração".equals(x.getNome_secao()) ).findFirst();
                                                if( perfilLogado.isPresent() ) { 
                                                    perfilLogado = perfilUserLogados.stream().filter( x -> "usuario".equals(x.getDesc_pagina() ) ).findFirst();
                                        %>	        <!-- Menu Configuração Usuario -->
											        <div class="col-md-4" style="max-width: 15rem;">
											            <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											                <div class="card-block">
											                     <div class="text-c-purple text-right">
										<%                          if( perfilLogado.isPresent() ) {  %>
				                                                        <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
				                        <%                          } else { %>
				                                                        <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
				                        <%                          }        %>                             
				                                                 </div>
										<%                       if( perfilLogado.isPresent() ) {  %>
                                                                    <a href="<%= request.getContextPath() %>/principal/usuario.jsp" class="waves-effect waves-dark">
				                        <%                       } else { %>
				                                                    <a href="#" style="pointer-events: none;" > 
				                        <%                       }        %>                             
											                        <h6><i class="fa fa-user-plus fa-3x text-primary"></i></h6>
											                        <h6 class="card-title text-dark">Cad. Usuários</h6>
											                    </a>
											                </div>
											            </div>
											        </div>
					                    <%          perfilLogado = perfilUserLogados.stream().filter( x -> "trocaSenha".equals(x.getDesc_pagina() ) ).findFirst();  %>
					                                <!-- Menu Configuração Troca Senha -->
											         <div class="col-md-4" style="max-width: 15rem;">
											            <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											                <div class="card-block">
											                     <div class="text-c-purple text-right">
										<%                           if( perfilLogado.isPresent() ) {  %>
				                                                         <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
				                        <%                           } else { %>
				                                                         <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
				                        <%                           }        %>                             
				                                                 </div>
										<%                       if( perfilLogado.isPresent() ) {  %>
                                                                    <a href="<%= request.getContextPath() %>/principal/trocaSenha.jsp" class="waves-effect waves-dark">
				                        <%                       } else { %>
				                                                    <a href="#" style="pointer-events: none;" > 
				                        <%                       }        %>                             
											                        <h6><i class="fa fa-lock fa-3x text-primary"></i></h6>
											                        <h6 class="card-title text-dark">Troca de Senha</h6>
											                    </a>
											                </div>
											            </div>
											        </div>
					                    <%          perfilLogado = perfilUserLogados.stream().filter( x -> "manutencaoUsuario".equals(x.getDesc_pagina() ) ).findFirst();  %>
					                                <!-- Menu Configuração Troca Senha -->											
											        <div class="col-md-4"style="max-width: 15rem;">
											           <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											               <div class="card-block">
											                    <div class="text-c-purple text-right">
										<%                          if( perfilLogado.isPresent() ) {  %>
				                                                        <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
				                        <%                          } else { %>
				                                                        <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
				                        <%                          }        %>                             
				                                                </div>
										<%                      if( perfilLogado.isPresent() ) {  %>
                                                                   <a href="<%= request.getContextPath() %>/ServletManutencaoUsuarioController?acao=listarUser" class="waves-effect waves-dark">
				                        <%                       } else { %>
				                                                   <a href="#" style="pointer-events: none;" > 
				                        <%                       }        %>                             
											                       <h6><i class="fa fa-gear fa-3x text-primary"></i></h6>
											                       <h6 class="card-title text-dark">Manuteção User</h6>
											                     </a>
											                </div>
											           </div>
											       </div>
								        <%      } else{ %>  
										           <div class="col-md-4" style="max-width: 15rem;">
										             <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
										                <div class="card-block">
												           <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
				                                                <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
				                                           </div>
												           <a href="#" style="pointer-events: none;" > 
												                <h6><i class="fa fa-user-plus fa-3x text-primary" ></i></h6>
												                <h6 class="card-title text-dark">Cad. Usuários</h6>
												           </a>
										                </div>								                
										             </div>
			                                       </div>
			                                       
											       <div class="col-md-4" style="max-width: 15rem;">
											         <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											            <div class="card-block">
											                <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                       </div>
					                                       <a href="#" style="pointer-events: none;" >
											                  <h6><i class="fa fa-lock fa-3x text-primary"></i></h6>
											                  <h6 class="card-title text-dark">Troca de Senha</h6>
											               </a> 
											            </div>
											         </div>
											       </div>								

											       <div class="col-md-4" style="max-width: 15rem;">
											         <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											            <div class="card-block">
											                <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                       </div>
					                                       <a href="#" style="pointer-events: none;" >
											                  <h6><i class="fa fa-gear fa-3x text-primary"></i></h6>
											                  <h6 class="card-title text-dark">Manuteção User</h6>
											               </a> 
											            </div>
											         </div>
											       </div>								
									        
	                                    <%      }  
                                            }
                                        %>
                                    </div>

								    <!-- ########################### -->
									<!--     Configuração Perfil     -->
									<!-- ########################### -->
									<br><br>
								    <p class="h5"><strong>Configuração Perfil</strong></p>
								    <div class="row my-12 text-primary">
                                         <% if( perfilUserLogados != null ) {
                                                Optional<ModelPerfilLogado> perfilLogado = perfilUserLogados.stream().filter( x -> "Configuração Perfil".equals(x.getNome_secao()) ).findFirst();
                                                if( perfilLogado.isPresent() ) { 
                                                    perfilLogado = perfilUserLogados.stream().filter( x -> "configPerfilUser".equals(x.getDesc_pagina() ) ).findFirst();
                                        %>	        <!-- Menu "Configuração Perfil" config. Perfil User -->	
                                                    <div class="col-md-3" style="max-width: 15rem;">
											            <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											                <div class="card-block">
					                                             <div class="text-c-purple text-right">
                                        <%                       if( perfilLogado.isPresent() ) {  %>
				                                                     <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
                                        <%                       } else { %>
				                                                     <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
                                        <%                       }        %>                             
		                                                         </div>
                                        <%                       if( perfilLogado.isPresent() ) {  %>
					                                                 <a href="<%= request.getContextPath() %>/perfil/configPerfilUser.jsp" class="waves-effect waves-dark">
                                        <%                       } else { %>
				                                                     <a href="#" style="pointer-events: none;" > 
                                        <%                       }        %>                             
											                       <h6><i class="fa fa-user-circle-o fa-3x text-primary"></i></h6>
											                       <h6 class="card-title text-dark">Add User Perfil</h6>
											                     </a>
											                </div>
											            </div>
											        </div>
                                        <%          perfilLogado = perfilUserLogados.stream().filter( x -> "configPerfil".equals(x.getDesc_pagina() ) ).findFirst(); %>
					                                <!-- Menu "Configuração Perfil" config. Perfil -->	
											        <div class="col-md-3" style="max-width: 15rem;">
											           <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											              <div class="card-block">
					                                           <div class="text-c-purple text-right">
                                        <%                     if( perfilLogado.isPresent() ) {  %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
                                        <%                     } else { %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
                                        <%                     }        %>                             
		                                                       </div>
                                        <%                     if( perfilLogado.isPresent() ) {  %>
					                                               <a href="<%= request.getContextPath() %>/perfil/configPerfil.jsp" class="waves-effect waves-dark">
                                        <%                     } else { %>
				                                                   <a href="#" style="pointer-events: none;" > 
                                        <%                     }        %>                             
											                     <h6><i class="fa fa-user-secret fa-3x text-primary"></i></h6>
											                     <h6 class="card-title text-dark">Perfil</h6>
											                   </a>
											              </div>
											           </div>
											        </div>
                                        <%          perfilLogado = perfilUserLogados.stream().filter( x -> "configSecao".equals(x.getDesc_pagina() ) ).findFirst(); %>
					                                <!-- Menu "Configuração Perfil" config. Perfil -->	
											        <div class="col-md-3" style="max-width: 15rem;">
											           <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											              <div class="card-block">
					                                           <div class="text-c-purple text-right">
                                        <%                     if( perfilLogado.isPresent() ) {  %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
                                        <%                     } else { %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
                                        <%                     }        %>                             
		                                                       </div>
                                        <%                     if( perfilLogado.isPresent() ) {  %>
					                                               <a href="<%= request.getContextPath() %>/ServeletSecao?acao=MontaSecaoInicial" class="waves-effect waves-dark">
                                        <%                     } else { %>
				                                                   <a href="#" style="pointer-events: none;" > 
                                        <%                     }        %>                             
											                     <h6><i class="fa fa-file-o fa-3x text-primary"></i></h6>
											                     <h6 class="card-title text-dark">Seção</h6>
											                   </a>
											              </div>
											           </div>
											        </div>
                                        <%          perfilLogado = perfilUserLogados.stream().filter( x -> "configPaginaSecao".equals(x.getDesc_pagina() ) ).findFirst(); %>
					                                <!-- Menu "Configuração Perfil" config. Pagina Secao -->									        
											        <div class="col-md-3" style="max-width: 15rem;">
											           <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											              <div class="card-block">
					                                           <div class="text-c-purple text-right">
                                        <%                     if( perfilLogado.isPresent() ) {  %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
                                        <%                     } else { %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
                                        <%                     }        %>                             
		                                                       </div>
                                        <%                     if( perfilLogado.isPresent() ) {  %>
					                                               <a href="<%= request.getContextPath() %>/ServletsPaginaPerfil?acao=MontaPaginaSecaoInicial" class="waves-effect waves-dark">
                                        <%                     } else { %>
				                                                   <a href="#" style="pointer-events: none;" > 
                                        <%                     }        %>                             
											                     <h6><i class="fa fa-files-o fa-3x text-primary"></i></h6>
											                     <h6 class="card-title text-dark">Página da Seção</h6>
											                   </a> 
											              </div>
											           </div>
											        </div>
								        <%      }else{ %>  
										           <div class="col-md-3" style="max-width: 15rem;">
										               <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
										                  <div class="card-block">
											                  <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                          </div>
					                                          <a href="#" style="pointer-events: none;" >
										                         <h6><i class="fa fa-user-circle-o fa-3x text-primary"></i></h6>
										                         <h6 class="card-title text-dark">Add User Perfil</h6>
											                  </a> 
										                  </div>
										               </div>
										           </div>
										           <div class="col-md-3" style="max-width: 15rem;">
										               <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
										                  <div class="card-block">
											                  <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                          </div>
					                                          <a href="#" style="pointer-events: none;" >
										                         <h6><i class="fa fa-user-secret fa-3x text-primary"></i></h6>
										                         <h6 class="card-title text-dark">Perfil</h6>
											                  </a> 
										                  </div>
										               </div>
										           </div>
										           <div class="col-md-3" style="max-width: 15rem;">
										               <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
										                  <div class="card-block">
											                  <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                          </div>
					                                          <a href="#" style="pointer-events: none;" >
										                         <h6><i class="fa fa-file-o fa-3x text-primary"></i></h6>
										                         <h6 class="card-title text-dark">Seção</h6>
											                  </a> 
										                  </div>
										               </div>
										           </div>
										           <div class="col-md-3" style="max-width: 15rem;">
										               <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
										                  <div class="card-block">
											                  <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                          </div>
					                                          <a href="#" style="pointer-events: none;" >
	 									                         <h6><i class="fa fa-files-o fa-3x text-primary"></i></h6>
										                         <h6 class="card-title text-dark">Página da Seção</h6>
											                  </a> 
										                  </div>
										               </div>
										           </div>
	                                    <%      }   
                                            }
                                        %>
								    </div>
 
								    <!-- ########################### -->
									<!--        Consolidações        -->
									<!-- ########################### -->
									<br><br>
								    <p class="h5"><strong>Consolidações</strong></p>
								    <div class="row my-12 text-primary">
                                         <% if( perfilUserLogados != null ) {
                                                Optional<ModelPerfilLogado> perfilLogado = perfilUserLogados.stream().filter( x -> "Consolidações".equals(x.getNome_secao()) ).findFirst();
                                                if( perfilLogado.isPresent() ) { 
                                                    perfilLogado = perfilUserLogados.stream().filter( x -> "afaturar".equals(x.getDesc_pagina() ) ).findFirst();
                                         %>	        <!-- Menu "Configuração Perfil" config. Perfil User -->	
											        <div class="col-md-6" style="max-width: 15rem;">
											            <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											                <div class="card-block">
					                                           <div class="text-c-purple text-right">
                                        <%                     if( perfilLogado.isPresent() ) {  %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
                                        <%                     } else { %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
                                        <%                     }        %>                             
		                                                       </div>
                                        <%                     if( perfilLogado.isPresent() ) {  %>
					                                               <a href="<%= request.getContextPath() %>/consolidacao/afaturar.jsp" class="waves-effect waves-dark">
                                        <%                     } else { %>
				                                                   <a href="#" style="pointer-events: none;" > 
                                        <%                     }        %>                             
											                      <h6><i class="fa fa-money fa-3x text-primary"></i></h6>
											                      <h6 class="card-title text-dark">A Faturar</h6>
											                    </a>
											                </div>
											            </div>
											        </div>
                                         <%     
                                                    perfilLogado = perfilUserLogados.stream().filter( x -> "vlrFaturado-vlrAFatura".equals(x.getDesc_pagina() ) ).findFirst();
                                         %>	        <!-- Menu "Configuração Perfil" config. Perfil User -->	
											        <div class="col-md-6" style="max-width: 15rem;">
											            <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											                <div class="card-block">
					                                           <div class="text-c-purple text-right">
                                        <%                     if( perfilLogado.isPresent() ) {  %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
                                        <%                     } else { %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
                                        <%                     }        %>                             
		                                                       </div>
                                        <%                     if( perfilLogado.isPresent() ) {  %>
					                                               <a href="<%= request.getContextPath() %>/consolidacao/vlrFaturado-vlrAFatura.jsp" class="waves-effect waves-dark">
                                        <%                     } else { %>
				                                                   <a href="#" style="pointer-events: none;" > 
                                        <%                     }        %>                             
											                      <h6><i class="fa fa-calculator fa-3x text-primary"></i></h6>
											                      <h6 class="card-title text-dark">Faturado x A faturar</h6>
											                    </a>
											                </div>
											            </div>
											        </div>
											        
											        
											        
								        <%      } else{ %>
										           <div class="col-md-6" style="max-width: 15rem;">
										               <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
										                  <div class="card-block">
											                  <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                          </div>
					                                          <a href="#" style="pointer-events: none;" >
									                             <h6><i class="fa fa-money fa-3x text-primary"></i></h6>
									                             <h6 class="card-title text-dark">A Faturar</h6>
											                  </a> 
										                  </div>
										               </div>
										           </div>


											       <div class="col-md-6" style="max-width: 15rem;">
											           <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											               <div class="card-block">
					                                          <div class="text-c-purple text-right">
				                                                  <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
		                                                      </div>
					                                              <a href="<%= request.getContextPath() %>/consolidacao/vlrFaturado-vlrAFatura.jsp" class="waves-effect waves-dark">
											                      <h6><i class="fa fa-calculator fa-3x text-primary"></i></h6>
											                      <h6 class="card-title text-dark">Faturado x A faturar</h6>
											                    </a>
											               </div>
											           </div>
											       </div>



	                                    <%      }  
                                            }%>
								    </div>

								    <!-- ########################### -->
									<!--     Config. Ambiente        -->
									<!-- ########################### -->
									<br><br>
								    <p class="h5"><strong>Config. Ambiente</strong></p>
								    <div class="row my-12 text-primary">
                                         <% if( perfilUserLogados != null ) {
                                                Optional<ModelPerfilLogado> perfilLogado = perfilUserLogados.stream().filter( x -> "Config. Ambiente".equals(x.getNome_secao()) ).findFirst();
                                                if( perfilLogado.isPresent() ) { 
                                         %>	        <!-- Menu "Configuração Perfil" config. Perfil User -->	
											        <div class="col-md-12" style="max-width: 15rem;">
											            <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											                <div class="card-block">
					                                           <div class="text-c-purple text-right">
                                        <%                     if( perfilLogado.isPresent() ) {  %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>
                                        <%                     } else { %>
				                                                   <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
                                        <%                     }        %>                             
		                                                       </div>
                                        <%                     if( perfilLogado.isPresent() ) {  %>
					                                               <a href="<%= request.getContextPath() %>/principal/principalNaoAdmin.jsp" class="waves-effect waves-dark">
                                        <%                     } else { %>
				                                                   <a href="#" style="pointer-events: none;" > 
                                        <%                     }        %>                             
											                      <h6><i class="fa fa-laptop fa-3x text-primary"></i></h6>
											                      <h6 class="card-title text-dark">Ambiente</h6>
											                    </a>											                
											                </div>
											            </div>
											        </div>
								        <%      }else{ %>
										           <div class="col-md-12" style="max-width: 15rem;">
										               <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
										                  <div class="card-block">
											                  <div class="text-c-purple text-right" data-toggle="tooltip" data-placement="top" title="Você não tem acesso a esta área!">
					                                            <i class="fa fa-lock f-1" style="font-size: 22px; color: black;"></i>
					                                          </div>
					                                          <a href="#" style="pointer-events: none;" >
									                             <h6><i class="fa fa-laptop fa-3x text-primary"></i></h6>
									                             <h6 class="card-title text-dark">Ambiente</h6>
											                  </a> 
										                  </div>
										               </div>
										           </div>
	                                    <%      }  
                                            } %>
								
								    </div>



								    <!-- ########################### -->
									<!--     Config. Ambiente        -->
									<!-- ########################### -->
									<br><br>
								    <p class="h5"><strong>Relatórios</strong></p>
								    <div class="row my-12 text-primary">
 											        <div class="col-md-12" style="max-width: 15rem;">
											            <div class="card text-center h-10 wrimagecard wrimagecard-topimage" style="max-width: 15rem;">
											                <div class="card-block">
					                                           <div class="text-c-purple text-right">
				                                                <i class="fa fa-lock f-1" style="font-size: 22px; color: white;"></i>

		                                                       </div>
					                                               <a href="<%= request.getContextPath() %>/relatorios/relClientesPorPeiodoEntrada.jsp" class="waves-effect waves-dark">
											                      <h6><i class="fa fa-pencil-square-o fa-3x text-primary"></i></h6>
											                      <h6 class="card-title text-dark">Rel. Clientes Entramtes</h6>
											                    </a>											                
											                </div>
											            </div>
											        </div>
								                    
								                    
                                    </div>
                                    

                                </div>
                            </div>
                        </div>
                    </div>
            </div>
      </div>
    </div>
</div>    
<jsp:include page="javascriptfile.jsp"></jsp:include>


</body>

</html>
