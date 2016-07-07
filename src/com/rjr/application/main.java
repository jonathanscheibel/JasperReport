package com.rjr.application;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
//import java.util.HashMap;
import java.util.HashMap;

import com.wals.jdbc.ConnectionFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionFactory fabrica = new ConnectionFactory();
		Connection con = fabrica.getInstance();
//		HashMap<String, Object> param = new HashMap<String, Object>();
		
		try {
//			JasperCompileManager.compileReportToFile("src/com/rjr/report/Blank_A4.jrxml");
			
		
//			JasperPrint jp = JasperFillManager.fillReport("src/com/rjr/report/Blank_A4.jasper", null, con);
//			JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\Ricardo\\workspace\\jasperReport\\src\\com\\rjr\\report\\Blank_A4.jasper", null, con);
//			JasperViewer jw = new JasperViewer(jp);
//			jw.setVisible(true);
			
            FileInputStream fis = new FileInputStream("C:\\Users\\Ricardo\\workspace\\jasperReport\\src\\com\\rjr\\report\\Blank_A4.jasper");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);

            //compile report
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
 
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

}
