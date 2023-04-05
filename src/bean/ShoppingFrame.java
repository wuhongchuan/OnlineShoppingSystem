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
 * 商店主界面
 * 包含多种商店
 */
public class ShoppingFrame extends JFrame implements ActionListener{
	//BorderLayout布局，北部放工具栏搜索相关控件，南部放操作按钮
	private JPanel jpNorth,jpNorthTop,jpNorthBottom,jpSouth;

	//框架大小
	Dimension faceSize = new Dimension(400,300);
	//程序图标
	Image icon;
	//建立菜单栏
	private JMenuBar jmb;
	private JMenu jm1,jm2;
	private JComboBox jcb1;
	private JMenuItem jmi1,jmi2,jmi3;
	//工具栏相关控件
	private JToolBar jtb;
	private JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	//最上部控件
	private JLabel jlQuery;
	private JTextField jtfQuery;
	private JButton jbQuery,jbFlush;
	//中部表格区域相关控件
	private JScrollPane jsp;
	//private StuModel model;
	private JTable jtInfo;
	//最底部控件
	private JButton jbAdd,jbUpdate,jbDelete,jbPageturn;


	//设置不同的人员信息显示窗体，供不同窗体的查询返回结果用
	public static StuInfo stuInfo = new StuInfo();
	public static CourseInfo corInfo = new CourseInfo();
	public static GradeInfo grdInfo = new GradeInfo();


	//程序初始化函数
	public ShoppingFrame(){
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		
		//添加框架的关闭事件处理
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		//框架大小
		this.setSize(faceSize);
		//设置标题
		this.setTitle("商店主界面");
		//程序图标
		icon = getImage("pic/1.jpg");
		this.setIconImage(icon);
		
		jpNorth = new JPanel();
		jpNorth.setLayout(new BorderLayout());
		jpNorthTop = new JPanel();
		jpNorthBottom = new JPanel();
		jpSouth = new JPanel();
		//菜单栏
		buildMenu();
		//上层面板的上部工具栏组件，并添加到面板中
		buildToolbar();
		jpNorth.add(jpNorthTop, BorderLayout.NORTH);
		//上层面板的下部查询组件
		jlQuery = new JLabel("Input");
		jtfQuery = new JTextField(10);
		//把相关添加到下拉框中
		String []ct = {"id","shopName","性别","专业","学院"};
		jcb1 = new JComboBox(ct);
		jbQuery = new JButton("查询");
		jbFlush = new JButton("刷新");
		jbQuery.addActionListener(this);
		jbFlush.addActionListener(this);
		jpNorthBottom.add(jlQuery);
		jpNorthBottom.add(jcb1);
		jpNorthBottom.add(jtfQuery);
		jpNorthBottom.add(jbQuery);
		jpNorthBottom.add(jbFlush);
		jpNorth.add(jpNorthBottom,BorderLayout.SOUTH);
		this.getContentPane().add(jpNorth, BorderLayout.NORTH);
	
		//中部的表格组件
		ShopModel model = new ShopModel();
		String sql = "select *from shop where 1=?";
		String[] params = new String[]{"1"};
		model.queryStudent(sql,params);
		jtInfo = new JTable(model);
		jtInfo.setRowHeight(25);
		jsp = new JScrollPane(jtInfo);
		this.getContentPane().add(jsp,BorderLayout.CENTER);
		
		
		//下部的增加修改删除控件，并通过添加到面板
		jbAdd = new JButton("添加");
		jbUpdate = new JButton("修改");
		jbDelete = new JButton("删除");
		jbPageturn = new JButton("翻页");
		jbAdd.addActionListener(this);
		jbUpdate.addActionListener(this);
		jbDelete.addActionListener(this);
		jbPageturn.addActionListener(this);
		jpSouth.add(jbAdd);
		jpSouth.add(jbUpdate);
		jpSouth.add(jbDelete);
		jpSouth.add(jbPageturn);
		this.getContentPane().add(jpSouth, BorderLayout.SOUTH);
		
		//整个主界面的属性设置
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
	
	//构建菜单相关控件
	public void buildMenu(){
		jmb = new JMenuBar();
		jm1 = new JMenu("管理");
		jm2 = new JMenu("帮助");
		jmi1 = new JMenuItem("添加");
		jmi2 = new JMenuItem("修改");
		jmi3 = new JMenuItem("删除");
		
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
	
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);

		jmb.add(jm1);
		jmb.add(jm2);
		this.setJMenuBar(jmb);
	}
	
