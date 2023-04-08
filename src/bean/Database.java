package bean;

import java.sql.*;
import java.sql.Statement;


public class Database {

	private Statement stmt=null;
	ResultSet rs=null;
	private Connection conn=null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	// name of database is 'database'
	private String url = "jdbc:mysql://localhost:3306/database?serverTimezone = GMT";
	private String user = "root";
	private String password = "crocodile";


	public Database(){
	}

	/**
	 * open database connect
	 */
	public void OpenConn()throws Exception{
		try{
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
		}
		catch(Exception e){
			System.err.println("OpenConn:"+e.getMessage());
		}
	}

	public void executeUpdate(String sql){
		stmt=null;
		rs=null;
		try{
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			conn.setAutoCommit(false);
			conn.commit();
		}
		catch(SQLException e){
			System.err.println("executeUpdate:"+e.getMessage());
		}
	}

	public void closeStmt(){
		try{
			stmt.close();
		}
		catch(SQLException e){
			System.err.println("closeStmt:"+e.getMessage());
		}
	}

	/**
	 * close database conn
	 */
	public void closeConn(){
		try{
			conn.close();
		}
		catch(SQLException ex){
			System.err.println("aq.closeConn:"+ex.getMessage());
		}
	}

}