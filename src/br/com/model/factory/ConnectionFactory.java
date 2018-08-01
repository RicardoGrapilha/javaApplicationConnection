package br.com.model.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private final static String serverName = "localhost";    //caminho do servidor do BD	 
	private final static String mydatabase = "javadb";        //nome do seu banco de dados
    
	private final static String URL = "jdbc:mysql://" + serverName + "/" + mydatabase;
    private final static String USER = "root";        //nome de um usuário de seu BD      
    private final static String PASS = "root";      //sua senha de acesso
    
    public static Connection getConnection(){
    	try {
			Class.forName(DRIVER);

			return DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException | SQLException ex) {
			throw new RuntimeException("Erro na conexão: ",ex);
		}
    	
    }
    public static void closeConnection(Connection con){    	
		try {
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    public static void closeConnection(Connection con, PreparedStatement stmt){
    	closeConnection(con);    	
		try {
			if(stmt != null)
				stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
    	closeConnection(con,stmt);
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
}
