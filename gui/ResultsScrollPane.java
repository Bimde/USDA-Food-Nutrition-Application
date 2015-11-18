package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ResultsScrollPane extends JScrollPane {
	private JTable table;

	public ResultsScrollPane(JTable table) {
		super(table);
		this.setSize(new Dimension(800, 700));
		this.setBackground(Color.CYAN);
		this.table = table;
	}

	public void add(IndividualResult result) {
		System.out.println(result.getFood().getValue("Long_Desc"));
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		model.addRow(new Object[] { new JLabel("Hi") });
	}
}
