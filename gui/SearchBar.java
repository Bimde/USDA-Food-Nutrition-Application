package gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchBar extends JTextField implements DocumentListener {
	private long updated;
	private Main mainPanel;

	public SearchBar(Main mainPanel) {
		this.mainPanel = mainPanel;
		setPreferredSize(new Dimension(600, 50));
		setFont(new Font("Serif", 0, 36));
		getDocument().addDocumentListener(this);
		this.updated = 0;
	}

	public synchronized long getLastUpdated() {
		return this.updated;
	}

	private void update() {
		this.updated += 1;
		if (Integer.MAX_VALUE - this.updated < 2) {
			this.updated = 0;
		}
		new Updatable(this.updated) {
			public void run() {
				try {
					Thread.sleep(500);
					if (getUpdates() == SearchBar.this.getLastUpdated()) {
						SearchBar.this.mainPanel.showResults(SearchBar.this.getText());
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void changedUpdate(DocumentEvent arg0) {
		update();
	}

	public void insertUpdate(DocumentEvent arg0) {
		update();
	}

	public void removeUpdate(DocumentEvent arg0) {
		update();
	}
}
