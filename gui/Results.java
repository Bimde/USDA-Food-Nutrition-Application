package tempgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.datastrucutres.FoodPacket;
import database.datastrucutres.FoodPacketList;
import database.datastrucutres.LinkedList;

public class Results extends JPanel {
	Main mainPanel;
	SearchBar searchBar;
	ResultsScrollPane scrollPane;
	LinkedList<IndividualResult> results;
	GridBagConstraints constraints;

	public Results(Main mainPanel) {
		super(new GridBagLayout());

		this.mainPanel = mainPanel;
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(900, 600));

		this.constraints = new GridBagConstraints();

		this.searchBar = new SearchBar(mainPanel);
		constraints.gridx = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.gridy = 0;
		constraints.weighty = 0.5;
		constraints.gridheight = 1;
		constraints.ipadx = 10;
		constraints.ipady = 10;
		this.add(this.searchBar, this.constraints);

		this.scrollPane = new ResultsScrollPane(new JTable(new DefaultTableModel()));
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridy = 1;
		constraints.gridheight = 2;
		constraints.weighty = 1;
		constraints.ipadx = 10;
		constraints.ipady = 10;
		this.add(this.scrollPane, this.constraints);
	}

	public void loadResults(FoodPacketList search) {
		FoodPacket[] array = search.toArray();
		this.results = new LinkedList<IndividualResult>();
		if (array != null) {
			int length = Math.min(Main.NO_OF_SEARCH_RESULTS, array.length);
			for (int i = 0; i < length; i++) {
				FoodPacket temp = array[i];
				IndividualResult result = new IndividualResult(temp);
				this.scrollPane.add(result);
			}
		}
	}

	public void internallyRequestFocus() {
		this.requestFocusInWindow();
		this.searchBar.requestFocusInWindow();
	}
}
