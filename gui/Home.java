package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class Home extends JPanel {
	Main mainPanel;
	SearchBar searchBar;

	public Home(Main mainPanel) {
		super(new GridBagLayout());

		this.mainPanel = mainPanel;

		this.setPreferredSize(new Dimension(900, 600));
		this.setBackground(Color.GRAY);

		this.searchBar = new SearchBar(mainPanel);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridwidth = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.ipadx = 10;
		constraints.ipady = 10;

		this.add(searchBar, constraints);
	}
	
	public void internallyRequestFocus() {
		this.requestFocusInWindow();
		this.searchBar.requestFocusInWindow();
	}
}