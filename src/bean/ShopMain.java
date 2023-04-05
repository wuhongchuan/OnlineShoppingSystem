package bean;

import javax.swing.*;
import java.awt.*;

// system main


public class ShopMain {
	boolean packFrame = false;
	public ShopMain(){
//		Login frame = new Login();
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

	public static void main(String[] args) {
		//设置运行风格
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ShopMain();
	}
}
