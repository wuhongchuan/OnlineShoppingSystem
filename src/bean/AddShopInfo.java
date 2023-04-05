package bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
 * ����µ��̵���Ϣ
 * */

public class AddShopInfo extends ShopInfo{

	ShopBean getShopInfo = new ShopBean();

	public AddShopInfo(){
		this.setTitle("����̵���Ϣ");
		this.setResizable(false);

		shopId.setEditable(true);
		shopName.setEditable(true);
		shopRating.setEditable(true);
		shopLocation.setEditable(true);

		//��������ʱ���ڵ�λ��
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - 400) / 2, 
				(screenSize.height - 300) / 2 + 45);
		

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


	public void downInit(){
		addInfo.setText("����");
		addInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(addInfo);
		clearInfo.setText("���");
		clearInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(clearInfo);
		exitInfo.setText("�˳�");
		exitInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(exitInfo);
		
		//����¼�����
		addInfo.addActionListener(this);
		clearInfo.addActionListener(this);
		exitInfo.addActionListener(this);

		this.contentPane.add(downPanel,BorderLayout.SOUTH);
	}
	
	
	//�¼�����
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == exitInfo){  //�˳�
			this.dispose();
		}
		else if(obj == addInfo){   //����
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
			
			AddShopInfo asi = new AddShopInfo();
			asi.downInit();
			asi.pack();
			asi.setVisible(true);
	
		}
		else if (obj == clearInfo) { //���
			setNull();
		}
	}	
}