package MyFarm.Model;

public class Fertilizer extends Item {
    private int amount;
    public Fertilizer () {
        amount = 0;
        this.setName("Fertilizer");
        this.setDescription("Not actually a tool, but is bought in finite amounts and fertilizes a specific tile; Cannot be placed on a tile with a plant.");
    }

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
