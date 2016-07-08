package com.rjr.application;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import com.wals.jdbc.ConnectionFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class main {
	
	@SuppressWarnings({"rawtypes", "unchecked"}) 
	public static void printReportParam2(String fileName, Connection conn, HashMap param){
		try {				          						
			FileInputStream fis = new FileInputStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn);
            JasperViewer.viewReport(jasperPrint, false);

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static HashMap<String, Class> readReportParameters(String fileName){
		HashMap<String, Class> mapa = new HashMap<>();
		
		//logica
		
		return mapa;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionFactory fabrica = new ConnectionFactory();
		Connection con = fabrica.getInstance();
		
		//Report sem parametros
		printReportParam2("src/com/rjr/report/ReportUser.jasper", con, null);
		
		//Criando parametros
		HashMap<String, Object> param = new HashMap<>();
        param.put("grupo", 2);
		
        //Report com paramentros
		printReportParam2("src/com/rjr/report/ReportUserParam.jasper", con, param);
		
		System.out.println("Fechando conexao");		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
