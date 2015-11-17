package database.datastrucutres;

/**
 * Data storage class to store nutrient information for individual foods in most
 * efficient way
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class Nutrient implements Comparable<Nutrient> {

	/**
	 * Array of data with indices associated with the headers at the same
	 * indices
	 */
	private String[] data;

	/**
	 * Primary key from the 'NUTR_DEF.txt' file
	 */
	private int key;

	/**
	 * Creates a new Nutrient object
	 * 
	 * @param nutrients
	 */
	public Nutrient(String[] nutrients) {
		this.data = nutrients;
		this.key = Integer.parseInt(this.data[0]);
	}

	/**
	 * Returns the string value associated with the specified header
	 * 
	 * @param header
	 *            Obtainable from
	 *            'FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.NUT_DATA]',
	 *            which contains all possible header options
	 * @return String value associated with the specified header or a BLANK
	 *         STRING if not found
	 */
	public String getValue(String header) {
		for (int i = 0; i < this.data.length; i++) {
			if (FoodPacketBinaryTree.HEADERS[FoodPacketBinaryTree.NUT_DATA][i]
					.equals(header))
				return this.data[i];
		}
		return "";
	}

	/**
	 * Getter for the primary key for the Nutrient object
	 * 
	 * @return the primary key from the 'NUTR_DEF.txt' file
	 */
	public int getKey() {
		return this.key;
	}

	/**
	 * Compares the primary keys of the Nutrient objetcs, returning a negative
	 * number if this object's key is greater than the object which was passed a
	 * a parameter's key
	 */
	@Override
	public int compareTo(Nutrient other) {
		return other.getKey() - this.key;
	}

	/**
	 * Returns the associated primary key
	 */
	@Override
	public String toString() {
		return this.key + "";
	}
}
