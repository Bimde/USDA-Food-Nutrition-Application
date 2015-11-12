package database.datastrucutres;

public class Nutrient{

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

	public int getKey() {
		return this.key;
	}
}
