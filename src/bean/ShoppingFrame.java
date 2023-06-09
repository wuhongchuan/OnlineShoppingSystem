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
 * Shop Main Frame
 * All Shop
 */
public class ShoppingFrame extends JFrame implements ActionListener{
	//B orderLayout
	private JPanel jpNorth,jpNorthTop,jpNorthBottom;

	Integer ccid = 0;
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

	JTable jtInfo;


	// Init
	public ShoppingFrame(Integer cid){
		this.ccid = cid;
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

		this.setSize(faceSize);

		this.setTitle("Shopping");
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
		String []ct = {"ItemId","ItemName","ItemPrice","ItemShopId"};
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
		ShopModel model = new ShopModel();
		String sql = "select * from shop where 1=?";
		String[] params = new String[]{"1"};
		model.queryShop(sql, params);

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
//		jb3 = new JButton("Show all Items \nof a shop",new ImageIcon("src/pic/3.png"));
		jb4 = new JButton("Add Item",new ImageIcon("src/pic/4.png"));
		jb5 = new JButton("Item Purchase",new ImageIcon("src/pic/5.png"));
		jb6 = new JButton("Show Orders",new ImageIcon("src/pic/6.png"));
		jb7 = new JButton("Order Canceling",new ImageIcon("src/pic/7.png"));
		jb8 = new JButton("Delete Order",new ImageIcon("src/pic/8.png"));

		jb10 = new JButton("Exit",new ImageIcon("src/pic/10.png"));
	
		jb1.addActionListener(this);
		jb2.addActionListener(this);
//		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		jb7.addActionListener(this);
		jb8.addActionListener(this);
//		jb9.addActionListener(this);
		jb10.addActionListener(this);
		
		jtb.add(jb1);
		jtb.add(jb2);
//		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);
		jtb.add(jb6);
		jtb.add(jb7);
		jtb.add(jb8);
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


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jb10){   // exit
			System.exit(0);
		}
		if (obj == jbQuery) {    // search
			String jtfValue = jtfQuery.getText().trim();
			if(jtfValue.equals("")){
				String sql = "select * from Item where 1=?";
				String[] params = new String[]{"1"};
				ItemModel model = new ItemModel();
				model.queryItem(sql, params);
				jtInfo.setModel(model);
			}else{
				if((String)jcb1.getSelectedItem() == "ItemId"){
					String sql = "select * from Item where itemId=?";
					String[] params = new String[]{jtfValue};
					ItemModel model = new ItemModel();
					model.queryItem(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="ItemName"){
					String sql = "select * from Item where itemName LIKE '" + jtfValue + "%'" ;
					ItemModel model = new ItemModel();
					model.queryItem(sql);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="ItemPrice"){
					String sql = "select * from Item where itemPrice=?";
					String[] params = new String[]{jtfValue};
					ItemModel model = new ItemModel();
					model.queryItem(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="ItemShopId"){
					String sql = "select * from Item where itemShopId=?";
					String[] params = new String[]{jtfValue};
					ItemModel model = new ItemModel();
					model.queryItem(sql, params);
					jtInfo.setModel(model);
				}
			}
		}
		else if (obj == jb1 || obj == jbFlush) { // show all shops
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
//		else if (obj == jb3) { // show Item in a shop
////			ItemModel
//			ShowShopItem ssi = new ShowShopItem();
//			ssi.downInit();
//			ssi.pack();
//			ssi.setVisible(true);
//			this.dispose();

//		}
		else if (obj == jb4) { // Add Item
			AddItemInfo asi = new AddItemInfo();
			asi.downInit();
			asi.pack();
			asi.setVisible(true);
		}
		else if (obj == jb5) { // purchase Item

			PurchaseItem pi = new PurchaseItem(ccid);
			pi.pack();
			pi.setVisible(true);
		}
		else if (obj == jb6) { // show orders
			String sql = "select * from purchase where 1=?";
			String[] params = new String[]{"1"};
			PurchaseModel model = new PurchaseModel();
			model.queryPurchase(sql, params);
			jtInfo.setModel(model);
		}
		else if (obj == jb7) { // order cancel
			DelOrderInfo doi = new DelOrderInfo(ccid);
			doi.pack();
			doi.setVisible(true);
		}
		else if (obj == jb8) { // order delete
			PurchaseBean pItem = new PurchaseBean();
			pItem.DeleteAllItem(ccid);
		}

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
