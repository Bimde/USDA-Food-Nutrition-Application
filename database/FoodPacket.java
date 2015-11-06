package database;

import database.datastrucutres.BinaryTree;

public class FoodPacket implements Comparable<FoodPacket> {

	public static final String[][] HEADERS = {{"NDB_No", "FdGrp_Cd", "Long_Desc", "Short_Desc", "ComName", "ManufacName", "Survey", "Ref_desc", "Refuse", "SciName", "N_Factor", "Pro_Factor", "Fat_Factor", "CHO_Factor"}, {}};
	public static final int FOOD_DES = 0;
	
	private String[] data, headers;
	private BinaryTree<Head> keys;
	private int key;

	public FoodPacket(String[] data, String[] headers) {
		this.data = data;
		this.headers = headers;
		this.addKeys();
		this.setKey(Integer.parseInt(data[0]));
	}

	@Override
	public int compareTo(FoodPacket other) {
		return other.getKey() - this.key;
	}
	
	public int compareTo(int key) {
		return key - this.key;
	}

	public int getKey() {
		return this.key;
	}

	public String getValue(String header) {
		return this.keys.get(new Head(header, "")).getValue();
	}
	
	public String toString()
	{
		return this.key+"";
	}

	private void addKeys() {
		this.keys = new BinaryTree<Head>();
		for (int i = 0; i < headers.length; i++) {
			keys.add(new Head(this.headers[i], this.data[i]));
		}
		this.data = null;
		this.headers = null;
	}

	private void setKey(int key) {
		this.key = key;
	}
}
