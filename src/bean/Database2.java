package bean;

import java.sql.*;

/**
 * 连接数据库的类
 */
public class Database2 {
	//驱动程序名
	private String driver = "com.mysql.cj.jdbc.Driver";
	String sql;
	//URL指要访问的数据库名system
//	String url="jdbc:mysql://localhost:3306/database?serverTimezone = GMT";
	//	操作数据库的类
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection conn=null;
	
	/**
	 * 执行sql语句，用来存放获取的结果集rs  无参
	 */
	public ResultSet queryResult(String sql){
		try{
			//加载驱动程序
			Class.forName(driver);
			//连接Mysql数据库
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
	 * 与参数集合获取结果集
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
	 * 执行sql语句
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
	 * 关闭数据库连接
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
	 *转换编码
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