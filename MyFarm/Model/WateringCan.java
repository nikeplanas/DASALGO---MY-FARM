package MyFarm.Model;

/**	This class is a representation of the WateringCan model.
 *	@author Ma. Patricia Y. Villaroman
 *	@author Nicole Danielle Planas
 *	@version 1.0
 */

public class WateringCan extends Item {

	/**
	 * This constructor instantiates WateringCan and sets the name and description for the respective object
	 */
	public WateringCan () {
		this.setName("Watering Can");
		this.setDescription("Used to water tiles.");
	}

	/**
	 * This method adds water to the current tile
	 * @param tile current tile on to add water
	 */
	public void waterTile (Tile tile) {
		tile.addWater();
	}
}
