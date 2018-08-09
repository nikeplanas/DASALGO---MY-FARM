package MyFarm.Model;
import.java.util.Random;

public abstract class Seed extends Item {
    private String name;
    private long harvestTime;
    private long secondsNeeded;
    private int timesWatered;
    private int waterNeeded;
    private int waterLimit;
    private int timesFertilized;
    private int fertilizerNeeded;
    private int fertilizerLimit;
    private int harvestCost;
    private String productsProduced;
    private double seedCost;
    private double baseSelling;
    private int amount;


    @Override
    public String getDescription () {
        return "Time to harvest: " + harvestTime +
                "\tMinimum Water Needed: " + waterNeeded +
                "\tMaximum Water Limit: " + waterLimit +
                "\nMinimum Fertilizer Needed: " + fertilizerNeeded +
                "\tMaximum Fertilizer Limit: " + fertilizerLimit +
                "\tSeed Cost: " + seedCost +
                "\nHarvest Cost: " + harvestCost +
                "\tBase Selling Price: " + baseSelling +
                "\tPossible No. of Products: " + productsProduced;
    }

    @Override
    public String toString() {
        return "Seed{" +
                "name=" + getName() +
                "harvestTime=" + harvestTime +
                ", secondsNeeded=" + secondsNeeded +
                ", timesWatered=" + timesWatered +
                ", waterNeeded=" + waterNeeded +
                ", waterLimit=" + waterLimit +
                ", timesFertilized=" + timesFertilized +
                ", fertilizerNeeded=" + fertilizerNeeded +
                ", fertilizerLimit=" + fertilizerLimit +
                ", harvestCost=" + harvestCost +
                ", productsProduced='" + productsProduced + '\'' +
                ", seedCost=" + seedCost +
                ", baseSelling=" + baseSelling +
                ", amount=" + amount +
                '}';
    }

    public long getHarvestTime() {
        return harvestTime;
    }

    public void setHarvestTime(long harvestTime) {
        this.harvestTime = harvestTime;
    }

    public long getSecondsNeeded() {
        return secondsNeeded;
    }

    public void setSecondsNeeded(long secondsNeeded) {
        this.secondsNeeded = secondsNeeded;
    }

    public int getTimesWatered() {
        return timesWatered;
    }

    public void setTimesWatered(int timesWatered) {
        this.timesWatered = timesWatered;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public void setWaterNeeded(int waterNeeded) {
        this.waterNeeded = waterNeeded;
    }

    public int getWaterLimit() {
        return waterLimit;
    }

    public void setWaterLimit(int waterLimit) {
        this.waterLimit = waterLimit;
    }

    public int getTimesFertilized() {
        return timesFertilized;
    }

    public void setTimesFertilized(int timesFertilized) {
        this.timesFertilized = timesFertilized;
    }

    public int getFertilizerNeeded() {
        return fertilizerNeeded;
    }

    public void setFertilizerNeeded(int fertilizerNeeded) {
        this.fertilizerNeeded = fertilizerNeeded;
    }

    public int getFertilizerLimit() {
        return fertilizerLimit;
    }

    public void setFertilizerLimit(int fertilizerLimit) {
        this.fertilizerLimit = fertilizerLimit;
    }

    public int getHarvestCost() {
        return harvestCost;
    }

    public void setHarvestCost(int harvestCost) {
        this.harvestCost = harvestCost;
    }

    public String getProductsProduced() {
        return productsProduced;
    }

    public void setProductsProduced(String productsProduced) {
        this.productsProduced = productsProduced;
    }

    public double getSeedCost() {
        return seedCost;
    }

    public void setSeedCost(double seedCost) {
        this.seedCost = seedCost;
    }

    public double getBaseSelling() {
        return baseSelling;
    }

    public void setBaseSelling(double baseSelling) {
        this.baseSelling = baseSelling;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double buy (double myFarmCoins) {
        return myFarmCoins;
    }

    public void plant (Tile t) {

        t.setSeed;
    }
    
    public int convertPPtoRandomIntInRange () {
        Random rand = new Random();
        if (productsProduced.contains("-")) {
            String[] s = productsProduced.split("[ -]", 2);
            System.out.println(s[0]);
            System.out.println(s[1]);
            int min = Integer.parseInt(s[0].trim()), max = Integer.parseInt(s[1].trim());
            return rand.nextInt(max - min + 1) + min;
        }
        return Integer.parseInt(productsProduced.trim());
    }
    
    /*

    public double computeSP (Tile t) {
        double sp;

        return sp;
    }
    */
    public void updateBonusLimits () {

    }


}
