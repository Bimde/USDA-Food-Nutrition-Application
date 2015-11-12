package database.datastrucutres;

/**
 * Data packet containing one food item and all associated data references
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class FoodPacket implements Comparable<FoodPacket> {

	private BinaryTree<Head> values;
	private int key;
	private NutrientList nutrientList;
	private String footNotes;

	public FoodPacket(String[] data, String[] headers) {
		this.addData(data, headers);
		this.key = Integer.parseInt(data[0]);
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
		return this.values.get(new Head(header, "")).getValue();
	}

	public boolean setValue(String header, String value) {
		Head temp = this.values.get(new Head(header, ""));
		if (temp == null)
			return false;
		temp.setValue(value);
		return true;
	}

	public String toString() {
		return this.key + "";
	}

	public void addData(String[] data, String[] headers) {
		this.values = new BinaryTree<Head>();
		for (int i = 0; i < headers.length; i++) {
			this.values.add(new Head(headers[i], data[i]));
		}
	}

	public void addNutrients(NutrientList nutrients) {
		this.nutrientList = nutrients;
	}

	public NutrientList getNutrients() {
		return this.nutrientList;
	}
	
	public void addFootNote(String note) {
		if(this.footNotes == null)
			this.footNotes = note;
		else
			this.footNotes += "\n"+note;
	}
	
	public String getFootNotes()
	{
		if(this.footNotes == null)
			return "";
		return this.footNotes;
	}
}
