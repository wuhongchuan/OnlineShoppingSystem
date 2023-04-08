package bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
 * add item information
 * */

public class DelOrderInfo extends JFrame implements ActionListener {

	static Integer cid = 0;
	JTextField customerId = new JTextField(5);

	JComboBox<Integer> shopId = new JComboBox<>();
	JComboBox<Integer> itemId = new JComboBox<>();

	JPanel centerPanel = new JPanel(new GridBagLayout());

	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();


	JButton deleteInfo = new JButton();
	JButton exitInfo = new JButton();


	public void Init() throws Exception{

		GridBagConstraints girdBagCon = new GridBagConstraints();
		jLabel1.setText("CustomerId��");
		jLabel1.setFont(new Font("Dialog",0,12));

		girdBagCon.gridx = 0;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,10,10,1);
		centerPanel.add(jLabel1, girdBagCon);


		girdBagCon.gridx = 1;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,1,10,15);
		customerId.setText(cid.toString());
		customerId.setEnabled(false);
		centerPanel.add(customerId);

		List<Integer> ct = getShopId();
		for (Integer item : ct) {
			shopId.addItem(item);
		}

		jLabel2.setText("ShopId��");
		jLabel2.setFont(new Font("Dialog",0,12));
		girdBagCon.gridx = 2;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,15,10,1);
		centerPanel.add(jLabel2, girdBagCon);

		girdBagCon.gridx = 3;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,1,10,10);
		centerPanel.add(shopId, girdBagCon);


		jLabel3.setText("ItemId��");
		jLabel3.setFont(new Font("Dialog",0,12));
		girdBagCon.gridx = 0;
		girdBagCon.gridy = 1;
		girdBagCon.insets = new Insets(10,10,10,1);
		centerPanel.add(jLabel3, girdBagCon);

		girdBagCon.gridx = 1;
		girdBagCon.gridy = 1;
		girdBagCon.insets = new Insets(10,1,10,15);
		centerPanel.add(itemId, girdBagCon);

		deleteInfo.setText("Delete");
		deleteInfo.setFont(new Font("Dialog",0,12));
		girdBagCon.gridx = 0;
		girdBagCon.gridy = 3;
		girdBagCon.insets = new Insets(10, 10, 10, 1);
		centerPanel.add(deleteInfo, girdBagCon);

		exitInfo.setText("Exit");
		exitInfo.setFont(new Font("Dialog",0,12));
		girdBagCon.gridx = 1;
		girdBagCon.gridy = 3;
		girdBagCon.insets = new Insets(10, 1, 10, 15);
		centerPanel.add(exitInfo, girdBagCon);

		getContentPane().add(centerPanel, BorderLayout.CENTER);

		InitShopId();

		deleteInfo.addActionListener(this);
		shopId.addActionListener(this);
		exitInfo.addActionListener(this);

	}

	public void InitShopId() {
		List<Integer> ct = getShopId();
		shopId.removeAllItems();
		for (Integer item : ct) {
			shopId.addItem(item);
		}
	}

	private void UpdateItemId() {
		List<Integer> ct = getItemId();
		itemId.removeAllItems();
		for (Integer item : ct) {
			itemId.addItem(item);
		}

	}

	public List getShopId(){
		ShopModel model = new ShopModel();
		String sql = "select shopId from purchase where 1=?";
		String [] params = new String[]{"1"};
		ArrayList idList = model.getShopId(sql, params);

		return idList;
	}

	public List getItemId(){

		ItemModel model = new ItemModel();
		String sql = "select itemId from purchase where shopId=?";
		Integer item_shop_id = Integer.parseInt(shopId.getSelectedItem().toString());

		String[] params = new String[]{item_shop_id.toString()};
		ArrayList idList = model.getItemShopId(sql, params);

		return idList;
	}

	public DelOrderInfo(Integer ccid){
		this.cid = ccid;
		this.setTitle("Delete Item");
		this.setResizable(false);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - 400) / 2, (screenSize.height - 300) / 2 + 45);

		ImageIcon img = new ImageIcon("pic/2.jpg");
		JLabel imgLabel = new JLabel(img);

		try	{
			Init();
		}
		catch(Exception	e) {
			e.printStackTrace();
		}

		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);
		contain.setLayout(new FlowLayout());
	}


	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == exitInfo){  // Exit
			this.dispose();
		}
		else if(obj == shopId){
			UpdateItemId();
		}
		else if(obj == deleteInfo){   // Purchase
			deleteInfo.setEnabled(false);
			exitInfo.setEnabled(false);

			PurchaseBean pItem = new PurchaseBean();
			pItem.DeleteOrderItem(Integer.parseInt(customerId.getText()), Integer.parseInt(shopId.getSelectedItem().toString()),
					Integer.parseInt(itemId.getSelectedItem().toString()));

			this.dispose();
		}
	}

}