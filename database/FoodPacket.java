package database;

import database.datastrucutres.BinaryTree;

public class FoodPacket implements Comparable<FoodPacket> {

	private String[] data;
	private BinaryTree<Head> keys;
	private int key;

	public FoodPacket(String[] data) {
		this.data = data;
		this.addKeys();
	}

	@Override
	public int compareTo(FoodPacket other) {
		return 0;
	}

	public int getKey() {
		return this.key;
	}

	public String getValue(String key) {
		return this.data[this.keys.get(new Head(key, 0)).getKey()];
	}

	private void addKeys() {
		this.keys = new BinaryTree<Head>();
		for (int i = 0; i < data.length; i++) {
			keys.add(new Head(this.data[i], i));
		}
	}
}
