//package bean;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.net.URL;
//import java.net.URLClassLoader;
//
//
//
//public class ItemInfo extends JFrame implements ActionListener{
//
//	Container contentPane;
//	JPanel centerPanel = new JPanel();
//	JPanel upPanel = new JPanel();
//	JPanel downPanel = new JPanel();
//	//框架的大小
//	Dimension faceSize = new Dimension(800,500);
//	JLabel jLabel1 = new JLabel();
//	JLabel jLabel2 = new JLabel();
//	JLabel jLabel3 = new JLabel();
//	JLabel jLabel4 = new JLabel();
//	JLabel jLabel5 = new JLabel();
//	JLabel jLabel6 = new JLabel();
//	JLabel jLabel7 = new JLabel();
//	JLabel jLabel8 = new JLabel();
//	JLabel jLabel9 = new JLabel();
//
//	JTextField itemId = new JTextField(15);
//	JTextField itemName = new JTextField(15);
//	JTextField itemPrice = new JTextField(15);
//	JTextField itemShopId = new JTextField(15);
//
//	JButton searchInfo = new JButton();
//	JButton addInfo = new JButton();
//	JButton modifyInfo = new JButton();
//	JButton deleteInfo = new JButton();
//	JButton clearInfo = new JButton();
//	JButton saveInfo = new JButton();
//	JButton exitInfo = new JButton();
//
//
//	GridBagLayout girdBag = new GridBagLayout();
//	GridBagConstraints girdBagCon;
//
//	public ItemInfo(){
//		//设置框架的大小
//		this.setSize(faceSize);
//		//设置标题
//		this.setTitle("学生综合信息管理");
//		this.setResizable(false);
//		//设置程序图标
//		this.setIconImage(getImage("pic/1.jpg"));
//		try	{
//			Init();
//		}
//		catch(Exception	e) {
//			e.printStackTrace();
//		}
//		ImageIcon img = new ImageIcon("pic/2.jpg");
//		JLabel imgLabel = new JLabel(img);
//
//		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
//
//		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
//		Container contain = this.getContentPane();
//		((JPanel) contain).setOpaque(false);
//		contain.setLayout(new FlowLayout());
//	}
//
//	public void Init() throws Exception{
//		contentPane =  this.getContentPane();
//		contentPane.setLayout(new BorderLayout());
//		//中部面板的布局
//		centerPanel.setLayout(girdBag);
//
//		jLabel1.setText("ItemId：");
//		jLabel1.setFont(new Font("Dialog",0,12));
//		girdBagCon = new GridBagConstraints();
//		girdBagCon.gridx = 0;
//		girdBagCon.gridy = 0;
//		girdBagCon.insets = new Insets(10,10,10,1);
//		girdBag.setConstraints(jLabel1, girdBagCon);
//		centerPanel.add(jLabel1);
//		girdBagCon = new GridBagConstraints();
//		girdBagCon.gridx = 1;
//		girdBagCon.gridy = 0;
//		girdBagCon.insets = new Insets(10,1,10,15);
//		girdBag.setConstraints(itemId, girdBagCon);
//		centerPanel.add(itemId);
//
//		jLabel2.setText("ShopName：");
//		jLabel2.setFont(new Font("Dialog",0,12));
//		girdBagCon = new GridBagConstraints();
//		girdBagCon.gridx = 2;
//		girdBagCon.gridy = 0;
//		girdBagCon.insets = new Insets(10,15,10,1);
//		girdBag.setConstraints(jLabel2, girdBagCon);
//		centerPanel.add(jLabel2);
//		girdBagCon = new GridBagConstraints();
//		girdBagCon.gridx = 3;
//		girdBagCon.gridy = 0;
//		girdBagCon.insets = new Insets(10,1,10,10);
//		girdBag.setConstraints(itemName, girdBagCon);
//		centerPanel.add(itemName);
//
//
//		jLabel3.setText("ShopRating：");
//		jLabel3.setFont(new Font("Dialog",0,12));
//		girdBagCon = new GridBagConstraints();
//		girdBagCon.gridx = 0;
//		girdBagCon.gridy = 1;
//		girdBagCon.insets = new Insets(10,10,10,1);
//		girdBag.setConstraints(jLabel3, girdBagCon);
//		centerPanel.add(jLabel3);
//		girdBagCon = new GridBagConstraints();
//		girdBagCon.gridx = 1;
//		girdBagCon.gridy = 1;
//		girdBagCon.insets = new Insets(10,1,10,15);
//		girdBag.setConstraints(itemPrice, girdBagCon);
//		centerPanel.add(itemPrice);
//
//
//		jLabel4.setText("ShopLocation：");
//		jLabel4.setFont(new Font("Dialog",0,12));
//		girdBagCon = new GridBagConstraints();
//		girdBagCon.gridx = 2;
//		girdBagCon.gridy = 1;
//		girdBagCon.insets = new Insets(10,15,10,1);
//		girdBag.setConstraints(jLabel4, girdBagCon);
//		centerPanel.add(jLabel4);
//		girdBagCon = new GridBagConstraints();
//		girdBagCon.gridx = 3;
//		girdBagCon.gridy = 1;
//		girdBagCon.insets = new Insets(10,1,10,10);
//		girdBag.setConstraints(itemShopId,girdBagCon);
//		centerPanel.add(itemShopId);
//
//
//		contentPane.add(centerPanel,BorderLayout.CENTER);
//
//		itemId.setEditable(false);
//		itemName.setEditable(false);
//		itemPrice.setEditable(false);
//		itemShopId.setEditable(false);
//	}
//
//	/**
//	 * 下部面板的布局
//	 */
//	public void downInit(){
//		searchInfo.setText("查询");
//		searchInfo.setFont(new Font("Dialog",0,12));
//		downPanel.add(searchInfo);
//		addInfo.setText("增加");
//		addInfo.setFont(new Font("Dialog",0,12));
//		downPanel.add(addInfo);
//		modifyInfo.setText("修改");
//		modifyInfo.setFont(new Font("Dialog",0,12));
//		downPanel.add(modifyInfo);
//		deleteInfo.setText("删除");
//		deleteInfo.setFont(new Font("Dialog",0,12));
//		downPanel.add(deleteInfo);
//		saveInfo.setText("保存");
//		saveInfo.setFont(new Font("Dialog",0,12));
//		downPanel.add(saveInfo);
//		clearInfo.setText("清空");
//		clearInfo.setFont(new Font("Dialog",0,12));
//		downPanel.add(clearInfo);
//		exitInfo.setText("退出");
//		exitInfo.setFont(new Font("Dialog",0,12));
//		downPanel.add(exitInfo);
//
//		contentPane.add(downPanel,BorderLayout.SOUTH);
//
//		//添加事件侦听
//		searchInfo.addActionListener(this);
//		addInfo.addActionListener(this);
//		modifyInfo.addActionListener(this);
//		deleteInfo.addActionListener(this);
//		saveInfo.addActionListener(this);
//		clearInfo.addActionListener(this);
//		exitInfo.addActionListener(this);
//
//		modifyInfo.setEnabled(false);
//		deleteInfo.setEnabled(false);
//		saveInfo.setEnabled(false);
//		clearInfo.setEnabled(false);
//	}
//
//
//
//	//事件处理
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		Object obj = e.getSource();
//		if(obj == searchInfo){   //查询
//		}
//		else if(obj == addInfo){   //增加
//		}
//		else if(obj == modifyInfo){   //修改
//		}
//		else if(obj == deleteInfo){   //删除
//		}
//		else if(obj == saveInfo){   //保存
//		}
//		else if(obj == clearInfo){   //清空
//			setNull();
//		}
//		else if (obj == exitInfo) { //退出
//			this.dispose();
//		}
//	}
//
//	//将文本框清空
//	void setNull(){
//		itemId.setText(null);
//		itemName.setText(null);
//		itemPrice.setText(null);
//		itemShopId.setText(null);
//	}
//
//	/*
//	 * 通过给定的文件名获得图像
//	 * */
//	Image getImage(String filename){
//		URLClassLoader urlLoader = (URLClassLoader)this.getClass().getClassLoader();
//		URL url = null;
//		Image image = null;
//		url = urlLoader.findResource(filename);
//		image = Toolkit.getDefaultToolkit().getImage(url);
//		MediaTracker mediatracker = new MediaTracker(this);
//		try {
//			mediatracker.addImage(image, 0);
//			mediatracker.waitForID(0);
//		}
//		catch (InterruptedException _ex) {
//			image = null;
//		}
//		if (mediatracker.isErrorID(0)) {
//			image = null;
//		}
//		return image;
//	}
//
//
//
//
//
//
//
//
//}
