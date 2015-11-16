package database.datastrucutres;

public abstract class Searchable implements Comparable<Searchable> {

	/**
	 * Value linked to the specified header
	 */
	protected String value;

	abstract public String getHeader();

	@Override
	abstract public int compareTo(Searchable other);

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
