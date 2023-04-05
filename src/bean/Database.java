package bean;

import java.beans.*;
import java.sql.*;
import java.sql.Statement;

/**
 * �������ݿ����
 */
public class Database {

	private Statement stmt=null;
	ResultSet rs=null;
	private Connection conn=null;
	String sql;


	public Database(){
	}

	/**
	 * �����ݿ����ӣ�
	 */
	public void OpenConn()throws Exception{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			// ���ݿ�����Ϊdatabase
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/database?serverTimezone = GMT",
					"root",
					"crocodile");
		}
		catch(Exception e){
			System.err.println("OpenConn:"+e.getMessage());
		}
	}

	/**
	 * ִ��sql��䣬���ؽ����rs
	 */
	public ResultSet executeQuery(String sql){
		stmt = null;
		rs=null;
		try{
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
		}
		catch(SQLException e){
			System.err.println("executeQuery:"+e.getMessage());
		}
		return rs;
	}

	/**
	 * ִ��sql���
	 */
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
	 * �ر����ݿ�����
	 */
	public void closeConn(){
		try{
			conn.close();
		}
		catch(SQLException ex){
			System.err.println("aq.closeConn:"+ex.getMessage());
		}
	}

	/*
	 *ת������
	 */
	public static String toGBK(String str){
		try {
			if(str==null)
				str = "";
			else
				str=new String(str.getBytes("ISO-8859-1"),"GBK");
		}
		catch (Exception e) {System.out.println(e);}

		return str;
	}
}