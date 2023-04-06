package bean;

import javax.swing.*;
import java.sql.ResultSet;

//有关商品信息数据库操作的类

public class ItemBean {
	String sql;
	ResultSet rs = null;

	String itemId;
	String itemName;
	String itemPrice;
	String itemShopId;


	// Add Item
	public void itemAdd(String iid, String name, String price, String isid) {
		Database DB = new Database();

		this.itemId = iid;
		this.itemName = name;
		this.itemPrice = price;
		this.itemShopId = isid;

		sql = "insert into item(itemId, itemName, itemPrice, itemShopId) values ('" + itemId + "','" + itemName + "','" + itemPrice + "','" + itemShopId + "')";
		try {
			DB.OpenConn();
			DB.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "A new record is successfully added！");

		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "save fail", "error", JOptionPane.ERROR_MESSAGE);
		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
	}


	/**
	 * Delete Item
	 */
	public void itemDel(String sid) {
		Database DB = new Database();

		this.itemId = sid;

		sql = "delete from item where itemid = " + itemId + "";
		try {
			DB.OpenConn();
			DB.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "A new record is successfully deleted！");
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "delete fail", "error", JOptionPane.ERROR_MESSAGE);
		} finally {
			DB.closeStmt();
			DB.closeConn();
		}
	}
}
