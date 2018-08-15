package MyFarm.Model;

import java.util.Random;

/**	This abstract class is a representation of a seed in MyFarm
 *	@author Ma. Patricia Y. Villaroman
 *	@author Nicole Danielle Planas
 *	@version 1.0
 */
public abstract class Seed extends Item {
    private long harvestTime;
    private long harvestTimeBonus;
    private int waterNeeded;
    private int waterLimit;
    private int fertilizerNeeded;
    private int fertilizerLimit;
    private int harvestCost;
    private String productsProduced;
    private double seedCost;
    private double baseSelling;
    private int amount;

    /** This is used to construct the class. The harvestTimeBonus is set to 0.
     *  The rest of the attributes will be set using setters.
     */
    public Seed () {
        harvestTimeBonus = 0;
    }


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

    /** This method overrides the toString() method which is used for checking the values
     *  of the attributes of a Seed object
     *
     * @return a string containing the attributes
     */
    @Override
    public String toString() {
        return "Seed{" +
                "name=" + getName() +
                "harvestTime=" + harvestTime +
                ", waterNeeded=" + waterNeeded +
                ", waterLimit=" + waterLimit +
                ", fertilizerNeeded=" + fertilizerNeeded +
                ", fertilizerLimit=" + fertilizerLimit +
                ", harvestCost=" + harvestCost +
                ", productsProduced='" + productsProduced + '\'' +
                ", seedCost=" + seedCost +
                ", baseSelling=" + baseSelling +
                ", amount=" + amount +
                '}';
    }

    /** It subtracts the harvest time bonus from the harvest time of the Seed
     *  and returns it.
     *
     * @return The number of seconds until the plant is ready for harvest.
     */

    public long getHarvestTimeWithBonus() {
        return harvestTime - harvestTimeBonus;
    }

    /** Gets the value of harvest time.
     *
     * @return The base time it takes to harvest the plant.
     */

    public long getHarvestTime() {
        return harvestTime;
    }

    /** Sets the value for harvest time.
     *
     * @param harvestTime The base time it takes to harvest the plant.
     */

    public void setHarvestTime(long harvestTime) {
        this.harvestTime = harvestTime;
    }

    /** Gets the value of the waterNeeded attribute.
     *
     * @return The minimum number of times needed to water the plant for it to survive.
     */

    public int getWaterNeeded() {
        return waterNeeded;
    }

    /** Sets the value of waterNeeded
     *
     * @param waterNeeded The value to be saved in waterNeeded.
     */
    public void setWaterNeeded(int waterNeeded) {
        this.waterNeeded = waterNeeded;
    }

    /** Gets the value of the bonus to harvest time from ranking up.
     *
     * @return The bonus time to be subtracted from the base harvest time.
     */

    public long getHarvestTimeBonus() {
        return harvestTimeBonus;
    }

    /** Sets the value for the bonus to harvest time
     *
     * @param harvestTimeBonus
     */

    public void setHarvestTimeBonus(long harvestTimeBonus) {
        this.harvestTimeBonus = harvestTimeBonus;
    }

    /** Gets the maximum number of times a plant can be watered.
     *
     * @return The maximum number of times a plant can be watered.
     */

    public int getWaterLimit() {
        return waterLimit;
    }

    /** Sets the value for waterLimit
     *
     * @param waterLimit the value to be saved to waterLimit.
     */
    public void setWaterLimit(int waterLimit) {
        this.waterLimit = waterLimit;
    }

    /** Gets the minimum fertilizer needed for a plant to survive.
     *
     * @return The attribute fertilizerNeeded.
     */

    public int getFertilizerNeeded() {
        return fertilizerNeeded;
    }

    /** Sets the value for fertilizerNeeded
     *
     * @param fertilizerNeeded The value to be saved to fertilizerNeeded.
     */

    public void setFertilizerNeeded(int fertilizerNeeded) {
        this.fertilizerNeeded = fertilizerNeeded;
    }

    /** Gets the limit to the times a soil can be fertilized and for it to apply
     *  to the plant.
     *
     * @return Maximum value to be applied in computing the fb(fertilizer bonus)
     */

    public int getFertilizerLimit() {
        return fertilizerLimit;
    }

    /** Sets the value of fertilizerLimit
     *
     * @param fertilizerLimit The value to be saved to fertilizerLimit
     */

    public void setFertilizerLimit(int fertilizerLimit) {
        this.fertilizerLimit = fertilizerLimit;
    }

    /** Gets the cost of harvesting plants under the seed types.
     *
     * @return The cost of harvesting a plant
     */

    public int getHarvestCost() {
        return harvestCost;
    }

    /** Sets the value of harvestCost
     *
     * @param harvestCost The value to be saved to harvestCost
     */

    public void setHarvestCost(int harvestCost) {
        this.harvestCost = harvestCost;
    }

    /** Gets the string containing the range of the number of products that the plant.
     *
     * @return String containing the range of the number of products that the plant
     *         can produce.
     */

    public String getProductsProduced() {
        return productsProduced;
    }


    /** Sets the value of productsProduced
     *
     * @param productsProduced The value to be saved to productsProduced
     */

    public void setProductsProduced(String productsProduced) {
        this.productsProduced = productsProduced;
    }

    /** Gets the cost of buying the seed
     *
     * @return Double value representing the cost of buying the seed.
     */

    public double getSeedCost() {
        return seedCost;
    }

    /** Sets the value of seedCost
     *
     * @param seedCost The value to be saved to seedCost
     */

    public void setSeedCost(double seedCost) {
        this.seedCost = seedCost;
    }

    /** Gets the base selling price of an individual product of the plant.
     *
     * @return The base selling price of a product.
     */

    public double getBaseSelling() {
        return baseSelling;
    }

    /** Sets the value of baseSelling
     *
     * @param baseSelling The value to be saved to baseSelling
     */

    public void setBaseSelling(double baseSelling) {
        this.baseSelling = baseSelling;
    }

    /** Gets the current amount of seeds.
     *
     * @return Integer value representing the current amount of seeds.
     */

    public int getAmount() {
        return amount;
    }

    /** Sets the value of amount
     *
     * @param amount The value to be saved to amount
     */

    public void setAmount(int amount) {
        this.amount = amount;
    }

    /** This method, based on the products produced String, generates a random
     *  value within the range to represent the number of products produced by
     *  an individual plant.
     * @return A random number based on the given range of the products produced.
     */

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

    /** This method increments the amount of seeds by one.
     *
     */

    public void addAmount() {
        amount++;
    }
}
