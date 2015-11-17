package gui;

public abstract class Updatable extends Thread {
	private long updates;

	public Updatable(long updates) {
		this.updates = updates;
	}

	public long getUpdates() {
		return this.updates;
	}
}
