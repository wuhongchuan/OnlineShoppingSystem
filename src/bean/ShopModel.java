package bean;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ShopModel extends AbstractTableModel{

	private Database2 db;
	private ResultSet rs = null;

	//创建表格的数据相关
	private Vector rowDatas,columnNames;
	public ShopModel(){}
	
	public boolean updataStop(String sql,String[] params){
		db = new Database2();
		return db.isUpdate(sql,params);
	}
	
	//获取查询的数据
	public void queryStudent(String sql,String[] params){
		columnNames = new Vector();
		columnNames.add("Id");
		columnNames.add("Name");
		columnNames.add("Rating");
		columnNames.add("Location");

		rowDatas = new Vector();
		try{
			db = new Database2();
			rs = db.queryResult(sql,params);
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getInt(1));
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
