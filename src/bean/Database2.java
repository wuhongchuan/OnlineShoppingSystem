package bean;

import java.sql.*;


public class Database2 {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/database?serverTimezone = GMT";
	private String user = "root";
	private String password = "crocodile";

	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;

	/**
	 * no param
	 */
	public ResultSet queryResult(String sql){
		try{
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
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


	public ResultSet queryResult(String sql,String[] params){
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, user, password);
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
	 * close connection
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

}