package MyFarm.Model;

public class Tile {
    private boolean plowed;
    private Seed seed;
    private int water;
    private int fertilizer;
    private int tileNum;
    private boolean obstructed;
    private boolean hasRock;

    public Tile(int i) {
        plowed = false;
        water = 0;
        fertilizer = 0;
        tileNum = i;
    }

    public void resetTile() {
        plowed = false;
        water = 0;
        fertilizer = 0;
        seed = null;
    }

    public void removeRock() {
        obstructed = false;
    }

    public void spawnRock() {
        obstructed = true;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public void setPlowed() {
        plowed = !plowed;
    }

    public boolean isPlowed() {
        return plowed;
    }

    public void addWater () {
        water++;
    }

    public void addFertilizer () {
        fertilizer++;
    }

    public boolean isObstructed() {
        return obstructed;
    }

    /**
    * This method gets the value of water
    */
    public int getWater() {
        return water;
    }

    /**
    * This method gets the value of fertilizer
    */
    public int getFertilizer() {
        return fertilizer;
    }
    
    /**
    * This method is the getter for the tile number
    */
    public int getTileNum() {
        return tileNum;
    }

    /**
    * This method sets the seed
    */
    public Seed getSeed() {
        return seed;
    }
}
