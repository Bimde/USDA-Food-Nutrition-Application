package database;

import database.datastrucutres.ListNode;

public class NutrientList {
	private ListNode<Nutrient> start, end;

	public NutrientList() {
	}
	
	public NutrientList(String[] headers, String[] values) {
		
	}

	public Nutrient get(int key) {
		ListNode<Nutrient> temp = start;
		while(temp != null)
		{
			if(temp.getItem().getKey() == key)
				return temp.getItem();
			temp = temp.getNext();
		}
		return null;
	}
	
	public Nutrient get(String header) {
		ListNode<Nutrient> temp = start;
		while(temp != null)
		{
			if(temp.getItem().getValue("Nutr_No").equals(header))
				return temp.getItem();
			temp = temp.getNext();
		}
		return null;
	}

	public void add(Nutrient value) {
		ListNode<Nutrient> node = new ListNode<Nutrient>(value);
		if (this.start == null)
			this.start = node;
		else
			this.end.setNext(node);
		this.end = node;
	}

	public int size() {
		int temp = 0;
		ListNode<Nutrient> temp1 = this.start;
		while (temp1.getNext() != null) {
			temp1 = temp1.getNext();
			temp++;
		}
		return temp;
	}
}
