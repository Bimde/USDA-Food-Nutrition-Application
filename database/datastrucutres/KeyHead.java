package database.datastrucutres;

public class KeyHead extends Head{

	private String header;
	
	public KeyHead(String header, int fileNo) {
		super(0, "", fileNo);
		this.header = header;
	}
	
	public KeyHead(String header, String value, int fileNo) {
		super(0, value, fileNo);
		this.header = header;
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
