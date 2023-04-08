package bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * add shop information
 * */

public class AddShopInfo extends JFrame implements ActionListener {

	Container contentPane;
	JTextField shopId = new JTextField(15);
	JTextField shopName = new JTextField(15);
	JTextField shopRating = new JTextField(15);
	JTextField shopLocation = new JTextField(15);

	JPanel centerPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();

	JButton addInfo = new JButton();
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

		jLabel2.setText("ShopName£º");
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
		girdBag.setConstraints(shopName, girdBagCon);
		centerPanel.add(shopName);


		jLabel3.setText("ShopRating£º");
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
		girdBag.setConstraints(shopRating, girdBagCon);
		centerPanel.add(shopRating);


		jLabel4.setText("ShopLocation£º");
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
		girdBag.setConstraints(shopLocation,girdBagCon);
		centerPanel.add(shopLocation);


		contentPane.add(centerPanel,BorderLayout.CENTER);

	}

	void setNull(){
		shopId.setText(null);
		shopName.setText(null);
		shopRating.setText(null);
		shopLocation.setText(null);
	}

	public AddShopInfo(){
		this.setTitle("Add Shop");
		this.setResizable(false);

		shopId.setEditable(true);
		shopName.setEditable(true);
		shopRating.setEditable(true);
		shopLocation.setEditable(true);

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
			shopId.setEnabled(false);
			shopName.setEnabled(false);
			shopRating.setEnabled(false);
			shopLocation.setEnabled(false);

			addInfo.setEnabled(false);
			clearInfo.setEnabled(false);
			exitInfo.setEnabled(false);

			ShopBean addShop = new ShopBean();
			addShop.shopAdd(shopId.getText(), shopName.getText(), shopRating.getText(),
					shopLocation.getText());
			
			this.dispose();
			
//			AddShopInfo asi = new AddShopInfo();
//			asi.downInit();
//			asi.pack();
//			asi.setVisible(true);
	
		}
		else if (obj == clearInfo) { // Clear
			setNull();
		}
	}	
}