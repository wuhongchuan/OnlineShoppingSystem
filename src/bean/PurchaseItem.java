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

public class PurchaseItem extends JFrame implements ActionListener {

	static String cid = "";
	JTextField customerId = new JTextField(5);

	JComboBox<String> shopId = new JComboBox<>();
	JComboBox<String> itemId = new JComboBox<>();

	JPanel centerPanel = new JPanel(new GridBagLayout());

	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();


	JButton purchaseInfo = new JButton();
	JButton exitInfo = new JButton();


	public void Init() throws Exception{

		GridBagConstraints girdBagCon = new GridBagConstraints();
		jLabel1.setText("CustomerId£º");
		jLabel1.setFont(new Font("Dialog",0,12));

		girdBagCon.gridx = 0;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,10,10,1);
		centerPanel.add(jLabel1, girdBagCon);


		girdBagCon.gridx = 1;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,1,10,15);
		customerId.setText(cid);
		customerId.setEnabled(false);
		centerPanel.add(customerId);

		List<String> ct = getShopId();
		for (String item : ct) {
			shopId.addItem(item);
		}

		jLabel2.setText("ShopId£º");
		jLabel2.setFont(new Font("Dialog",0,12));
		girdBagCon.gridx = 2;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,15,10,1);
		centerPanel.add(jLabel2, girdBagCon);

		girdBagCon.gridx = 3;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,1,10,10);
		centerPanel.add(shopId, girdBagCon);


		jLabel3.setText("ItemId£º");
		jLabel3.setFont(new Font("Dialog",0,12));
		girdBagCon.gridx = 0;
		girdBagCon.gridy = 1;
		girdBagCon.insets = new Insets(10,10,10,1);
		centerPanel.add(jLabel3, girdBagCon);

		girdBagCon.gridx = 1;
		girdBagCon.gridy = 1;
		girdBagCon.insets = new Insets(10,1,10,15);
		centerPanel.add(itemId, girdBagCon);

		purchaseInfo.setText("Purchase");
		purchaseInfo.setFont(new Font("Dialog",0,12));
		girdBagCon.gridx = 0;
		girdBagCon.gridy = 3;
		girdBagCon.insets = new Insets(10, 10, 10, 1);
		centerPanel.add(purchaseInfo, girdBagCon);

		exitInfo.setText("Exit");
		exitInfo.setFont(new Font("Dialog",0,12));
		girdBagCon.gridx = 1;
		girdBagCon.gridy = 3;
		girdBagCon.insets = new Insets(10, 1, 10, 15);
		centerPanel.add(exitInfo, girdBagCon);

		getContentPane().add(centerPanel, BorderLayout.CENTER);

		InitShopId();

		purchaseInfo.addActionListener(this);
		shopId.addActionListener(this);
		exitInfo.addActionListener(this);

	}

	public void InitShopId() {
		List<String> ct = getShopId();
		shopId.removeAllItems();
		for (String item : ct) {
			shopId.addItem(item);
		}
	}

	private void UpdateItemId() {
		List<String> ct = getItemId();
		itemId.removeAllItems();
		for (String item : ct) {
			itemId.addItem(item);
		}

	}

	public List getShopId(){
		ShopModel model = new ShopModel();
		String sql = "select shopId from Shop where 1=?";
		String[] params = new String[]{"1"};
		ArrayList idList = model.getShopId(sql, params);

		return idList;
	}

	public List getItemId(){

		ItemModel model = new ItemModel();
		String sql = "select itemId from Item where itemShopId=?";
		String item_shop_id = shopId.getSelectedItem().toString();

		String[] params = new String[]{item_shop_id};
		ArrayList idList = model.getItemShopId(sql, params);

		return idList;
	}

	public PurchaseItem(String ccid){
		this.cid = ccid;
		this.setTitle("Purchase Item");
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
		else if(obj == purchaseInfo){   // Purchase
			purchaseInfo.setEnabled(false);
			exitInfo.setEnabled(false);

			PurchaseBean pItem = new PurchaseBean();
			pItem.purchaseItem(customerId.getText(), shopId.getSelectedItem().toString(),
					itemId.getSelectedItem().toString());

			this.dispose();
		}

	}


}