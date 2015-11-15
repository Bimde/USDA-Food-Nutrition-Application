package database.datastrucutres;

/**
 * *** NOT RECOMENDED FOR MASS STORAGE ***: due to object STORING HEADER VALUE
 * (duplicate values), unlike the 'LinkedSearchable' class.<br>
 * Data storage class which links a specified header to a specified value,
 * useful for storing temporary or small values where public static array of the
 * headers aren't available, or for SEARCHING for LinkedSearchable objects, as
 * only the headers are compared in the compareTo method, this means a
 * LinkedSearchable object with a reference to header x will equal a
 * IndependantSearchable object with x as the header
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class IndependantSearchable extends LinkedSearchable {

	/**
	 * The header value to store
	 */
	private String header;

	/**
	 * Creates a new LinkedSearchable object with no value, 0 for the file
	 * number and overwrites the header reference with the specified header
	 * value
	 * 
	 * @param header
	 *            The header value to store
	 */
	public IndependantSearchable(String header) {
		super(0, "", 0);
		this.header = header;
	}

	/**
	 * Creates a new LinkedSearchable object with the specified value and 0 for
	 * the file number, but overwrites the header reference with the specified
	 * header value
	 * 
	 * @param header
	 * @param value
	 * @param fileNo
	 */
	public IndependantSearchable(String header, String value) {
		super(0, value, 0);
		this.header = header;
	}

	/**
	 * Returns the header value instead of getting the header from the reference
	 */
	@Override
	public String getHeader() {
		return this.header;
	}

	/**
	 * Compares the header value instead of the getting the header using the
	 * reference
	 */
	@Override
	public int compareTo(LinkedSearchable other) {
		if (this.header == null)
			return 1;
		else if (other.getHeader() == null)
			return -1;
		return this.header.compareTo(other.getHeader());
	}

}
