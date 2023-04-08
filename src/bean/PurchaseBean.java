package bean;

import javax.swing.*;
import java.sql.ResultSet;

//有关顾客买卖商品的类

public class PurchaseBean {
	String sql;
	ResultSet rs = null;

	String customerId;
	String shopId;
	String itemId;


	// Purchase Item
	public void purchaseItem(String cid, String sid, String iid) {
		Database DB = new Database();

		this.customerId = cid;
		this.shopId = sid;
		this.itemId = iid;

		sql = "insert into purchase(customerId, shopId, itemId) values ('" + customerId + "','" + shopId + "','" + itemId + "')";
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
	public void DeleteOrderItem(String cid, String sid, String iid) {
		Database DB = new Database();

		this.customerId = cid;
		this.shopId = sid;
		this.itemId = iid;

		sql = "delete from purchase WHERE customerId='" + customerId + "' AND shopId='" + shopId + "'AND itemId='" + itemId +"'";
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

	/**
	 * Delete All Items
	 */
	public void DeleteAllItem(String cid) {
		Database DB = new Database();
		this.customerId = cid;

		sql = "delete from purchase WHERE customerId='" + customerId +"'";
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
