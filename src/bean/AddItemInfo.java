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

public class AddItemInfo extends JFrame implements ActionListener {

	Container contentPane;
	JTextField itemId = new JTextField(15);
	JTextField itemName = new JTextField(15);
	JTextField itemPrice = new JTextField(15);
//	JTextField itemShopId = new JTextField(15);


	JPanel centerPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();

	JComboBox<String> itemShopId = new JComboBox<>();

	JButton addInfo = new JButton();
	JButton clearInfo = new JButton();
	JButton exitInfo = new JButton();

	GridBagLayout girdBag = new GridBagLayout();
	GridBagConstraints girdBagCon;

	public void Init() throws Exception{
		contentPane =  this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		centerPanel.setLayout(girdBag);

		jLabel1.setText("ItemId£º");
		jLabel1.setFont(new Font("Dialog",0,12));
		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 0;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,10,10,1);
		girdBag.setConstraints(jLabel1, girdBagCon);
		centerPanel.add(jLabel1);
		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 1;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,1,10,15);
		girdBag.setConstraints(itemId, girdBagCon);
		centerPanel.add(itemId);

		jLabel2.setText("ItemName£º");
		jLabel2.setFont(new Font("Dialog",0,12));
		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 2;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,15,10,1);
		girdBag.setConstraints(jLabel2, girdBagCon);
		centerPanel.add(jLabel2);
		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 3;
		girdBagCon.gridy = 0;
		girdBagCon.insets = new Insets(10,1,10,10);
		girdBag.setConstraints(itemName, girdBagCon);
		centerPanel.add(itemName);


		jLabel3.setText("ItemPrice£º");
		jLabel3.setFont(new Font("Dialog",0,12));
		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 0;
		girdBagCon.gridy = 1;
		girdBagCon.insets = new Insets(10,10,10,1);
		girdBag.setConstraints(jLabel3, girdBagCon);
		centerPanel.add(jLabel3);
		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 1;
		girdBagCon.gridy = 1;
		girdBagCon.insets = new Insets(10,1,10,15);
		girdBag.setConstraints(itemPrice, girdBagCon);
		centerPanel.add(itemPrice);

		List<String> ct = getShopItemId();

		for (String item : ct) {
			itemShopId.addItem(item);
		}

		jLabel4.setText("ItemShopId£º");
		jLabel4.setFont(new Font("Dialog",0,12));
		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 2;
		girdBagCon.gridy = 1;
		girdBagCon.insets = new Insets(10,15,10,1);
		girdBag.setConstraints(jLabel4, girdBagCon);
		centerPanel.add(jLabel4);
		girdBagCon = new GridBagConstraints();
		girdBagCon.gridx = 3;
		girdBagCon.gridy = 1;
		girdBagCon.insets = new Insets(10,1,10,10);
		girdBag.setConstraints(itemShopId,girdBagCon);
		centerPanel.add(itemShopId);

		contentPane.add(centerPanel,BorderLayout.CENTER);

	}

	void setNull(){
		itemId.setText(null);
		itemName.setText(null);
		itemPrice.setText(null);
//		itemShopId.setText(null);
	}

//	public List getShopItemId(){
//		ItemModel model = new ItemModel();
//		String sql = "select itemShopId from Item where 1=?";
//		String[] params = new String[]{"1"};
//		ArrayList idList = model.getItemShopId(sql, params);
//
//		return idList;
//	}

	public List getShopItemId(){
		ItemModel model = new ItemModel();
		String sql = "select shopId from Shop where 1=?";
		String[] params = new String[]{"1"};
		ArrayList idList = model.getItemShopId(sql, params);

		return idList;
	}
	public AddItemInfo(){
		this.setTitle("Add Item");
		this.setResizable(false);

		itemId.setEditable(true);
		itemName.setEditable(true);
		itemPrice.setEditable(true);
//		itemShopId.setEditable(true);

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


	public void downInit(){
		addInfo.setText("Add");
		addInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(addInfo);
		clearInfo.setText("Clear");
		clearInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(clearInfo);
		exitInfo.setText("Exit");
		exitInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(exitInfo);

		addInfo.addActionListener(this);
		clearInfo.addActionListener(this);
		exitInfo.addActionListener(this);

		this.contentPane.add(downPanel,BorderLayout.SOUTH);
	}
	

	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == exitInfo){  // Exit
			this.dispose();
		}
		else if(obj == addInfo){   // Add
			itemId.setEnabled(false);
			itemName.setEnabled(false);
			itemPrice.setEnabled(false);
//			itemShopId.setEnabled(false);

			addInfo.setEnabled(false);
			clearInfo.setEnabled(false);
			exitInfo.setEnabled(false);

			ItemBean addItem = new ItemBean();
			addItem.itemAdd(itemId.getText(), itemName.getText(), itemPrice.getText(),
					itemShopId.getSelectedItem().toString());

			this.dispose();

		}
		else if (obj == clearInfo) { // Clear
			setNull();
		}
	}	
}