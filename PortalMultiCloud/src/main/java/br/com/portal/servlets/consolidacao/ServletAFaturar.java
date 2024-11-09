package br.com.portal.servlets.consolidacao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import br.com.portal.dao.DAOUtil;
import br.com.portal.dao.consolidacao.DAOAFaturar;
import br.com.portal.model.InformacoesDW;
import br.com.portal.model.consolidacao.ModelAFaturar;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@MultipartConfig
public class ServletAFaturar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String realPathLog = "";

	public ServletAFaturar() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
	   		String acao = request.getParameter("acao");
			
	   		if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("montaTelaTAbelaDW") ) {
	   			DAOAFaturar daoAFaturar = new DAOAFaturar();
	   			List<InformacoesDW> listInformacoesDW = daoAFaturar.getInfoDW();
				Gson gson = new Gson();
				String lista = gson.toJson(listInformacoesDW);
			    response.getWriter().write(lista);
				
		     }else{
				  request.getRequestDispatcher("consolidacao/afaturar.jsp").forward(request, response);
		    }		
	   	}catch(Exception e){
				e.printStackTrace();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				requestDispatcher.forward(request, response);			
	    }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOAFaturar daoAFaturar = new DAOAFaturar();
		try {
		   Integer radioSelecionado = ( request.getParameter( "radioSelecionado" )  != null && !request.getParameter( "radioSelecionado" ).isEmpty() ? Integer.valueOf( request.getParameter( "radioSelecionado" ) ) : 0 );

		   if( radioSelecionado == 1 ) {
			   List<ModelAFaturar> listaAFaturar = daoAFaturar.porcessaBaseMySql();

	            if(listaAFaturar != null) {
	            	
			    	request.setAttribute("msg", "Processamento Diretamente da View DW" );
			    	int totalLinhas =  listaAFaturar.size();
			    	
			    	if(totalLinhas > 0) {
			    		request.setAttribute("totalLinhas", totalLinhas );
			    		
			    		String vlrFaturaros[] = daoAFaturar.vlrsFaturamento();
			    		for(String vt : vlrFaturaros) {
							String textoSplit [] = vt.split(";");
							if( textoSplit[0].trim().equals("BRL")) {
	 						    request.setAttribute("real", textoSplit[0].trim() );
	 						    request.setAttribute("vlrReal", textoSplit[1].trim() );
							}else if( textoSplit[0].trim().equals("USD")) {
	 						    request.setAttribute("dolar", textoSplit[0].trim() );
	 						    request.setAttribute("vlrdolar", textoSplit[1].trim() );
							}else if( textoSplit[0].trim().equals("EUR")) {
	 						    request.setAttribute("euro", textoSplit[0].trim() );
	 						    request.setAttribute("vlreuro", textoSplit[1].trim() );
							}
							
			    		}
			    		request.setAttribute("vlrTotal", daoAFaturar.getVlrTotal( ) );
			    		request.setAttribute("msg", "Processamento Diretamente da View DW finalizado com sucesso!" );
			    	}
			    }
//			   System.out.println(listaAFaturar);
			   
		   } else if( radioSelecionado == 2 ) {
		   
			   String filePath = getServletContext().getRealPath("/PROCESSOAFATURAR"); 
	           String arqProcessar = null;
	           String nomeArq = "";
	           
	           realPathLog = request.getServletContext().getRealPath("/LOG");
	 
			   if (request.getPart("arqAFaturar") != null) {
				    try {
				    	for(Part part : request.getParts() ) {
				    		makeDir(filePath);
				    		if( part.getName().equals("arqAFaturar") ) { 
				    			arqProcessar = filePath + File.separator + part.getSubmittedFileName();
				    			nomeArq = part.getSubmittedFileName();	
				    			
				    			if(nomeArq != null && !nomeArq.isEmpty()) {
					    			File file = new File(arqProcessar);
					    			if( checkFileExists(file) ) {
					    			    file.delete();
					    			    part.write(filePath + File.separator + part.getSubmittedFileName() );
					    			}else {
					    				part.write(filePath + File.separator + part.getSubmittedFileName() );
					    			}
				    			}else {
				    				request.setAttribute("msg", "Favor Selecionar um Arquivo para processamento!" );
				    			}
				    		}
				    	}
				    	
				      } catch (Exception e) {
				        e.printStackTrace();
				      }		    
			    }
			   
	            if(arqProcessar != null && nomeArq != null && !nomeArq.isEmpty() ) {
	            	
			    	request.setAttribute("msg", "Procensando o arquivo: " + nomeArq );
			    	int totalLinhas =  lerArquivoCargaBase(arqProcessar);
			    	
			    	if(totalLinhas > 0) {
			    		request.setAttribute("totalLinhas", totalLinhas );
			    		
			    		String vlrFaturaros[] = daoAFaturar.vlrsFaturamento();
			    		for(String vt : vlrFaturaros) {
							String textoSplit [] = vt.split(";");
							if( textoSplit[0].trim().equals("BRL")) {
	 						    request.setAttribute("real", textoSplit[0].trim() );
	 						    request.setAttribute("vlrReal", textoSplit[1].trim() );
							}else if( textoSplit[0].trim().equals("USD")) {
	 						    request.setAttribute("dolar", textoSplit[0].trim() );
	 						    request.setAttribute("vlrdolar", textoSplit[1].trim() );
							}else if( textoSplit[0].trim().equals("EUR")) {
	 						    request.setAttribute("euro", textoSplit[0].trim() );
	 						    request.setAttribute("vlreuro", textoSplit[1].trim() );
							}
							
			    		}
			    		request.setAttribute("vlrTotal", daoAFaturar.getVlrTotal( ) );
			    		request.setAttribute("msg", "Arquivo '" + nomeArq + "' procesando com sucesso!" );
			    	}
			    	
			    	File file = new File(arqProcessar);
			    	file.delete();
			    }
			    
			    request.setAttribute("nomeArq", nomeArq );
		    }
		    
		    request.getRequestDispatcher("consolidacao/afaturar.jsp").forward(request, response); 
		    
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("erro.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	
	public void generateLog(String message) throws IOException {

        DAOUtil daoUtil = new DAOUtil();
		
        
		String nomeArqLog = "log_a_faturar_" + daoUtil.getDateLog() + ".LOG";
		
		
		Path path = Paths.get(realPathLog);
		
		if(!Files.exists(path)) {
			
			Files.createDirectory(path);
			
		}
		
		File log = new File(realPathLog + "/" + nomeArqLog);
		
		if(!log.exists()) {
			
			log.createNewFile();
		
		}
		
		FileWriter fw = new FileWriter(log, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(message);
		bw.newLine();

		bw.close();
		fw.close();
		
	
}	
	
	
    public static boolean checkFileExists(File file) {
	    return file.exists() && !file.isDirectory();
	}

    public void makeDir(String secondArg) {
    	
//        File theDir = new File(System.getProperty("user.dir") + File.separator + secondArg);
        File theDir = new File(secondArg);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            } 
            catch(SecurityException se){
                //handle it
            }        
            if(result) {    
                System.out.println("Repository " + theDir.getName() + " was been created");  
            }
        }

 }
	public int lerArquivoCargaBase( String nomeArquivo ) throws IOException {
//		System.out.println("Inicio processanmento do arquivo: " + nomeArquivo );
	    Path path = Paths.get( nomeArquivo );
	    List<ModelAFaturar> listaAFaturar = new ArrayList<ModelAFaturar>();
        int linhaPro = 0;
	    List<String> linhasArquivo = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
	    for (String linha : linhasArquivo) {
	    	ModelAFaturar aFaturar = new ModelAFaturar();
	    	String dados[] = linha.split(";"); // 8 Campos na linha lida para clientes
	    	if( linhaPro == 0 ){ linhaPro++; continue; }
/*
	    	System.out.println("["+ linhaPro +"] - " + linha );

	    	System.out.println( linha );	    	
	    	System.out.println("Tamanho: " + dados.length);	    	
	        System.out.println( "[" + dados[0].trim()  + "]" ); // - 0  PEP 	        
	        System.out.println( "[" + dados[1].trim()  + "]" ); // - 1  Nome Emissor da Ordem 
	        System.out.println( "[" + dados[2].trim()  + "]" ); // - 2  Data de Faturamento 	        
	        System.out.println( "[" + dados[3].trim()  + "]" ); // - 3  Mês	        
	        System.out.println( "[" + dados[4].trim()  + "]" ); // - 4  Ano	        
	        System.out.println( "[" + dados[5].trim()  + "]" ); // - 5  Valor	        
	        System.out.println( "[" + dados[6].trim()  + "]" ); // - 6  Moeda 	        
	        System.out.println( "[" + dados[7].trim()  + "]" ); // - 7  Valor Faturamento   	        
*/
	        
	        // Informacoes provenientes do arquivo.
	    	
	    	aFaturar.setPep           ( dados[0 ].trim() != null && !dados[0 ].trim().isEmpty() ? dados[0 ].trim() : null );   // - 0  razaoSocial 	                        
	    	aFaturar.setNome_emissor  ( dados[1 ].trim() != null && !dados[1 ].trim().isEmpty() ? dados[1 ].trim() : null );   // - 1  pep                                   
	    	aFaturar.setDt_faturamento( dados[2 ].trim() != null && !dados[2 ].trim().isEmpty() ? dados[2 ].trim() : null );   // - 2  alias 	                              
	    	aFaturar.setMes           ( dados[3 ].trim() != null && !dados[3 ].trim().isEmpty() ? dados[3 ].trim() : null );   // - 3  status	                              
	    	aFaturar.setAno           ( dados[4 ].trim() != null && !dados[4 ].trim().isEmpty() ? dados[4 ].trim() : null );   // - 4  comercial	                            
	    	aFaturar.setValor         ( dados[5 ].trim() != null && !dados[5 ].trim().isEmpty() ? dados[5 ].trim() : null );   // - 5  emailCriseSeidor                      
	    	aFaturar.setMoeda         ( dados[6 ].trim() != null && !dados[6 ].trim().isEmpty() ? dados[6 ].trim() : null );   // - 6  obsCriseSeidor 	                      
	    	aFaturar.setVl_faturamento( dados[7 ].trim() != null && !dados[7 ].trim().isEmpty() ? dados[7 ].trim() : null );   // - 7  cnpj   	        

	    
	        
	    	// if(linhaPro%10 == 0) System.out.println("Quantidade de linhas processadas: " + linhaPro );
	    	
	    	listaAFaturar.add(aFaturar);
	    	linhaPro++;
	    }

//	    System.out.println("Quantidade de linhas processadas: " + linhaPro );	    
	    DAOAFaturar daoAFaturar = new DAOAFaturar();
	    try {
			return daoAFaturar.gravarAFaturar(listaAFaturar);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return 0;
	}
	
	public int getQtyLinhaAqrTxt( String Arquivo ) {
		int qtdLinha = 0;
		
		File arquivoLeitura = new File(Arquivo);
		LineNumberReader linhaLeitura;
		try {
			linhaLeitura = new LineNumberReader(new FileReader(arquivoLeitura));
			linhaLeitura.skip(arquivoLeitura.length());
			qtdLinha = linhaLeitura.getLineNumber();		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return qtdLinha;
	}

}
