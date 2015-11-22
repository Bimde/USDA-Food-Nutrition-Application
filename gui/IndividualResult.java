package tempgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import database.datastrucutres.FoodPacket;

public class IndividualResult extends JPanel {
	private FoodPacket food;
	private JLabel label;

	public IndividualResult(FoodPacket food) {
		super();
		this.food = food;
		this.setPreferredSize(new Dimension(600, 50));
		this.setBackground(Color.CYAN);
		this.loadData();
	}

	private void loadData() {
		System.out.println(this.food);
		this.label = new JLabel(this.food.getValue("Long_Desc"));
		this.label.setFont(new Font("Serif", 0, 24));
		this.add(this.label);
	}

	public FoodPacket getFood() {
		return this.food;
	}
}
