package gui;

import java.awt.CardLayout;

import javax.swing.JPanel;

import database.Database;

public class Main extends JPanel {
	public static final String HOME_PANEL = "home";
	public static final String RESULTS_PANEL = "results";
	public static final String LOADING_PANEL = "loading";
	public static final int SEARCH_DELAY = 500;
	public static final int NO_OF_SEARCH_RESULTS = 25;
	private Home home;
	private JPanel loading;
	private Results results;
	private Database database;

	public Main() {
		super(new CardLayout());

		this.database = new Database();

		this.home = new Home(this);
		this.results = new Results(this);
		this.loading = new Loading(this);

		this.add(this.home, HOME_PANEL);
		this.add(this.results, RESULTS_PANEL);
		this.add(this.loading, LOADING_PANEL);

		this.showPanel(LOADING_PANEL);
	}

	public void showPanel(String panel) {
		CardLayout layout = (CardLayout) this.getLayout();
		layout.show(this, panel);
	}

	public Database getDatabase() {
		return this.database;
	}

	public void showResults(String query) {
		this.results.loadResults(this.database.search(query));
		this.showPanel(RESULTS_PANEL);
		this.results.internallyRequestFocus();
	}

	public void doneLoading() {
		this.showPanel(HOME_PANEL);
		this.home.internallyRequestFocus();
	}
}
