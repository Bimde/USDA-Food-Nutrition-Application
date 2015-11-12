package database.datastrucutres;

/**
 * Data storage class to store nutrient information for individual foods in most efficient way
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class Nutrient {

	private String[] data;
	private int key;

	public Nutrient(String[] nutrients) {
		this.data = nutrients;
		this.key = Integer.parseInt(this.data[0]);
	}

	public String getValue(String header) {
		for (int i = 0; i < data.length; i++) {
			if (FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.NUT_DATA][i].equals(header))
				return this.data[i];
		}
		return "";
	}

	public int getKey() {
		return this.key;
	}
}
