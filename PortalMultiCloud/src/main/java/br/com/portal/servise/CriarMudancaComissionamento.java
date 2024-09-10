package br.com.portal.servise;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import br.com.portal.model.CategoriaPadraoGMUD;
import br.com.portal.model.CriticidadeGMUD;
import br.com.portal.model.DadosMudancaGMUD;
import br.com.portal.model.InformacaoDadostMudanca;
import br.com.portal.model.ImpactoMudancaGMUD;
import br.com.portal.model.ItemCategoriaPadrao;
import br.com.portal.model.ListClientesAfetados;
import br.com.portal.model.ResponsavelAtividadeGMUD;
import br.com.portal.model.StatusAtividadeGmud;
import br.com.portal.model.TipoMudancaGMUD;
import br.com.portal.model.AcaoPosAtividadeGMUD;


public class CriarMudancaComissionamento {

	private String urlBase;
	private String idCliente;
	private String idContrato;
	private String loginUser;
	private String email_solicitante;
	private CategoriaPadraoGMUD categoriaPadrao = new CategoriaPadraoGMUD();
	
	public CriarMudancaComissionamento( String urlBase, String idCliente, String idContrato, String loginUser, String email_solicitante ) {
		this.urlBase           = urlBase;
		this.idCliente         = idCliente;
		this.idContrato        = idContrato;
		this.loginUser         = loginUser;
		this.email_solicitante = email_solicitante;
	}

    public String sendPOST(String url) throws Exception {

        String result = "";
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        String json = getDadosApi( );
        // send a JSON data
        post.setEntity(new StringEntity(json,  "UTF-8"));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            result = EntityUtils.toString(response.getEntity());
        }

