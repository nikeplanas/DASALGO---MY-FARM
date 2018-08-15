package MyFarm.Model;

    /**	This class is a representation of the Pickaxe model.
     *	@author Ma. Patricia Y. Villaroman
     *	@author Nicole Danielle Planas
    *	@version 1.0
     */
public class Pickaxe extends Item {

    /** This method sets the name and description of object Pickaxe
    *
    */
	public Pickaxe () {
		this.setName("Pickaxe");
		this.setDescription("Used for destroying rocks obstructing tiles.");
	}

	/** This method checks if the t is obstructed before breaking rock
	 * 
	 * @param t the current Tile
	 */
	public void breakRock(Tile t) {
		if (t.isObstructed()) {
			t.breakRock();
		}
	}
}
