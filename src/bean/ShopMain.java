package bean;

import javax.swing.*;
import java.awt.*;

// system main


public class ShopMain {
	boolean packFrame = false;
	static Integer ccid = 0;

	public ShopMain(Integer cid){
		this.ccid = cid;
		ShoppingFrame frame = new ShoppingFrame(cid);

		if(packFrame){
			frame.pack();
		}else{
			frame.validate();
		}

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

//	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		new ShopMain(ccid);
//	}

}
