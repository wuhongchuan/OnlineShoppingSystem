package bean;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

//有关顾客买卖商品的类

public class PurchaseBean {
	String sql;
	ResultSet rs = null;

	Integer purchaseId;
	Integer customerId;
	Integer shopId;
	Integer itemId;


	// Purchase Item
	public void purchaseItem(Integer pid, Integer cid, Integer sid, Integer iid) {
		Database DB = new Database();

		this.purchaseId = pid;
		this.customerId = cid;
		this.shopId = sid;
		this.itemId = iid;

		sql = "insert into purchase(pId, customerId, shopId, itemId) values ('" + purchaseId.toString() + "','"+ customerId.toString() + "','" + shopId.toString() + "','" + itemId.toString() + "')";
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

	public int getPurchaseId(){
		Database2 db = new Database2();
		Integer purchaseId = null;
		sql = "select max(pid) from purchase";
		try{
			rs = db.queryResult(sql);
			if(rs.next()){
				purchaseId = rs.getInt(1) + 1;
			}else{
				purchaseId = 1;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		return purchaseId;
	}


	/**
	 * Delete Item
	 */
	public void DeleteOrderItem(Integer cid, Integer sid, Integer iid) {
		Database DB = new Database();

		this.customerId = cid;
		this.shopId = sid;
		this.itemId = iid;

		sql = "delete from purchase WHERE customerId='" + customerId.toString() + "' AND shopId='" + shopId.toString() + "'AND itemId='" + itemId.toString() +"'";
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
	public void DeleteAllItem(Integer cid) {
		Database DB = new Database();
		this.customerId = cid;

		sql = "delete from purchase WHERE customerId='" + customerId +"'";
		try {
			DB.OpenConn();
			DB.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "All orders successfully deleted！");
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "delete fail", "error", JOptionPane.ERROR_MESSAGE);
		} finally {
			DB.closeStmt();
			DB.closeConn();
		}

	}
}
