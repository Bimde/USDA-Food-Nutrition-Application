package database.datastrucutres;

public class FoodBinaryTree extends BinaryTree<FoodPacket> {
	
	public static final String[][] HEADERS = {
			{ "NDB_No", "FdGrp_Cd", "Long_Desc", "Short_Desc", "ComName", "ManufacName", "Survey", "Ref_desc", "Refuse",
					"SciName", "N_Factor", "Pro_Factor", "Fat_Factor", "CHO_Factor" },
			{ "NDB_No", "Nutr_No", "Nutr_Val", "Num_Data_Pts", "Std_Error", "Src_Cd", "Derriv_Cd", "Ref_NDB_No",
					"Add_Nutr_Mark", "Num_Studies", "Min", "Max", "DF", "Low_EB", "Up_EB", "Stat_cmt", "AddMod_Date",
					"CC" },
			{ "NDB_No", "Seq", "Amount", "Msre_Desc", "Gm_Wgt", "Num_Data_Pts", "Std_Dev" },
			{ "Nutr_No", "Units", "Tagname", "NutrDesc", "Num_Dec", "SR_Order" }, { "FdGrp_Cd", "FdGrp_Desc" } };

	public static final int FOOD_DES = 0, NUT_DATA = 1, WEIGHT = 2, NUTR_DEF = 3, FD_GROUP = 4;
	
	public FoodBinaryTree() {
		super();
	}

	public FoodPacket get(int key) {
		Node<FoodPacket> temp = this.head;
		while (true) {
			if (temp == null)
				return null;
			int dif = temp.getItem().compareTo(key);
			if (dif == 0)
				return temp.getItem();
			if (temp.isLeaf())
				return null;
			if (dif > 0)
				temp = temp.getLeftChild();
			else
				temp = temp.getRightChild();
		}
	}

	public LinkedList<FoodPacket> search(String query, String header) {
		LinkedList<FoodPacket> list = new LinkedList<FoodPacket>();
		if (this.internalSearch(this.head, query.toLowerCase(), header, list))
			return list;
		return null;
	}
	
	public LinkedList<FoodPacket> search(String query, String[] headers) {
		LinkedList<FoodPacket> list = new LinkedList<FoodPacket>();
		if (this.internalSearch(this.head, query.toLowerCase(), headers, list))
			return list;
		return null;
	}

	private boolean internalSearch(Node<FoodPacket> node, String query, String header, LinkedList<FoodPacket> list) {
		if (node != null) {
			FoodPacket food = node.getItem();
			boolean found = false;
			if (food.getValue(header).toLowerCase().contains(query)) {
				list.add(food);
				found = true;
			}
			boolean found1 = internalSearch(node.getLeftChild(), query, header, list),
					found2 = internalSearch(node.getRightChild(), query, header, list);
			return found || found1 || found2;
		}
		return false;
	}
	
	private boolean internalSearch(Node<FoodPacket> node, String query, String[] headers, LinkedList<FoodPacket> list) {
		if (node != null) {
			FoodPacket food = node.getItem();
			boolean found = false, found1 = false;
			for(int i = 0; !found && i < headers.length; i++)
			{
				if (food.getValue(headers[i]).toLowerCase().contains(query)) {
					list.add(food);
					found = true;
					found1 = true;
				}
			}
			boolean found2 = internalSearch(node.getLeftChild(), query, headers, list),
					found3 = internalSearch(node.getRightChild(), query, headers, list);
			return found3 || found2 || found1;
		}
		return false;
	}
}