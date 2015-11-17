package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import database.datastrucutres.FoodPacket;
import database.datastrucutres.FoodPacketList;

public class Results extends JPanel {
	Main mainPanel;

	public Results(Main mainPanel) {
		super(new GridBagLayout());

		this.mainPanel = mainPanel;

		setPreferredSize(new Dimension(900, 600));

		SearchBar searchBar = new SearchBar(mainPanel);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridwidth = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.ipadx = 10;
		constraints.ipady = 10;

		this.add(searchBar, constraints);
	}

	public void loadResults(FoodPacketList search) {
		FoodPacket[] array = search.toArray();
		if (array != null) {
			for (FoodPacket i : array)
				System.out.println(i);
		}
	}

}
