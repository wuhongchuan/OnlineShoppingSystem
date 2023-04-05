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
 * �̵�������
 * ���������̵�
 */
public class ShoppingFrame extends JFrame implements ActionListener{
	//BorderLayout���֣������Ź�����������ؿؼ����ϲ��Ų�����ť
	private JPanel jpNorth,jpNorthTop,jpNorthBottom,jpSouth;

	//��ܴ�С
	Dimension faceSize = new Dimension(400,300);
	//����ͼ��
	Image icon;
	//�����˵���
	private JMenuBar jmb;
	private JMenu jm1,jm2;
	private JComboBox jcb1;
	private JMenuItem jmi1,jmi2,jmi3;
	//��������ؿؼ�
	private JToolBar jtb;
	private JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	//���ϲ��ؼ�
	private JLabel jlQuery;
	private JTextField jtfQuery;
	private JButton jbQuery,jbFlush;
	//�в����������ؿؼ�
	private JScrollPane jsp;
	//private StuModel model;
	private JTable jtInfo;
	//��ײ��ؼ�
	private JButton jbAdd,jbUpdate,jbDelete,jbPageturn;


	//���ò�ͬ����Ա��Ϣ��ʾ���壬����ͬ����Ĳ�ѯ���ؽ����
	public static StuInfo stuInfo = new StuInfo();
	public static CourseInfo corInfo = new CourseInfo();
	public static GradeInfo grdInfo = new GradeInfo();


	//�����ʼ������
	public ShoppingFrame(){
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		
		//��ӿ�ܵĹر��¼�����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		//��ܴ�С
		this.setSize(faceSize);
		//���ñ���
		this.setTitle("�̵�������");
		//����ͼ��
		icon = getImage("pic/1.jpg");
		this.setIconImage(icon);
		
		jpNorth = new JPanel();
		jpNorth.setLayout(new BorderLayout());
		jpNorthTop = new JPanel();
		jpNorthBottom = new JPanel();
		jpSouth = new JPanel();
		//�˵���
		buildMenu();
		//�ϲ������ϲ����������������ӵ������
		buildToolbar();
		jpNorth.add(jpNorthTop, BorderLayout.NORTH);
		//�ϲ������²���ѯ���
		jlQuery = new JLabel("Input");
		jtfQuery = new JTextField(10);
		//�������ӵ���������
		String []ct = {"id","shopName","�Ա�","רҵ","ѧԺ"};
		jcb1 = new JComboBox(ct);
		jbQuery = new JButton("��ѯ");
		jbFlush = new JButton("ˢ��");
		jbQuery.addActionListener(this);
		jbFlush.addActionListener(this);
		jpNorthBottom.add(jlQuery);
		jpNorthBottom.add(jcb1);
		jpNorthBottom.add(jtfQuery);
		jpNorthBottom.add(jbQuery);
		jpNorthBottom.add(jbFlush);
		jpNorth.add(jpNorthBottom,BorderLayout.SOUTH);
		this.getContentPane().add(jpNorth, BorderLayout.NORTH);
	
		//�в��ı�����
		ShopModel model = new ShopModel();
		String sql = "select *from shop where 1=?";
		String[] params = new String[]{"1"};
		model.queryStudent(sql,params);
		jtInfo = new JTable(model);
		jtInfo.setRowHeight(25);
		jsp = new JScrollPane(jtInfo);
		this.getContentPane().add(jsp,BorderLayout.CENTER);
		
		
		//�²��������޸�ɾ���ؼ�����ͨ����ӵ����
		jbAdd = new JButton("���");
		jbUpdate = new JButton("�޸�");
		jbDelete = new JButton("ɾ��");
		jbPageturn = new JButton("��ҳ");
		jbAdd.addActionListener(this);
		jbUpdate.addActionListener(this);
		jbDelete.addActionListener(this);
		jbPageturn.addActionListener(this);
		jpSouth.add(jbAdd);
		jpSouth.add(jbUpdate);
		jpSouth.add(jbDelete);
		jpSouth.add(jbPageturn);
		this.getContentPane().add(jpSouth, BorderLayout.SOUTH);
		
		//�������������������
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
	
	//�����˵���ؿؼ�
	public void buildMenu(){
		jmb = new JMenuBar();
		jm1 = new JMenu("����");
		jm2 = new JMenu("����");
		jmi1 = new JMenuItem("���");
		jmi2 = new JMenuItem("�޸�");
		jmi3 = new JMenuItem("ɾ��");
		
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

		jb1 = new JButton("����̵�",new ImageIcon("src/pic/1.png"));
//		jb2 = new JButton("����̵�",new ImageIcon("src/pic/2.png"));
		jb3 = new JButton("ɾ���̵�",new ImageIcon("src/pic/3.png"));
//		jb4 = new JButton("ѧ��ѡ��",new ImageIcon("src/pic/4.png"));
//		jb5 = new JButton("�γ�����",new ImageIcon("src/pic/5.png"));
//		jb6 = new JButton("�γ��޸�",new ImageIcon("src/pic/6.png"));
//		jb7 = new JButton("�γ�ɾ��",new ImageIcon("src/pic/7.png"));
//		jb8 = new JButton("�ɼ�����",new ImageIcon("src/pic/8.png"));
//		jb9 = new JButton("�ɼ��޸�",new ImageIcon("src/pic/9.png"));
		jb10 = new JButton("�˳�",new ImageIcon("src/pic/10.png"));
	
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
	
	
	//�����ʼ������
	private void Init() throws Exception{
		//�رճ���ʱ�Ĳ���
		this.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			}
		);	
	}
	
