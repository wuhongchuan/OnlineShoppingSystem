package bean;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class ShopModel extends AbstractTableModel{

	private Database2 db;
	private ResultSet rs = null;

	private Vector rowDatas,columnNames;
	public ShopModel(){}
	
	public boolean updateShop(String sql,String[] params){
		db = new Database2();
		return db.isUpdate(sql,params);
	}

	public void queryShop(String sql,String[] params){
		columnNames = new Vector();
		columnNames.add("Shop_Id");
		columnNames.add("Shop_Name");
		columnNames.add("Shop_Rating");
		columnNames.add("Shop_Location");

		rowDatas = new Vector();
		try{
			db = new Database2();
			rs = db.queryResult(sql,params);
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				rowDatas.add(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			db.close();
		}
	}

	public ArrayList getShopId(String sql, String[] params){

		ArrayList<Integer> idList = new ArrayList<Integer>();
		try{
			db = new Database2();
			rs = db.queryResult(sql,params);

			while (rs.next()) {
				idList.add(rs.getInt(1));
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		return idList;
	}

	public int getRowCount() {
		return this.rowDatas.size();
	}
	public int getColumnCount() {
		return this.columnNames.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector)this.rowDatas.get(rowIndex)).get(columnIndex);
	}
	public String getColumnName(int column){
		return (String)this.columnNames.get(column);
	}
	
}
