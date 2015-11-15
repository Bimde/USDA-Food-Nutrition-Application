package database.datastrucutres;

/**
 * Data storage class which links a specified header to a specified value, the
 * header value being an integer reference to the header in the
 * FoodPacketBinaryTree.HEADERS array to save space and prevent duplicate data
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class LinkedSearchable implements Comparable<LinkedSearchable> {

	/**
	 * Value linked to the specified header
	 */
	private String value;

	/**
	 * The reference to the index of the array containing the header (in the
	 * FoodPacketBinaryTree.HEADERS 2-D array)
	 */
	private int fileNo;

	/**
	 * The index of the header in the array of headers for the specific fileNo,
	 * obtainable using the 'fileNo' variable for the first index. For example:
	 * 'FoodPacketBinaryTree.HEADERS[fileNo][header]' is where the specified
	 * header is stored
	 */
	private int header;

	/**
	 * Creates a data storage object with a reference to the header (to save
	 * memory) and a desired value to be linked to it
	 * 
	 * @param header
	 *            Name of field
	 * @param value
	 *            Value to link the header to
	 * @param fileNo
	 *            index of array where header information is stored (obtainable
	 *            using fileNo name, for example: for fileNo 'FOOD_DES.txt', use
	 *            variable FoodPacketBinaryTree.FOOD_DES as the fileNo number)
	 */
	public LinkedSearchable(int header, String value, int fileNo) {
		this.value = value;
		this.header = header;
		this.fileNo = fileNo;
	}

	/**
	 * Getter for the contained value
	 * 
	 * @return contained value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the contained value to a new string
	 * 
	 * @param value
	 *            New value to be associated with the contained header
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Finds header referenced by the contained header number
	 * 
	 * @return header referenced by the contained header number, or a BLANK
	 *         STRING if not found
	 */
	public String getHeader() {
		try {
			return FoodPacketBinaryTree.HEADERS[this.fileNo][this.header];
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Getter for the header reference number
	 * 
	 * @return the index position in the specified header array for where this
	 *         object's header is stored
	 */
	public int getHeaderNo() {
		return this.header;
	}

	/**
	 * Returns the compareTo() value of the two (string) headers
	 */
	@Override
	public int compareTo(LinkedSearchable other) {
		if (this.header < 0)
			return 1;
		else if (other.getHeaderNo() < 0)
			return -1;
		return this.getHeader().compareTo(other.getHeader());
	}
}
