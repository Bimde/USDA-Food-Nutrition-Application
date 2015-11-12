package database.datastrucutres;

public class NutrientList extends LinkedList<Nutrient> {
	public NutrientList() {
		super();
	}

	public Nutrient get(int key) {
		ListNode<Nutrient> temp = this.head;
		while (temp != null) {
			if (temp.getItem().getKey() == key)
				return temp.getItem();
			temp = temp.getNext();
		}
		return null;
	}

	public Nutrient get(String key) {
		ListNode<Nutrient> temp = this.head;
		while (temp != null) {
			if (temp.getItem().getValue("Nutr_No").equals(key))
				return temp.getItem();
			temp = temp.getNext();
		}
		return null;
	}
	
	public boolean contains(int key){
		return this.get(key) != null;
	}
	
	public boolean contains(String key){
		return this.get(key) != null;
	}
}
