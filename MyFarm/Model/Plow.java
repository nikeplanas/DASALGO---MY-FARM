package MyFarm.Model;

/**	This class is a representation of the Plow model.	 
 *	@author Ma. Patricia Y. Villaroman
 *	@author Nicole Danielle Planas
 *	@version 1.0
 */

public class Plow extends Item {

	/**
	 * This method sets name and description for Plow object
	 */
	public Plow () {
		this.setName("Plow");
		this.setDescription("Used to plow tiles and remove withered plants.");
	}

	/**
	 * This method sets the current t to plowed
	 * @param t is the current tile
	 */

	public void plowTile (Tile t) {
		t.setPlowed();
	}
}
