package bean;

import javax.swing.*;
import java.sql.ResultSet;

//有关商场信息数据库操作的类

public class ShopBean {
	String sql;
	ResultSet rs = null;

	String shopId;
	String shopName;
	String shopRating;
	String shopLocation;


	// add shop information
	public void shopAdd(String sid, String name, String rating, String location) {
		Database DB = new Database();

		this.shopId = sid;
		this.shopName = name;
		this.shopRating = rating;
		this.shopLocation = location;

		sql = "insert into shop(shopId, shopName, shopRating, shopLocation) values ('" + shopId + "','" + shopName + "','" + shopRating + "','" + shopLocation + "')";
		try {
			DB.OpenConn();
			DB.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "A new record is successfully added！！");

		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "save fail", "error", JOptionPane.ERROR_MESSAGE);
		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
	}
}

//	/**
//	 * delete shop
//	 */
//	public void shopDel(String sid){
//		Database DB = new Database();
//
//		this.shopId = sid;
//
//		sql = "delete from shop where shopid = "+shopId+"";
//		try{
//			DB.OpenConn();
//			DB.executeUpdate(sql);
//			JOptionPane.showMessageDialog(null,"成功删除一条新的纪录！");
//		}
//		catch(Exception e){
//			System.out.println(e);
//			JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
//		}
//		finally {
//			DB.closeStmt();
//			DB.closeConn();
//		}
//	}
