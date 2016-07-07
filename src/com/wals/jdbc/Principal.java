package com.wals.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {
	
	
	public static void exemploStatement(Connection con) {
		
		String sql = "select int_user, nm_user from usuario";
		Statement s = null;
		ResultSet rs = null;
		try {
			s = con.createStatement();			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			rs = s.executeQuery(sql);
			
			System.out.println("Lista de usu·rios com statement");
			System.out.println("---------------------------------------------");
	        while (rs.next ()) {
	        	System.out.println (rs.getString ("INT_USER") + " - " + rs.getString ("NM_USER"));
	          }			
	        System.out.println("---------------------------------------------");
	        
		} catch (java.sql.SQLException e) {
			System.out.println("Unable to submit a static SQL query.");
			// We can't go much further without a result set, return...
			return;
		}
		
	}
	
	public static void exemploPreparedStatement(Connection con) {
		String sql2 = "select * from usuario where int_user = ?";
		PreparedStatement ps = null;		
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql2);
			ps.setString(1, "001");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			rs = ps.executeQuery();
			
			System.out.println("Lista de usu·rios com preparedstatement");
			System.out.println("---------------------------------------------");
	        while (rs.next ()) {
	        	System.out.println (rs.getString ("INT_USER") + " - " + rs.getString ("NM_USER"));
	          }			
	        System.out.println("---------------------------------------------");
	        
		} catch (java.sql.SQLException e) {
			System.out.println("Unable to submit a static SQL query.");
			// We can't go much further without a result set, return...
			return;
		}				
	}
	
	public static ResultSet exemploDinamico(Connection con, String sql) {
		Statement s = null;		
		try {
			s = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			return s.executeQuery(sql);			
		} catch (java.sql.SQLException e) {
			System.out.println("Unable to submit a static SQL query.");
			// We can't go much further without a result set, return...
			return null;
		}				
	}

	public static void main(String[] args) {
		System.out.println("Fabricando conex√£o");
		ConnectionFactory fabrica = new ConnectionFactory();
		Connection con = fabrica.getInstance();


		System.out.println("Exemplo de Satement");
		System.out.println("");
		exemploStatement(con);
		
		
		System.out.println("Exemplo de PreparedSatement");
		System.out.println("");
		exemploPreparedStatement(con);
		
		
		
		ResultSet rs = exemploDinamico(con, "select * from usuario");
		System.out.println("Lista de usu·rio com preparedstatement");
		System.out.println("---------------------------------------------");
        try {
			while (rs.next ()) {
				System.out.println (rs.getString ("INT_USER") + " - " + rs.getString ("NM_USER"));
			  }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
        System.out.println("---------------------------------------------");		
		
		
		System.out.println("Fechando conex√£o");
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
