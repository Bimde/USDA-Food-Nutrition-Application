package database.datastrucutres;

public class NutrientList extends LinkedList<Nutrient> {

	public NutrientList() {
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

	public Nutrient get(String header) {
		ListNode<Nutrient> temp = this.head;
		while (temp != null) {
			if (temp.getItem().getValue("Nutr_No").equals(header))
				return temp.getItem();
			temp = temp.getNext();
		}
		return null;
	}
}
