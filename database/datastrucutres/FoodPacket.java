package database.datastrucutres;

/**
 * Data packet containing one food item and all associated data references
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class FoodPacket implements Comparable<FoodPacket> {

	private SearchableLinkedList values;
	private int key, fileNo;
	private NutrientList nutrientList;
	private LinkedList<String> footNotes, languals;

	public FoodPacket(String[] data, int fileNo) {
		this.addData(data);
		this.key = Integer.parseInt(data[0]);
		this.footNotes = new LinkedList<String>();
		this.languals = new LinkedList<String>();
		this.fileNo = fileNo;
	}

	@Override
	public int compareTo(FoodPacket other) {
		return other.getKey() - this.key;
	}

	public int compareTo(int key) {
		return key - this.key;
	}

	public int getKey() {
		return this.key;
	}

	public String getValue(String header) {
		return this.values.get(header);
	}

	public boolean setValue(String header, String value) {
		return this.values.set(header, value);
	}

	public String toString() {
		return this.key + "";
	}

	public void addData(String[] data) {
		this.values = new SearchableLinkedList();
		for (int i = 0; i < data.length; i++) {
			this.values.add(new Head(i, data[i], this.fileNo));
		}
	}

	public void addNutrients(NutrientList nutrients) {
		this.nutrientList = nutrients;
	}

	public NutrientList getNutrients() {
		return this.nutrientList;
	}

	public void addFootNote(String note) {
		this.footNotes.add(note);
	}

	public String[] getFootNotes() {
		return this.footNotes.toArray();
	}

	public void addLanguals(String data) {
		this.languals.add(data);
	}

	public String[] getLanguals() {
		return this.languals.toArray();
	}
}
