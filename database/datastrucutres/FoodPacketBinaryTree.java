package database.datastrucutres;

import java.util.Arrays;

/**
 * Binary Tree containing only FoodPacket objects and FoodPacket-related methods
 * 
 * @author Bimesh De Silva
 * @version Final (November 2015)
 *
 */
public class FoodPacketBinaryTree extends BinaryTree<FoodPacket> {

	public static final String[][] HEADERS = {
			{ "NDB_No", "FdGrp_Cd", "Long_Desc", "Short_Desc", "ComName", "ManufacName", "Survey", "Ref_desc", "Refuse",
					"SciName", "N_Factor", "Pro_Factor", "Fat_Factor", "CHO_Factor" },
			{ "NDB_No", "Nutr_No", "Nutr_Val", "Num_Data_Pts", "Std_Error", "Src_Cd", "Derriv_Cd", "Ref_NDB_No",
					"Add_Nutr_Mark", "Num_Studies", "Min", "Max", "DF", "Low_EB", "Up_EB", "Stat_cmt", "AddMod_Date",
					"CC" },
			{ "NDB_No", "Seq", "Amount", "Msre_Desc", "Gm_Wgt", "Num_Data_Pts", "Std_Dev" },
			{ "Nutr_No", "Units", "Tagname", "NutrDesc", "Num_Dec", "SR_Order" }, { "FdGrp_Cd", "FdGrp_Desc" },
			{ "Factor_Code", "Description" }, { "NDB_No", "Factor_Code" },
			{ "NDB_No", "Footnt_No", "Footnt_Typ", "Nutr_No", "Footnt_Txt" } };

	public static final int FOOD_DES = 0, NUT_DATA = 1, WEIGHT = 2, NUTR_DEF = 3, FD_GROUP = 4, LANGDESC = 5,
			LANGUAL = 6, FOOTNOTE = 7;

	/**
	 * Maximum number of words searched by from a user's query
	 */
	public static final int QUERY_CAP = 5;

	// 0 1 2 3 4 5 6 7 8 9 10 11 12 13
	// CHO_Factor, ComName, Fat_Factor, FdGrp_Cd, Long_Desc, ManufacName,
	// NDB_No, N_Factor, Pro_Factor, Ref_desc, Refuse, SciName, Short_Desc,
	// Survey

	public FoodPacketBinaryTree() {
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

	public FoodPacketList search(String[] queries, String[] headers, boolean useLanguals) {
		FoodPacketList list = new FoodPacketList();
		
		// Only look at first x words from the user's query, x being value inside 'QUERY_CAP'
		if (queries.length > QUERY_CAP)
			queries = Arrays.copyOfRange(queries, 0, 5);

		// Call the recursive search method with the starting node (the head) and specified parameters
		this.internalSearch(this.head, queries, headers, list, useLanguals);
		return list;
	}

	private boolean internalSearch(Node<FoodPacket> node, String[] queries, String[] headers, FoodPacketList list,
			boolean useLanguals) {
		if (node != null) {
			FoodPacket food = node.getItem();
			int matches = 0;
			for (String query : queries) {
				for (String header : headers) {
					if (food.getValue(header).toLowerCase().contains(query)) {
						matches += 2;
					}
				}
				if (useLanguals)
					matches += langualSearch(node, query, list);
			}
			if (matches > 0)
				list.add(food, matches);
			internalSearch(node.getLeftChild(), queries, headers, list, useLanguals);
			internalSearch(node.getRightChild(), queries, headers, list, useLanguals);
		}
		return false;
	}

	private int langualSearch(Node<FoodPacket> node, String query, FoodPacketList list) {
		String[] langualData = node.getItem().getLanguals();
		int matches = 0;
		if (!(langualData == null || langualData.length <= 0)) {
			for (int i = 0; i < langualData.length; i++) {
				if (langualData[i].toLowerCase().contains(query))
					matches++;
			}
		}
		return matches;
	}
}