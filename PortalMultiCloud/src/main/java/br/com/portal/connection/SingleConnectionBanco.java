package br.com.portal.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnectionBanco {

	private static String url            = "jdbc:sqlserver://PISEIDORSQL:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=PORTALMULTICLOUD_PRD;";         
//    private static String url            = "jdbc:sqlserver://PISEIDORSQL:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=PORTALMULTICLOUD_DEV;";         
//	private static String url            = "jdbc:sqlserver://10.154.20.130:1433;encrypt=false;instanceName=PORTALMULTICLOUD;database=PORTALMULTICLOUD_DEV;";         
	private static String password       = "59~49h\"0FtgnNbi1";
	private static String user           = "portalmulticloud";
//	private static String password       = "54wnHsiu1W";
//	private static String user           = "sa";
	
	private static Connection connection = null;
	
	static {
		conectar();
	}
	public SingleConnectionBanco(){
		conectar();
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
