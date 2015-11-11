package database;

public class NutrientPacket {

	private NutrientList nutrients;

	public NutrientPacket(NutrientList nutrients) {
		this.nutrients = nutrients;
	}

	public NutrientList getNutrients() {
		return this.nutrients;
	}
}
