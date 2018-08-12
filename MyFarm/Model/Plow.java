package MyFarm.Model;

public class Plow extends Item {

    public Plow () {
        this.setName("Plow");
        this.setDescription("Used to plow tiles and remove withered plants.");
    }
    
    public void plowTile (Tile t) {
        t.setPlowed();
    }
}
