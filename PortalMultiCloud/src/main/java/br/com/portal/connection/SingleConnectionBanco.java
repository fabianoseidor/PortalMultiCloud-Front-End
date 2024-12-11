package br.com.portal.connection;

//Classes necessárias para uso de Banco de dados //
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SingleConnectionBanco {
	
	public static String status = "Não conectou...";

//	private static String url      = "jdbc:sqlserver://PISEIDORSQL:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=PORTALMULTICLOUD_PRD;";         
    private static String url      = "jdbc:sqlserver://PISEIDORSQL:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=PORTALMULTICLOUD_DEV;";         
//	private static String url        = "jdbc:sqlserver://10.154.20.130:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=PORTALMULTICLOUD_DEV;";         

//	private static String urlGM    = "jdbc:sqlserver://PISEIDORSQL:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=LOGINUNIFICADO_PRD;";         
    private static String urlGM    = "jdbc:sqlserver://PISEIDORSQL:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=LOGINUNIFICADO_DEV;";         
    
    
    private static String password       = "59~49h\"0FtgnNbi1";
	private static String user           = "portalmulticloud";
//	private static String password       = "54wnHsiu1W";
//	private static String user           = "sa";
	
	private static Connection connection      = null;
	private static Connection connectionGMUD  = null;
	private static Connection connectionMySQL = null; //atributo do tipo connectionMySQL
	
	static {
		conectar();
		conectarGMUD();
		getConexaoMySQL();
	}
	public SingleConnectionBanco(){
		conectar();
		conectarGMUD();
		getConexaoMySQL();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			   connection = DriverManager.getConnection(url,user, password  );
			   connection.setAutoCommit(false);
//			   System.out.println("Conectou com sucesso!");
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	
	private static void conectarGMUD() {
		try {
			if(connectionGMUD == null) {
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			   connectionGMUD = DriverManager.getConnection(urlGM, user, password  );
			   connectionGMUD.setAutoCommit(false);
//			   System.out.println("Conectou com sucesso!");
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}

	public static Connection getConnectionGMUD() {
		return connectionGMUD;
	}
	
	public void DeconectionGMUD() {
		try {
			connectionGMUD.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public void Deconection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static java.sql.Connection getConexaoMySQL() {
		  
	   try {
	
	      // Carregando o JDBC Driver padrão
	      String driverName = "com.mysql.cj.jdbc.Driver";
	      Class.forName(driverName);
	
	      // Configurando a nossa conexão com um banco de dados//
	      String serverName = "10.154.15.131";//caminho do servidor do BD
	      String mydatabase = "prd";          //nome do seu banco de dados
	      String url        = "jdbc:mysql://" + serverName + "/" + mydatabase;
	      String username   = "multicloud";  //nome de um usuário de seu BD
	      String password   = "60_ulMPMqS";  //sua senha de acesso
	      connectionMySQL   = DriverManager.getConnection(url, username, password);
	
	      //Testa sua conexão//
	      if (connectionMySQL != null) {
	          status = ("STATUS--->Conectado com sucesso!");
	      } else {
	          status = ("STATUS--->Não foi possivel realizar conexão");
	      }
	
	      return connectionMySQL;
	   } catch (ClassNotFoundException e) {  //Driver não encontrado
	        System.out.println("O driver expecificado nao foi encontrado.");
	        return null;
	   } catch (SQLException e) {
	        //Não conseguindo se conectar ao banco
	        System.out.println("Nao foi possivel conectar ao Banco de Dados.");
	        return null;
	   }
	
	}

	//Método que retorna o status da sua conexão//
	public static String statusConection() {
	   return status;
	}
	
	//Método que fecha sua conexão//
	public static boolean FecharConexao() {
	
	   try {
		   SingleConnectionBanco.getConexaoMySQL().close();
	      return true;
	   } catch (SQLException e) {
	      return false;
	   }
	}
	
	//Método que reinicia sua conexão//
	public static java.sql.Connection ReiniciarConexao() {
	   FecharConexao();
	   return SingleConnectionBanco.getConexaoMySQL();
	}
	
}
