package com.wals.jdbc;

import java.sql.Connection;

public class ConnectionFactory {
    private String URL    = "jdbc:firebirdsql:localhost/3050:C:\\Users\\Ricardo\\workspace\\jasperReport\\BANCOTESTE.FDB?sql_dialect=3";
    private String USER   = "sysdba";
    private String PASS   = "masterkey";
    private String DRIVER = "org.firebirdsql.jdbc.FBDriver";
    
    private Connection con;
    
    public Connection getInstance() {
    	if (con == null) {
    		registrarDriver();
    		inicializarConexao();
    	}
    	return con;
    }
    
    private void registrarDriver() {
        try {
            Class.forName (DRIVER);
          }
          catch (java.lang.ClassNotFoundException e) {
            // A call to Class.forName() forces us to consider this exception :-)...
            System.out.println ("Firebird JCA-JDBC driver not found in class path");
            System.out.println (e.getMessage ());
            return;
          }    	
    }
    
    private void inicializarConexao() {
        try {
            con = java.sql.DriverManager.getConnection (URL, USER, PASS);
            System.out.println ("Connection established.");
          }
          catch (java.sql.SQLException e) {
              e.printStackTrace();
            System.out.println ("Unable to establish a connection through the driver manager.");                                
          }  
    }

}
