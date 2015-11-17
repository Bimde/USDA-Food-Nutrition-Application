package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ResultsScrollPane extends ScrollPane {
	private JTable table;

	public ResultsScrollPane() {
		super();
		this.setSize(new Dimension(800, 700));
		this.setBackground(Color.CYAN);
		this.table = new JTable(new DefaultTableModel());
		this.add(this.table);
	}

	public void add(IndividualResult result) {
		System.out.println(result.getFood().getValue("Long_Desc"));
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		model.addRow(new Object[]{new JLabel(result.getFood().getValue("Long_Desc"))});
	}
}
