package MyFarm.Model;

public class WateringCan extends Item {

    public WateringCan () {
        this.setName("Watering Can");
        this.setDescription("Used to water tiles.");
    }
    
    public void waterTile (Tile tile) {
        tile.addWater();
    }
}
