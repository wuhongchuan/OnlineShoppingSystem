package bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.net.URLClassLoader;

/*
 * �ṩ�����棬��������̳�
 * */

public class ShopInfo extends JFrame implements ActionListener{

	Container contentPane;
	JPanel centerPanel = new JPanel();
	JPanel upPanel = new JPanel();
	JPanel downPanel = new JPanel();
	//��ܵĴ�С
	Dimension faceSize = new Dimension(800,500);
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	JLabel jLabel7 = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel jLabel9 = new JLabel();

	JTextField shopId = new JTextField(15);
	JTextField shopName = new JTextField(15);
	JTextField shopRating = new JTextField(15);
	JTextField shopLocation = new JTextField(15);

	JButton searchInfo = new JButton();
	JButton addInfo = new JButton();
	JButton modifyInfo = new JButton();
	JButton deleteInfo = new JButton();
	JButton clearInfo = new JButton();
	JButton saveInfo = new JButton();
	JButton exitInfo = new JButton();


	GridBagLayout girdBag = new GridBagLayout();
	GridBagConstraints girdBagCon;

	public ShopInfo(){
		//���ÿ�ܵĴ�С
		this.setSize(faceSize);
		//���ñ���
		this.setTitle("ѧ���ۺ���Ϣ����");
		this.setResizable(false);
		//���ó���ͼ��
		this.setIconImage(getImage("pic/1.jpg"));
		try	{
			Init();
		}
		catch(Exception	e) {
			e.printStackTrace();
		}
		ImageIcon img = new ImageIcon("pic/2.jpg");
		//Ҫ���õı���ͼƬ
		JLabel imgLabel = new JLabel(img);
		//������ͼ���ڱ�ǩ�
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		//��������ǩ��ӵ�jfram��LayeredPane����
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// ���ñ�����ǩ��λ��
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false); 
		// �����������Ϊ͸������LayeredPane����еı�����ʾ������
		contain.setLayout(new FlowLayout());
	}
	
	public void Init() throws Exception{
		contentPane =  this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		//�в����Ĳ���
		centerPanel.setLayout(girdBag);
		
		jLabel1.setText("ShopId��");
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
		
		jLabel2.setText("ShopName��");
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
		

		jLabel3.setText("ShopRating��");
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
		
		
		jLabel4.setText("ShopLocation��");
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

		shopId.setEditable(false);
		shopName.setEditable(false);
		shopRating.setEditable(false);
		shopLocation.setEditable(false);
	}
	
	/**
	 * �²����Ĳ���
	 */
	public void downInit(){
		searchInfo.setText("��ѯ");
		searchInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(searchInfo);
		addInfo.setText("����");
		addInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(addInfo);
		modifyInfo.setText("�޸�");
		modifyInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(modifyInfo);
		deleteInfo.setText("ɾ��");
		deleteInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(deleteInfo);
		saveInfo.setText("����");
		saveInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(saveInfo);
		clearInfo.setText("���");
		clearInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(clearInfo);
		exitInfo.setText("�˳�");
		exitInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(exitInfo);

		contentPane.add(downPanel,BorderLayout.SOUTH);

		//����¼�����
		searchInfo.addActionListener(this);
		addInfo.addActionListener(this);
		modifyInfo.addActionListener(this);
		deleteInfo.addActionListener(this);
		saveInfo.addActionListener(this);
		clearInfo.addActionListener(this);
		exitInfo.addActionListener(this);

		modifyInfo.setEnabled(false);
		deleteInfo.setEnabled(false);
		saveInfo.setEnabled(false);
		clearInfo.setEnabled(false);
	}

	
	
	//�¼�����
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == searchInfo){   //��ѯ
		}
		else if(obj == addInfo){   //����
		}
		else if(obj == modifyInfo){   //�޸�
		}
		else if(obj == deleteInfo){   //ɾ��
		}
		else if(obj == saveInfo){   //����
		}
		else if(obj == clearInfo){   //���
			setNull();
		}
		else if (obj == exitInfo) { //�˳�
			this.dispose();
		}
	}

	//���ı������
	void setNull(){
		shopId.setText(null);
		shopName.setText(null);
		shopRating.setText(null);
		shopLocation.setText(null);
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
