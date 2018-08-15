package MyFarm.Model;

/**	This class is a representation of the fertilizer model.
 *	@author Ma. Patricia Y. Villaroman
 *	@author Nicole Danielle Planas
 *	@version 1.0
 */
public class Fertilizer extends Item {
	private int amount;
	
	/**
	* This constructor instantiates Fertilizer which also sets the amount to 0 and sets the name and
	* description for the respective object.
	*/
	public Fertilizer () {
		amount = 0;
		this.setName("Fertilizer");
		this.setDescription("Not actually a tool, but is bought in finite amounts and fertilizes a specific tile; Cannot be placed on a tile with a plant.");
	}

	/**	
	 *	This method is the getter for amount of fertilizer
	 *	@return the amount of fertilizer
	 *
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * This method counts the amount of fertilizer every increment.
	 */
	public void addAmount() {
		amount++;
	}

	/** 
	 * This method adds fertilizer to the tile.
	 * Decreases amount of current fertilizer for every use.
	 * @param t the current Tile
	 */
	public void fertilizeTile (Tile t) {
		if (t.getSeed() == null) {
			t.addFertilizer();
			amount--;
		}
	}
}
