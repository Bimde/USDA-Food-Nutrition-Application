package tempgui;

import java.awt.Container;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame() {
		super("Nutrition Database");
		Main temp = new Main();
		this.getContentPane().add(temp);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
