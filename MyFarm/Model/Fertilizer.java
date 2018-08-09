package MyFarm.Model;

public class Fertilizer extends Item {
    private int amount;
    public Fertilizer () {
        amount = 0;
        this.setName("Fertilizer");
        this.setDescription("Not actually a tool, but is bought in finite amounts and fertilizes a specific tile; Cannot be placed on a tile with a plant; Costs 10 OC; At the start of the game, the user originally has 5 units of fertilizer");
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount() {
        amount++;
    }
}
