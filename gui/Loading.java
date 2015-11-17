package gui;

import database.Database;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Loading extends JPanel {
	private Main mainPanel;
	private Database database;
	private JLabel label;

	public Loading(Main mainPanel) {
		this.mainPanel = mainPanel;
		this.database = mainPanel.getDatabase();
		setPreferredSize(new Dimension(900, 600));
		setBackground(Color.LIGHT_GRAY);
		this.label = new JLabel();
		this.label.setFont(new Font("Serif", 0, 24));
		this.add(this.label);
		this.displayLoadMessages();
	}

	private void displayLoadMessages() {
		new Thread() {
			public void run() {
				while (!Loading.this.database.isLoaded()) {
					Loading.this.label.setText(Loading.this.database
							.getLoadingMessage());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Loading.this.mainPanel.doneLoading();
			}
		}.start();
	}
}
