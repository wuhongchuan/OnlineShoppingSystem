package bean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
 * 添加新的商店信息
 * */

public class AddShopInfo extends ShopInfo{

	ShopBean getShopInfo = new ShopBean();

	public AddShopInfo(){
		this.setTitle("添加商店信息");
		this.setResizable(false);

		shopId.setEditable(true);
		shopName.setEditable(true);
		shopRating.setEditable(true);
		shopLocation.setEditable(true);

		//设置运行时窗口的位置
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - 400) / 2, 
				(screenSize.height - 300) / 2 + 45);
		

		ImageIcon img = new ImageIcon("pic/2.jpg");
		//要设置的背景图片
		JLabel imgLabel = new JLabel(img);
		//将背景图放在标签里。
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		//将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// 设置背景标签的位置
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false); 
		// 将内容面板设为透明。将LayeredPane面板中的背景显示出来。
		contain.setLayout(new FlowLayout());
	}


	public void downInit(){
		addInfo.setText("增加");
		addInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(addInfo);
		clearInfo.setText("清空");
		clearInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(clearInfo);
		exitInfo.setText("退出");
		exitInfo.setFont(new Font("Dialog",0,12));
		downPanel.add(exitInfo);
		
		//添加事件监听
		addInfo.addActionListener(this);
		clearInfo.addActionListener(this);
		exitInfo.addActionListener(this);

		this.contentPane.add(downPanel,BorderLayout.SOUTH);
	}
	
	
	//事件处理
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == exitInfo){  //退出
			this.dispose();
		}
		else if(obj == addInfo){   //增加
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
		else if (obj == clearInfo) { //清空
			setNull();
		}
	}	
}