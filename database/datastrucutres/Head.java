package database.datastrucutres;

class Head implements Comparable<Head> {

	private String value, header;

	public Head(String header, String value) {
		this.value = value;
		this.header = header;
	}

	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getHeader() {
		return this.header;
	}

	@Override
	public int compareTo(Head other) {
		if (this.header == null)
			return 1;
		else if (other.getHeader() == null)
			return -1;
		return this.header.compareTo(other.getHeader());
	}
}
