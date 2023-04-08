package bean;

import java.sql.*;

/**
 * �������ݿ����
 */
public class Database2 {
	//����������
	private String driver = "com.mysql.cj.jdbc.Driver";
	String sql;
	//URLָҪ���ʵ����ݿ���system
//	String url="jdbc:mysql://localhost:3306/database?serverTimezone = GMT";
	//	�������ݿ����
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;
	
	/**
	 * ִ��sql��䣬������Ż�ȡ�Ľ����rs  �޲�
	 */
	public ResultSet queryResult(String sql){
		try{
			//������������
			Class.forName(driver);
			//����Mysql���ݿ�
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/database?serverTimezone = GMT",
												"root",
												"crocodile");
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			
		}
		return rs;
	}

	/*
	 * ��������ϻ�ȡ�����
	 * */
	public ResultSet queryResult(String sql,String[] params){
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/database?serverTimezone = GMT",
												"root",
												"crocodile");
			ps = conn.prepareStatement(sql);


			for(int i=0;i<params.length;i++){
				ps.setString(i+1, params[i]);
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			
		}
		return rs;
	}
	
	/**
	 * ִ��sql���
	 */
	public boolean isUpdate(String sql,String[] params){
		boolean b = true;
		try{
			Class.forName(driver);
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/database?serverTimezone = GMT",
											"root",
											"crocodile");
			ps = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				ps.setString(i+1, params[i]);
			}
			int i = ps.executeUpdate();
			if(i!=1){
				b = false;
			}else{
				b = true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	

	/**
	 * �ر����ݿ�����
	 */
	public void close(){
		try{
			if(rs!=null){
				rs.close();
				rs = null;
			}
			if(ps!=null){
				ps.close();
				ps = null;
			}

			if(conn!=null){
				conn.close();
				conn = null;
			}

		}
		catch(SQLException e){
			e.printStackTrace();
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