        return result;
    }

	private String getDadosApi( ) throws Exception {
		InformacaoDadostMudanca infDadostMudanca = new InformacaoDadostMudanca();
		
		var DtInicio = LocalDate.now();
		var dtFinal = LocalDate.now();
		var formatador = DateTimeFormatter.ofPattern("HH:mm:ss");
		var horaAtual = LocalTime.now();

		List<ItemCategoriaPadrao> atividadesMudanca        = getListaItensCategoriaPadrao( getItemCategoriaPadrao( this.urlBase ), this.urlBase, DtInicio.toString() );
		List<ListClientesAfetados> mudancaClientesAfetados = getLitaClienteAfetado();
		CriticidadeGMUD criticidade = new CriticidadeGMUD();
		criticidade.setId_criticidade("2"); criticidade.setCriticidade("MEDIA");
		ImpactoMudancaGMUD impactoMudanca = new ImpactoMudancaGMUD();
		impactoMudanca.setId_impacto_mudanca("2"); impactoMudanca.setImpacto_mudanca("MEDIA");
		TipoMudancaGMUD tipoMudanca = new TipoMudancaGMUD();
		tipoMudanca.setId_tipo_mudanca("3"); tipoMudanca.setTipo_mudanca("PADRÃO");
		CategoriaPadraoGMUD categoriaPadrao = new CategoriaPadraoGMUD();
		
		categoriaPadrao.setId_categoria_padrao("11");
		
		List<String> arquivosMudanca = new ArrayList<String>();
		DadosMudancaGMUD dadosMudanca = new DadosMudancaGMUD();
		AcaoPosAtividadeGMUD  acaoPosAtividade = new AcaoPosAtividadeGMUD();
		acaoPosAtividade.setPlano_rollback(""); acaoPosAtividade.setPlano_teste("");

		var descricaoMudanca     = "GMUD criada automaticamente para a criação de novo Ambiente.";
		var justificativaMudanca = "GMUD criada devido ao Comissionamento do novo cliente, " + mudancaClientesAfetados.get(0).getNome_cliente() + ", referente ao Contrato ID( " + this.idContrato + " ) no Portal de Gestão de Clientes.";
		String tituloMudanca     = "GMUD DE COMISSIONAMENTO PARA O CLIENTE '" + mudancaClientesAfetados.get(0).getNome_cliente() + "' !";
		
		infDadostMudanca.setTitulo_mudanca(tituloMudanca);
		infDadostMudanca.setLogin_user(this.loginUser);
		infDadostMudanca.setEmail_solicitante(this.email_solicitante);

		dadosMudanca.setDt_inicio            ( DtInicio.toString()             );
		dadosMudanca.setHr_inicio            ( formatador.format(horaAtual)    );
		dadosMudanca.setDt_final             ( dtFinal.plusDays(5).toString() );
		dadosMudanca.setHr_final             ( formatador.format(horaAtual)    );
		dadosMudanca.setDsc_mudanca          ( descricaoMudanca                );
		dadosMudanca.setJustificativa_mudanca( justificativaMudanca            );
		
		infDadostMudanca.setAtividadesMudanca      ( atividadesMudanca       );
		infDadostMudanca.setMudancaClientesAfetados( mudancaClientesAfetados );
		infDadostMudanca.setCriticidade            ( criticidade             );
		infDadostMudanca.setImpactoMudanca         ( impactoMudanca          );
		infDadostMudanca.setTipoMudanca            ( tipoMudanca             );
		infDadostMudanca.setCategoriaPadrao        ( this.categoriaPadrao    );
		infDadostMudanca.setArquivosMudanca        ( arquivosMudanca         );
		infDadostMudanca.setDadosMudanca           ( dadosMudanca            );
		infDadostMudanca.setAcaoPosAtividade       ( acaoPosAtividade        );

		Gson gson = new Gson();
		
		return gson.toJson(infDadostMudanca);
	}
	
		
	private List<ItemCategoriaPadrao> getListaItensCategoriaPadrao( String listaItemCatPadrao, String urlBase, String dtTarefa ) throws Exception {
		List<ItemCategoriaPadrao> atividadesMudanca  = new ArrayList<ItemCategoriaPadrao>();
		JSONArray  jsArrayItemCatPadrao = new JSONArray ( listaItemCatPadrao  );

		JSONObject categoriaPadrao = jsArrayItemCatPadrao.getJSONObject(0).getJSONObject("categoriaPadrao");
		int idCategoriaPadrao = categoriaPadrao.getInt("id_categoria_padrao");
		
		this.categoriaPadrao.setId_categoria_padrao( Integer.toString(idCategoriaPadrao) );
		this.categoriaPadrao.setCategoria_padrao   ( categoriaPadrao.getString("categoria_padrao"   ) );

		for( int i = 0; i < jsArrayItemCatPadrao.length(); ++i) {
			 JSONObject objItemCatPadrao = jsArrayItemCatPadrao.getJSONObject(i);
			 ItemCategoriaPadrao itemCatPadrao = salvarItemCatPadrao( objItemCatPadrao, urlBase, dtTarefa ) ;
			 if( itemCatPadrao != null ) atividadesMudanca.add(itemCatPadrao);			 
		}		
		return atividadesMudanca;
	}

	private List<ListClientesAfetados> getLitaClienteAfetado() {
		List<ListClientesAfetados> mudancaClientesAfetados = new ArrayList<ListClientesAfetados>();
		try {
			String listaCliente = getClientesAfetados( this.urlBase, this.idCliente );
			JSONObject  jsClientesAfetados = new JSONObject ( listaCliente );
			ListClientesAfetados listClientesAfetados = new ListClientesAfetados();
			
			int idClientesAf = jsClientesAfetados.getInt( "id_clientes_af" );
			
			listClientesAfetados.setId_clientes_af( Integer.toString       ( idClientesAf      ) );
			listClientesAfetados.setNome_cliente  ( jsClientesAfetados.getString( "nome_cliente"   ) );
			mudancaClientesAfetados.add(listClientesAfetados);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mudancaClientesAfetados;
	}
	
	
	/******************************************************************/
	/*                                                                */
	/*                                                                */
	/******************************************************************/
	private String getClientesAfetados( String urlBase, String idCliebte )  throws Exception {
	    String urlAPI  = urlBase + "obterByIdClientesAfetadosPortal/" + idCliebte;
	    
	    try {
			URL url =  URI.create( urlAPI ).toURL();
			HttpURLConnection urlConnection = null;
			try {
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readStream(in);			
			
            } finally {
                urlConnection.disconnect();
            }
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
	    

	    return "ERROR";
	}
	
	private String getItemCategoriaPadrao( String urlBase )  throws Exception {
	    String urlAPI  = urlBase + "listaItemCategoriaPadrao/11";
	    
	    try {
			URL url =  URI.create( urlAPI ).toURL();
			HttpURLConnection urlConnection = null;
			try {
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readStream(in);			
			
            } finally {
                urlConnection.disconnect();
            }
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		return "ERROR";
	}

	private String getResponsavelTarefa( String urlBase, String idCliente )  throws Exception {
	    String urlAPI  = urlBase + "obterResponsavelAtividadePorId/" + idCliente;
	    
	    try {
			URL url =  URI.create( urlAPI ).toURL();
			HttpURLConnection urlConnection = null;
			try {
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                return readStream(in);			
			
            } finally {
                urlConnection.disconnect();
            }
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		
		return "ERROR";
	}
	

	private String getMinutoToHoraMinutos( Long valor ) {
		long minutos = valor % 60;
		long horas = (valor - minutos) / 60;
		
		String horaMinutos =  String.format("%02d", horas) + ":" + String.format("%02d", minutos);
		
		return horaMinutos;
	}
	
	private ItemCategoriaPadrao salvarItemCatPadrao( JSONObject itemCatPadrao, String urlBase, String dtTarefa ) throws Exception {
		ItemCategoriaPadrao itemCategoriaPadrao = new ItemCategoriaPadrao();

		int prioridade      = itemCatPadrao.getInt( "prioridade");
		Long duracao         = itemCatPadrao.getLong( "duracao");
		String strDuracao   = getMinutoToHoraMinutos( duracao );
		strDuracao   = getMinutoToHoraMinutos( 90L );
		
		
		itemCategoriaPadrao.setTitulo_atividade_mudanca( itemCatPadrao.getString( "tituloCatPadrao" ) );
		itemCategoriaPadrao.setAtividade_mudanca       ( itemCatPadrao.getString( "descCatPadrao"   ) );
		itemCategoriaPadrao.setDt_tarefa               ( dtTarefa                                     );
		itemCategoriaPadrao.setDuracao                 ( strDuracao                                   );
		itemCategoriaPadrao.setPrioridade              ( Integer.toString       ( prioridade        ) );
		itemCategoriaPadrao.setEnviar_email            ( false                                          );

		String r_Resp_Trarefa = getResponsavelTarefa( urlBase, "7" );
		
		JSONObject jsRespTrarefa  = new JSONObject( r_Resp_Trarefa );
		
		int idResponsavelTarefa = jsRespTrarefa.getInt( "id_responsavel_atividade");
		
		ResponsavelAtividadeGMUD responsavelAtividade = new ResponsavelAtividadeGMUD();
		
		responsavelAtividade.setId_responsavel_atividade(Integer.toString             ( idResponsavelTarefa           ));
		responsavelAtividade.setResponsavel_atividade(jsRespTrarefa.getString    ( "responsavel_atividade"       ));
		responsavelAtividade.setEmailResponsavelAtividade(jsRespTrarefa.getString( "email_responsavel_atividade" ));
		
		itemCategoriaPadrao.setResponsavelAtividade(responsavelAtividade);
		
		StatusAtividadeGmud statusAtividade = new StatusAtividadeGmud();
		statusAtividade.setId_status_atividade("2");
		
		itemCategoriaPadrao.setStatusAtividade(statusAtividade);
		
		return itemCategoriaPadrao;
	}

    private static String readStream(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
//        System.out.println(sb.toString());
        br.close();
        return sb.toString();
    }    


}
