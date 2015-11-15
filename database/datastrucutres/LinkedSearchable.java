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

	private String value;
	private int file, header;

	public LinkedSearchable(int header, String value, int file) {
		this.value = value;
		this.header = header;
		this.file = file;
	}

	public String getValue() {
		return this.value;
	}

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
			return FoodPacketBinaryTree.HEADERS[this.file][this.header];
		} catch (Exception e) {
			return "";
		}
	}

	public int getHeaderNo() {
		return this.header;
	}

	@Override
	public int compareTo(LinkedSearchable other) {
		if (this.header < 0)
			return 1;
		else if (other.getHeaderNo() < 0)
			return -1;
		return this.getHeader().compareTo(other.getHeader());
	}
}
