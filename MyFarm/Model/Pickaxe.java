package MyFarm.Model;

public class Pickaxe extends Item {

    public Pickaxe () {
        this.setName("Pickaxe");
        this.setDescription("Used for destroying rocks obstructing tiles.");
    }

    public void breakRock(Tile t) {
        if (t.isObstructed()) {
            t.breakRock();
        }
    }
}