	public void buildToolbar(){
		jtb = new JToolBar();
		jtb.setLocation(0,20);

		jb1 = new JButton("添加商店",new ImageIcon("src/pic/1.png"));
//		jb2 = new JButton("添加商店",new ImageIcon("src/pic/2.png"));
		jb3 = new JButton("删除商店",new ImageIcon("src/pic/3.png"));
//		jb4 = new JButton("学生选课",new ImageIcon("src/pic/4.png"));
//		jb5 = new JButton("课程增加",new ImageIcon("src/pic/5.png"));
//		jb6 = new JButton("课程修改",new ImageIcon("src/pic/6.png"));
//		jb7 = new JButton("课程删除",new ImageIcon("src/pic/7.png"));
//		jb8 = new JButton("成绩增加",new ImageIcon("src/pic/8.png"));
//		jb9 = new JButton("成绩修改",new ImageIcon("src/pic/9.png"));
		jb10 = new JButton("退出",new ImageIcon("src/pic/10.png"));
	
		jb1.addActionListener(this);
//		jb2.addActionListener(this);
		jb3.addActionListener(this);
//		jb4.addActionListener(this);
//		jb5.addActionListener(this);
//		jb6.addActionListener(this);
//		jb7.addActionListener(this);
//		jb8.addActionListener(this);
//		jb9.addActionListener(this);
		jb10.addActionListener(this);
		
		jtb.add(jb1);
//		jtb.add(jb2);
		jtb.add(jb3);
//		jtb.add(jb4);
//		jtb.add(jb5);
//		jtb.add(jb6);
//		jtb.add(jb7);
//		jtb.add(jb8);
//		jtb.add(jb9);
		jtb.add(jb10);
		jpNorthTop.add(jtb);
	}
	
	
	//程序初始化函数
	private void Init() throws Exception{
		//关闭程序时的操作
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
		if(obj == jb10){   //退出
			System.exit(0);
		}
		if (obj == jbQuery) {    //查询
			String jtfValue = jtfQuery.getText().trim();
			//查询的时候输入空字符串
			if(jtfValue.equals("")){
				String sql = "select * from student where 1=?";
				String[] params = new String[]{"1"};
				StuModel model = new StuModel();
				model.queryStudent(sql, params);
				jtInfo.setModel(model);
			}else{
				//学号查询
				if((String)jcb1.getSelectedItem()=="学号"){
					String sql = "select * from student where sNum=?";
					String[] params = new String[]{jtfValue};
					StuModel model = new StuModel();
					model.queryStudent(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="姓名"){
					String sql = "select * from student where sName=?";
					String[] params = new String[]{jtfValue};
					StuModel model = new StuModel();
					model.queryStudent(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="性别"){
					String sql = "select * from student where sSex=?";
					String[] params = new String[]{jtfValue};
					StuModel model = new StuModel();
					model.queryStudent(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="专业"){
					String sql = "select * from student where sMajor=?";
					String[] params = new String[]{jtfValue};
					StuModel model = new StuModel();
					model.queryStudent(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="学院"){
					String sql = "select * from student where sCollege=?";
					String[] params = new String[]{jtfValue};
					StuModel model = new StuModel();
					model.queryStudent(sql, params);
					jtInfo.setModel(model);
				}

			}
		}
		else if(obj == jbFlush){
			this.dispose();
			boolean packFrame = false;
			ShoppingFrame frame = new ShoppingFrame();
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
		else if(obj == jbPageturn){
			this.dispose();
			boolean packFrame = false;
			StuMain3 frame = new StuMain3();
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

		else if(obj == jb1 || obj == jbAdd){ //学生信息增加
			AddShopInfo asi = new AddShopInfo();
			asi.downInit();
			asi.pack();
			asi.setVisible(true);
		}
//		else if (obj == jb2 || obj == jbUpdate) { //学生信息修改
//			EditStuInfo esi = new EditStuInfo();
//			esi.downInit();
//			esi.pack();
//			esi.setVisible(true);
//		}
		else if (obj == jb3 || obj == jbDelete) { //学生信息删除
			DelStuInfo dsi = new DelStuInfo();
			dsi.downInit();
			dsi.pack();
			dsi.setVisible(true);
		}
//		else if (obj == jb4) { //学生选课
//			SelectCourse sc = new SelectCourse();
//			sc.downInit();
//			sc.pack();
//			sc.setVisible(true);
//		}
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



//		else if (obj == itemSearchStu) { //学生查询
//			stuInfo.downInit();
//			stuInfo.pack();
//			stuInfo.setVisible(true);
//		}
//		else if (obj == itemSearchCor) { //选课查询
//			corInfo.downInit();
//			corInfo.pack();
//			corInfo.setVisible(true);
//		}
//		else if (obj == itemSearchGrd) { //成绩查询
//			grdInfo.downInit();
//			grdInfo.pack();
//			grdInfo.setVisible(true);
//		}
		//查询功能的事件处理
//		else if (obj == itemSearchStuBySnum) { //按学号查询
//			StuSearchSnum ssSnum = new StuSearchSnum();
//			ssSnum.pack();
//			ssSnum.setVisible(true);
//		}
//		else if (obj == itemSearchStuBySname) { //按学姓名查询
//			StuSearchSname ssSname = new StuSearchSname();
//			ssSname.pack();
//			ssSname.setVisible(true);
//		}
//		else if (obj == itemSearchStuBySsex) { //按性别查询
//			StuSearchSsex ssSsex = new StuSearchSsex();
//			ssSsex.pack();
//			ssSsex.setVisible(true);
//		}
//		else if (obj == itemSearchStuByScollege) { //按学院查询
//			StuSearchScollege ssScollege = new StuSearchScollege();
//			ssScollege.pack();
//			ssScollege.setVisible(true);
//		}
//		else if (obj == itemSearchStuBySmajor) { //按专业查询
//			StuSearchSmajor ssSmajor = new StuSearchSmajor();
//			ssSmajor.pack();
//			ssSmajor.setVisible(true);
//		}
//		else if (obj == itemSearchStuByCname) { //按课程名查询课程
//			CourseSearchCname csCname = new CourseSearchCname();
//			csCname.pack();
//			csCname.setVisible(true);
//		}
//		else if (obj == itemSearchStuByCteacher) { //按授课教师查询课程
//			CourseSearchCteacher csCteacher = new CourseSearchCteacher();
//			csCteacher.pack();
//			csCteacher.setVisible(true);
//		}
//		else if (obj == itemSearchMutGrd) { //查询所有科目成绩
//			GrdSearchAllGrade gsag = new GrdSearchAllGrade();
//			gsag.pack();
//			gsag.setVisible(true);
//		}
	}
	/*
	 * 通过给定的文件名获得图像
	 * */
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
