package database.datastrucutres;

/**
 * Data storage class created to be an 'easy-sorting' solution for balanced
 * binary trees
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class Head implements Comparable<Head> {

	private String value;
	private int file, header;

	public Head(int header, String value, int file) {
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

	public String getHeader() {
		if (this.header < 0)
			return "";
		return FoodPacketBinaryTree.HEADERS[this.file][this.header];
	}

	public int getHeaderNo() {
		return this.header;
	}

	@Override
	public int compareTo(Head other) {
		if (this.header < 0)
			return 1;
		else if (other.getHeaderNo() < 0)
			return -1;
		return this.getHeader().compareTo(other.getHeader());
	}
}
