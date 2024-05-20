package br.com.portal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.portal.connection.SingleConnectionBanco;
public class DAOError {

	private Connection connection;
	public DAOError() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public void insertError( String paginaDao, String paginaJsp, String modulo, String linha, String user, String error ) {
		
		String sql = "INSERT INTO ERROR ( DATA, PAGINA_DAO, PAGINA_JSP, MODULO, LINHA_PG, USER_PORTAL, ERROR ) "
				   + "           VALUES ( ?, ?, ?, ?, ?, ?, ? )                                                ";

		
		PreparedStatement prepareSql;
		Timestamp dtCad = new java.sql.Timestamp(new java.util.Date().getTime());
		String dataCadRecurso =  dtCad.toString().substring(0, 19);

		try {
			prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString   ( 1 , dataCadRecurso ); 			
			prepareSql.setString   ( 2 , paginaDao      ); 			
			prepareSql.setString   ( 3 , paginaJsp      ); 			
			prepareSql.setString   ( 4 , modulo         ); 			
			prepareSql.setString   ( 5 , linha          ); 			
			prepareSql.setString   ( 6 , user           ); 			
			prepareSql.setString   ( 7 , error          ); 			
            
			prepareSql.execute();
			connection.commit();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erro: " + e.getMessage());
			
		}        
		
	}
}
