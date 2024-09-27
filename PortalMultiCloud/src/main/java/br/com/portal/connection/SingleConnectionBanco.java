package br.com.portal.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnectionBanco {

//	private static String url            = "jdbc:sqlserver://PISEIDORSQL:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=PORTALMULTICLOUD_PRD;";         
    private static String url            = "jdbc:sqlserver://PISEIDORSQL:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=PORTALMULTICLOUD_DEV;";         
//	private static String url            = "jdbc:sqlserver://10.154.20.130:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=PORTALMULTICLOUD_DEV;";         

//	private static String urlGM          = "jdbc:sqlserver://PISEIDORSQL:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=LOGINUNIFICADO_PRD;";         
    private static String urlGM          = "jdbc:sqlserver://PISEIDORSQL:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=LOGINUNIFICADO_DEV;";         
    
    
    private static String password       = "59~49h\"0FtgnNbi1";
	private static String user           = "portalmulticloud";
//	private static String password       = "54wnHsiu1W";
//	private static String user           = "sa";
	
	private static Connection connection = null;
	private static Connection connectionGMUD = null;
	
	static {
		conectar();
		conectarGMUD();
	}
	public SingleConnectionBanco(){
		conectar();
		conectarGMUD();
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
}
