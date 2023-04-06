package bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * Item Main Frame
 * All Item
 */
public class ItemFrame extends JFrame implements ActionListener{
	//B orderLayout
	private JPanel jpNorth,jpNorthTop,jpNorthBottom;

	Dimension faceSize = new Dimension(400,300);
	Image icon;
	// menu
	private JMenuBar jmb;
	private JMenu jm1;
	private JComboBox jcb1;
	// menu
	private JToolBar jtb;
	private JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	// top
	private JLabel jlQuery;
	private JTextField jtfQuery;
	private JButton jbQuery,jbFlush;
	// center
	private JScrollPane jsp;

	private JTable jtInfo;


	// Init
	public ItemFrame(){
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

		this.setSize(faceSize);

		this.setTitle("商店主界面");
		icon = getImage("pic/1.jpg");
		this.setIconImage(icon);
		
		jpNorth = new JPanel();
		jpNorth.setLayout(new BorderLayout());
		jpNorthTop = new JPanel();
		jpNorthBottom = new JPanel();


		buildMenu();
		buildToolbar();
		jpNorth.add(jpNorthTop, BorderLayout.NORTH);
		jlQuery = new JLabel("Input");
		jtfQuery = new JTextField(10);
		// search
		String []ct = {"itemId","itemName","itemPrice","shopId"};
		jcb1 = new JComboBox(ct);
		jbQuery = new JButton("Search");
		jbFlush = new JButton("Flush");
		jbQuery.addActionListener(this);
		jbFlush.addActionListener(this);
		jpNorthBottom.add(jlQuery);
		jpNorthBottom.add(jcb1);
		jpNorthBottom.add(jtfQuery);
		jpNorthBottom.add(jbQuery);
		jpNorthBottom.add(jbFlush);
		jpNorth.add(jpNorthBottom,BorderLayout.SOUTH);
		this.getContentPane().add(jpNorth, BorderLayout.NORTH);
	
		// center
		ItemModel model = new ItemModel();
		String sql = "select *from item where 1=?";
		String[] params = new String[]{"1"};
		model.queryItem(sql,params);
		jtInfo = new JTable(model);
		jtInfo.setRowHeight(25);
		jsp = new JScrollPane(jtInfo);
		this.getContentPane().add(jsp,BorderLayout.CENTER);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		try{
			Init();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// menu
	public void buildMenu(){
		jmb = new JMenuBar();
		jm1 = new JMenu("help");
		jmb.add(jm1);
		this.setJMenuBar(jmb);
	}
	
	public void buildToolbar(){
		jtb = new JToolBar();
		jtb.setLocation(0,20);

		jb1 = new JButton("Show all Shops",new ImageIcon("src/pic/1.png"));
		jb2 = new JButton("Add Shop",new ImageIcon("src/pic/2.png"));
		jb3 = new JButton("Show all Items \nof a shop",new ImageIcon("src/pic/3.png"));
		jb4 = new JButton("Add Item",new ImageIcon("src/pic/4.png"));
		jb5 = new JButton("Item Purchase",new ImageIcon("src/pic/5.png"));
		jb6 = new JButton("Order Canceling",new ImageIcon("src/pic/6.png"));
//		jb7 = new JButton("课程删除",new ImageIcon("src/pic/7.png"));
//		jb8 = new JButton("成绩增加",new ImageIcon("src/pic/8.png"));
//		jb9 = new JButton("成绩修改",new ImageIcon("src/pic/9.png"));
		jb10 = new JButton("Exit",new ImageIcon("src/pic/10.png"));
	
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
//		jb7.addActionListener(this);
//		jb8.addActionListener(this);
//		jb9.addActionListener(this);
		jb10.addActionListener(this);
		
		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);
		jtb.add(jb6);
//		jtb.add(jb7);
//		jtb.add(jb8);
//		jtb.add(jb9);
		jtb.add(jb10);
		jpNorthTop.add(jtb);
	}
	

	private void Init() throws Exception{
		this.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			}
		);	
	}
	
	//事件处理
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jb10){   // exit
			System.exit(0);
		}
		if (obj == jbQuery) {    // search
			String jtfValue = jtfQuery.getText().trim();
			//查询的时候输入空字符串
			if(jtfValue.equals("")){
				String sql = "select * from item where 1=?";
				String[] params = new String[]{"1"};
				ItemModel model = new ItemModel();
				model.queryItem(sql, params);
				jtInfo.setModel(model);
			}else{
				if((String)jcb1.getSelectedItem() == "itemId"){
					String sql = "select * from shop where itemId=?";
					String[] params = new String[]{jtfValue};
					ItemModel model = new ItemModel();
					model.queryItem(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="itemName"){
					String sql = "select * from shop where itemName=?";
					String[] params = new String[]{jtfValue};
					ItemModel model = new ItemModel();
					model.queryItem(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="itemPrice"){
					String sql = "select * from shop where itemPrice=?";
					String[] params = new String[]{jtfValue};
					ItemModel model = new ItemModel();
					model.queryItem(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="shopId"){
					String sql = "select * from shop where itemShopId=?";
					String[] params = new String[]{jtfValue};
					ItemModel model = new ItemModel();
					model.queryItem(sql, params);
					jtInfo.setModel(model);
				}
			}
		}
		else if(obj == jbFlush){
			this.dispose();
			boolean packFrame = false;
			ItemFrame frame = new ItemFrame();
			if(packFrame){
				frame.pack();
			}else{
				frame.validate();
			}
			//设置运行时窗口的位置
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize = frame.getSize();
			if(frameSize.height > screenSize.height){
				frameSize.height = screenSize.height;
			}
			if(frameSize.width > screenSize.width){
				frameSize.width = screenSize.width;
			}
			frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
			frame.setVisible(true);
		}

		else if (obj == jb1) { // show all shops
			String sql = "select * from shop where 1=?";
			String[] params = new String[]{"1"};
			ShopModel model = new ShopModel();
			model.queryShop(sql, params);
			jtInfo.setModel(model);
		}
		else if(obj == jb2){ // Add Shop
			AddShopInfo asi = new AddShopInfo();
			asi.downInit();
			asi.pack();
			asi.setVisible(true);
		}
		else if (obj == jb3) { // show all items
			String sql = "select * from item where 1=?";
			String[] params = new String[]{"1"};
			ItemModel model = new ItemModel();
			model.queryItem(sql, params);
			jtInfo.setModel(model);
		}
		else if (obj == jb4) { // Add Item
			AddItemInfo asi = new AddItemInfo();
			asi.downInit();
			asi.pack();
			asi.setVisible(true);
		}
//		else if (obj == jb5) { //课程增加
//			AddCourseInfo aci = new AddCourseInfo();
//			aci.downInit();
//			aci.pack();
//			aci.setVisible(true);
//		}
//		else if (obj == jb6) { //课程修改
//			EditCourseInfo eci = new EditCourseInfo();
//			eci.downInit();
//			eci.pack();
//			eci.setVisible(true);
//		}
//		else if (obj == jb7) { //课程删除
//			DelCourseInfo dci = new DelCourseInfo();
//			dci.downInit();
//			dci.pack();
//			dci.setVisible(true);
//		}
//		else if (obj == jb8) { //成绩增加
//			AddGradeInfo agi = new AddGradeInfo();
//			agi.downInit();
//			agi.pack();
//			agi.setVisible(true);
//		}
//		else if (obj == jb9) { //成绩修改
//			EditGradeInfo egi = new EditGradeInfo();
//			egi.downInit();
//			egi.pack();
//			egi.setVisible(true);
//		}

	}



	Image getImage(String filename){
		URLClassLoader urlLoader = (URLClassLoader)this.getClass().getClassLoader();
		URL url = null;
		Image image = null;
		url = urlLoader.findResource(filename);
		image = Toolkit.getDefaultToolkit().getImage(url);
		MediaTracker mediatracker = new MediaTracker(this);
		try {
			mediatracker.addImage(image, 0);
			mediatracker.waitForID(0);
		}
		catch (InterruptedException _ex) {
			image = null;
		}
		if (mediatracker.isErrorID(0)) {
			image = null;
		}
		return image;
	}

}
