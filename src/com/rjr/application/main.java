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

	public static void printReport(Connection conn){
		try {	
//			HashMap param = new HashMap();
//			JasperPrint jp = JasperFillManager.fillReport("src/com/rjr/report/ReportUser.jasper", param, con);
//			JasperViewer jw = new JasperViewer(jp);
//			jw.setVisible(true);			          			
			
			FileInputStream fis = new FileInputStream("C:\\Users\\Ricardo\\workspace\\jasperReport\\src\\com\\rjr\\report\\ReportUser.jasper");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            
            //compile report
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
 
            // view report to UI
            JasperViewer.viewReport(jasperPrint, false);

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void printReportParam(Connection conn, @SuppressWarnings("rawtypes") HashMap param){
		try {				          						
			FileInputStream fis = new FileInputStream("C:\\Users\\Ricardo\\workspace\\jasperReport\\src\\com\\rjr\\report\\ReportUserParam.jasper");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
            
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            @SuppressWarnings("unchecked")
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionFactory fabrica = new ConnectionFactory();
		Connection con = fabrica.getInstance();
		
		//Report sem parametros
//		printReport(con);
		
		//Criando parametros
		HashMap param = new HashMap();
        param.put("grupo", 1);
		
        //Report com paramentros
        printReportParam(con, param);
        
		
		System.out.println("Fechando conexao");		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
