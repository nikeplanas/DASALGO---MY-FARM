package MyFarm.Model;

/**	This class is a representation of the Plow model.	 
 *	@author Ma. Patricia Y. Villaroman
 *	@author Nicole Danielle Planas
 *	@version 1.0
 */

public class Plow extends Item {

	/**
	 * This constructor instantiates Plow
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
	
	/**
	* This method computes for the total amount of time a crop is withered then remove the withered plant
	* @param t is the current tile
	*/
	
	public void removeWithered (Tile t) {
        	t.setCurrentTime((t.getSeed().getHarvestTimeWithBonus() * 3) + 60);
    	}
}
