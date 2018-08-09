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

    public int getWater() {
        return water;
    }

    public int getFertilizer() {
        return fertilizer;
    }

    public int getTileNum() {
        return tileNum;
    }

    public Seed getSeed() {
        return seed;
    }
}
