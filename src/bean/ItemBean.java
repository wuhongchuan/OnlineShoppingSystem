package bean;

import javax.swing.*;
import java.sql.ResultSet;

// item database

public class ItemBean {
	String sql;
	ResultSet rs = null;

	Integer itemId;
	String itemName;
	String itemPrice;
	Integer itemShopId;


	// Add Item
	public void itemAdd(Integer iid, String name, String price, Integer isid) {
		Database DB = new Database();

		this.itemId = iid;
		this.itemName = name;
		this.itemPrice = price;
		this.itemShopId = isid;

		sql = "insert into item(itemId, itemName, itemPrice, itemShopId) values ('" + itemId.toString() + "','" + itemName + "','" + itemPrice + "','" + itemShopId.toString() + "')";
		try {
			DB.OpenConn();
			DB.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "A new record is successfully added£¡");

		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "save fail", "error", JOptionPane.ERROR_MESSAGE);
		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
	}

//	/**
//	 * Delete Item
//	 */
//	public void itemDel(Integer sid) {
//		Database DB = new Database();
//
//		this.itemId = sid;
//
//		sql = "delete from item where itemid = " + itemId.toString() + "";
//		try {
//			DB.OpenConn();
//			DB.executeUpdate(sql);
//			JOptionPane.showMessageDialog(null, "A new record is successfully deleted£¡");
//		} catch (Exception e) {
//			System.out.println(e);
//			JOptionPane.showMessageDialog(null, "delete fail", "error", JOptionPane.ERROR_MESSAGE);
//		} finally {
//			DB.closeStmt();
//			DB.closeConn();
//		}
//	}
}