	//�¼�����
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jb10){   //�˳�
			System.exit(0);
		}
		if (obj == jbQuery) {    //��ѯ
			String jtfValue = jtfQuery.getText().trim();
			//��ѯ��ʱ��������ַ���
			if(jtfValue.equals("")){
				String sql = "select * from student where 1=?";
				String[] params = new String[]{"1"};
				StuModel model = new StuModel();
				model.queryStudent(sql, params);
				jtInfo.setModel(model);
			}else{
				//ѧ�Ų�ѯ
				if((String)jcb1.getSelectedItem()=="ѧ��"){
					String sql = "select * from student where sNum=?";
					String[] params = new String[]{jtfValue};
					StuModel model = new StuModel();
					model.queryStudent(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="����"){
					String sql = "select * from student where sName=?";
					String[] params = new String[]{jtfValue};
					StuModel model = new StuModel();
					model.queryStudent(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="�Ա�"){
					String sql = "select * from student where sSex=?";
					String[] params = new String[]{jtfValue};
					StuModel model = new StuModel();
					model.queryStudent(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="רҵ"){
					String sql = "select * from student where sMajor=?";
					String[] params = new String[]{jtfValue};
					StuModel model = new StuModel();
					model.queryStudent(sql, params);
					jtInfo.setModel(model);
				}else if((String)jcb1.getSelectedItem()=="ѧԺ"){
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
			//��������ʱ���ڵ�λ��
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
			//��������ʱ���ڵ�λ��
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

		else if(obj == jb1 || obj == jbAdd){ //ѧ����Ϣ����
			AddShopInfo asi = new AddShopInfo();
			asi.downInit();
			asi.pack();
			asi.setVisible(true);
		}
//		else if (obj == jb2 || obj == jbUpdate) { //ѧ����Ϣ�޸�
//			EditStuInfo esi = new EditStuInfo();
//			esi.downInit();
//			esi.pack();
//			esi.setVisible(true);
//		}
		else if (obj == jb3 || obj == jbDelete) { //ѧ����Ϣɾ��
			DelStuInfo dsi = new DelStuInfo();
			dsi.downInit();
			dsi.pack();
			dsi.setVisible(true);
		}
//		else if (obj == jb4) { //ѧ��ѡ��
//			SelectCourse sc = new SelectCourse();
//			sc.downInit();
//			sc.pack();
//			sc.setVisible(true);
//		}
//		else if (obj == jb5) { //�γ�����
//			AddCourseInfo aci = new AddCourseInfo();
//			aci.downInit();
//			aci.pack();
//			aci.setVisible(true);
//		}
//		else if (obj == jb6) { //�γ��޸�
//			EditCourseInfo eci = new EditCourseInfo();
//			eci.downInit();
//			eci.pack();
//			eci.setVisible(true);
//		}
//		else if (obj == jb7) { //�γ�ɾ��
//			DelCourseInfo dci = new DelCourseInfo();
//			dci.downInit();
//			dci.pack();
//			dci.setVisible(true);
//		}
//		else if (obj == jb8) { //�ɼ�����
//			AddGradeInfo agi = new AddGradeInfo();
//			agi.downInit();
//			agi.pack();
//			agi.setVisible(true);
//		}
//		else if (obj == jb9) { //�ɼ��޸�
//			EditGradeInfo egi = new EditGradeInfo();
//			egi.downInit();
//			egi.pack();
//			egi.setVisible(true);
//		}



//		else if (obj == itemSearchStu) { //ѧ����ѯ
//			stuInfo.downInit();
//			stuInfo.pack();
//			stuInfo.setVisible(true);
//		}
//		else if (obj == itemSearchCor) { //ѡ�β�ѯ
//			corInfo.downInit();
//			corInfo.pack();
//			corInfo.setVisible(true);
//		}
//		else if (obj == itemSearchGrd) { //�ɼ���ѯ
//			grdInfo.downInit();
//			grdInfo.pack();
//			grdInfo.setVisible(true);
//		}
		//��ѯ���ܵ��¼�����
//		else if (obj == itemSearchStuBySnum) { //��ѧ�Ų�ѯ
//			StuSearchSnum ssSnum = new StuSearchSnum();
//			ssSnum.pack();
//			ssSnum.setVisible(true);
//		}
//		else if (obj == itemSearchStuBySname) { //��ѧ������ѯ
//			StuSearchSname ssSname = new StuSearchSname();
//			ssSname.pack();
//			ssSname.setVisible(true);
//		}
//		else if (obj == itemSearchStuBySsex) { //���Ա��ѯ
//			StuSearchSsex ssSsex = new StuSearchSsex();
//			ssSsex.pack();
//			ssSsex.setVisible(true);
//		}
//		else if (obj == itemSearchStuByScollege) { //��ѧԺ��ѯ
//			StuSearchScollege ssScollege = new StuSearchScollege();
//			ssScollege.pack();
//			ssScollege.setVisible(true);
//		}
//		else if (obj == itemSearchStuBySmajor) { //��רҵ��ѯ
//			StuSearchSmajor ssSmajor = new StuSearchSmajor();
//			ssSmajor.pack();
//			ssSmajor.setVisible(true);
//		}
//		else if (obj == itemSearchStuByCname) { //���γ�����ѯ�γ�
//			CourseSearchCname csCname = new CourseSearchCname();
//			csCname.pack();
//			csCname.setVisible(true);
//		}
//		else if (obj == itemSearchStuByCteacher) { //���ڿν�ʦ��ѯ�γ�
//			CourseSearchCteacher csCteacher = new CourseSearchCteacher();
//			csCteacher.pack();
//			csCteacher.setVisible(true);
//		}
//		else if (obj == itemSearchMutGrd) { //��ѯ���п�Ŀ�ɼ�
//			GrdSearchAllGrade gsag = new GrdSearchAllGrade();
//			gsag.pack();
//			gsag.setVisible(true);
//		}
	}
	/*
	 * ͨ���������ļ������ͼ��
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
