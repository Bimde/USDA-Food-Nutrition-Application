package database;

public class Nutrient implements KeyCompare<Nutrient> {

	private String[] data;
	private int key;

	public Nutrient(String[] nutrients) {
		this.data = nutrients;
		this.key = Integer.parseInt(this.data[0]);
	}

	public String getValue(String header) {
		for (int i = 0; i < data.length; i++) {
			if (FoodPacket.HEADERS[FoodPacket.NUT_DATA][i].equals(header))
				return this.data[i];
		}
		return "";
	}

	@Override
	public int compareTo(Nutrient other) {
		return other.getKey() - this.key;
	}

	@Override
	public int compareTo(int key) {
		return key - this.key;
	}

	public int getKey() {
		return this.key;
	}
}
