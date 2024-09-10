package br.com.portal.dao;


import java.util.Date;
import java.text.*;

import java.text.SimpleDateFormat;
import java.time.*;

public class DAOUtil {
	
	public DAOUtil() { }
	

	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date(0);
		return dateFormat.format(date);
	}
	
	
	   /*
     * O usuario entra com o campo do banco de dados do tipo Date
     * e a função retorna o a data formatada 
     */
    
    public String getMostraDataBD(Date data){
        String data_return = null; //Data que ira retornar
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        data_return = sdf.format(data);
        
        return data_return;
    }
    
    /*
     * O sistema pega a data e formata automaticamente para o formato
     * do banco de dados. E retorna a formatação.
     * O método funciona como campo Date.
     */ 
    public Date getDataBDNew() throws ParseException{
		return new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis());	
  	
    	/*
        Date d1 = new Date();  
        Date d2 = new java.sql.Date(d1.getTime());
        return d2;
        */
    }
    
    public Date getDataBDTrunc( String dataBD ) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	if(dataBD != null && !dataBD.isEmpty()) {
	    	Date data;
			try {
				data = (Date) formatter.parse(dataBD);
				return data;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	// System.out.println(data);
    	}
		return null;
    }

    public Date getDataBD( String dataBD ) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(dataBD != null && !dataBD.isEmpty()) {
	    	Date data;
			try {
				data = (Date) formatter.parse(dataBD);
				return data;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	// System.out.println(data);
    	}
		return null;
    }
     
    // stmtInsert.setTimestamp(2,new java.sql.Timestamp(java.util.Calendar.getInstance().getTimeInMillis()));

    public String FormataDataStringTelaDataTime(String dataBD){
    	if(dataBD != null && !dataBD.isEmpty()) {
	        Date dt;
			try {
				if( dataBD != null && !dataBD.isEmpty() ) {
				    dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataBD);
			        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dt);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	return null;
    }

    public String FormataDataStringTelaData(String dataBD){
    	if(dataBD != null && !dataBD.isEmpty()) {
	        Date dt;
			try {
				if( dataBD != null && !dataBD.isEmpty() ) {
				    dt = new SimpleDateFormat("yyyy-MM-dd").parse(dataBD);
			        return new SimpleDateFormat("dd/MM/yyyy").format(dt);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	return null;
    }

    public LocalDate getDtFinal( String tipoTime, LocalDate dataBase, int tempo ) {
//    	LocalDate dataBase = LocalDate.now();
    	
    	if( tipoTime.equals("S") ) { // Verifica se o calculo sera por semana
    		return dataBase.plusWeeks(tempo);
    	}else if( tipoTime.equals("M") ) { // Verifica se o calculo sera por mes
    		return dataBase.plusMonths(tempo);
    	}else if( tipoTime.equals("A") ) { // Verifica se o calculo sera por ano
    		return dataBase.plusYears(tempo);
    	}
    	
    	return null;
    }
    

    public java.sql.Date FormtGravaDataBD( String dataBD ) {
    	if(dataBD != null && !dataBD.isEmpty()) {
	    	try {
	    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	    	java.sql.Date data;
			
				data = new java.sql.Date(format.parse(dataBD).getTime());
				return data;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return null;
    }
  
    public String getDataTimeBD(java.sql.Timestamp data){
        String data_return = null; //Data que ira retornar
        if(data != null ) {
	       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	       data_return = sdf.format(data);
	        return data_return;
        }
        return null;
    }

	public String getDateTimeLog() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
		Date date = new Date(0);
		return dateFormat.format(date);
	}   
	
	public String getDateLog() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date(0);
		return dateFormat.format(date);
	}   

}
