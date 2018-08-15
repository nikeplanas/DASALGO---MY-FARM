package MyFarm.Model;

/**	This class is a representation of the fertilizer model.
 *	@author Ma. Patricia Y. Villaroman
 *	@author Nicole Danielle Planas
 *	@version 1.0
 */
public class Fertilizer extends Item {
    private int amount;
    public Fertilizer () {
        amount = 0;
        this.setName("Fertilizer");
        this.setDescription("Not actually a tool, but is bought in finite amounts and fertilizes a specific tile; Cannot be placed on a tile with a plant.");
    }

/**	This method is the getter for amount
 *	
 *	@return the 
 *	
 */
    public int getAmount() {
        return amount;
    }

    public void addAmount() {
        amount++;
    }
    
    public void fertilizeTile (Tile t) {
        if (t.getSeed() == null) {
            t.addFertilizer();
            amount--;
        }
    }
}
