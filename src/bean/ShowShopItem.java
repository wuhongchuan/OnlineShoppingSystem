package bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * add shop information
 * */

public class ShowShopItem extends JFrame implements ActionListener {

	Container contentPane;
	public String shopIdTemp;
	JTextField shopId = new JTextField(15);

	JPanel centerPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel jLabel1 = new JLabel();

	JButton showInfo = new JButton();
	JButton clearInfo = new JButton();
	JButton exitInfo = new JButton();

	GridBagLayout girdBag = new GridBagLayout();
	GridBagConstraints girdBagCon;


	public void Init() throws Exception{
		contentPane =  this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		centerPanel.setLayout(girdBag);

		jLabel1.setText("ShopId£º");
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
		girdBag.setConstraints(shopId, girdBagCon);
		centerPanel.add(shopId);

		contentPane.add(centerPanel,BorderLayout.CENTER);

	}

	void setNull(){
		shopId.setText(null);
	}

	public ShowShopItem(){
		this.setTitle("Show items in a shop");
		this.setResizable(false);

		shopId.setEditable(true);

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
		showInfo.setText("Show");
		showInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(showInfo);
		clearInfo.setText("Clear");
		clearInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(clearInfo);
		exitInfo.setText("Exit");
		exitInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(exitInfo);

		showInfo.addActionListener(this);
		clearInfo.addActionListener(this);
		exitInfo.addActionListener(this);

		this.contentPane.add(downPanel,BorderLayout.SOUTH);
	}
	

	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == exitInfo){  // Exit
			this.dispose();
		}
		else if(obj == showInfo){   // Add
			shopId.setEnabled(false);

			showInfo.setEnabled(false);
			clearInfo.setEnabled(false);
			exitInfo.setEnabled(false);

			shopIdTemp = shopId.getText().trim();

			ShoppingFrame sf = new ShoppingFrame("");

			String sql = "select * from shop where shopId=?";
			String[] params = new String[]{shopIdTemp};
			ShopModel model = new ShopModel();
			model.queryShop(sql, params);

			sf.getTable().setModel(model);

			this.dispose();

		}
		else if (obj == clearInfo) { // Clear
			setNull();
		}
	}

//	public ShopModel getModel() {
//		return model;
//	}

//	public String getShopId() {
//		return shopIdTemp;
//	}
}