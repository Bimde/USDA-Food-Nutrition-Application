package database;

import database.datastrucutres.BinaryTree;

public class FoodPacket implements KeyCompare<FoodPacket> {

	public static final String[][] HEADERS = {
			{ "NDB_No", "FdGrp_Cd", "Long_Desc", "Short_Desc", "ComName", "ManufacName", "Survey", "Ref_desc", "Refuse",
					"SciName", "N_Factor", "Pro_Factor", "Fat_Factor", "CHO_Factor" },
			{ "NDB_No", "Nutr_No", "Nutr_Val", "Num_Data_Pts", "Std_Error", "Src_Cd", "Derriv_Cd", "Ref_NDB_No",
					"Add_Nutr_Mark", "Num_Studies", "Min", "Max", "DF", "Low_EB", "Up_EB", "Stat_cmt", "AddMod_Date",
					"CC" },
			{ "NDB_No", "Seq", "Amount", "Msre_Desc", "Gm_Wgt", "Num_Data_Pts", "Std_Dev" },
			{ "Nutr_No", "Units", "Tagname", "NutrDesc", "Num_Dec", "SR_Order" }, { "FdGrp_Cd", "FdGrp_Desc" } };

	public static final int FOOD_DES = 0, NUT_DATA = 1, WEIGHT = 2, NUTR_DEF = 3, FD_GROUP = 4;

	private BinaryTree<Head> values;
	private int key;
	private NutrientPacket nutrientPacket;

	public FoodPacket(String[] data, String[] headers) {
		this.addData(data, headers);
		this.setKey(Integer.parseInt(data[0]));
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

	public void addNutrientPacket(NutrientPacket nutrients) {
		this.nutrientPacket = nutrients;
	}

	public NutrientPacket getNutrientPacket() {
		return this.nutrientPacket;
	}

	private void setKey(int key) {
		this.key = key;
	}
}
