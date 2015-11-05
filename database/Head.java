package database;

public class Head implements Comparable<Head>{

	private String name;
	private int key;

	public Head(String name, int key) {
		this.name = name;
		this.key = key;
	}

	public int getKey() {
		return this.key;
	}
	
	public String getName () {
		return this.name;
	}

	@Override
	public int compareTo(Head other) {
		return this.name.compareTo(other.getName());
	}
}
