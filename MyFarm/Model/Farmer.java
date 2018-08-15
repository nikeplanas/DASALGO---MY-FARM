package MyFarm.Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**	This class is a representation of the farm model.
 *	@author Ma. Patricia Y. Villaroman
 *	@author Nicole Danielle Planas
 *	@version 1.0
 */
public class Farmer {
    private String name;
    private double myFarmCoins;
    private int level;
    private int xp;
    private int moneyBonus;
    private String type;

    private ArrayList<Tile> field;
    private ArrayList<Item> tools;
    private ArrayList<Seed> seeds;

    /** This constructor instantiates the farm which includes the field, the inventory of
     *  xctools and seeds, and the attributes of the player.
     *
     * @param name the name of the current player.
     * @throws IOException is thrown for reading the csv file containing the different seeds
     */

    public Farmer (String name) throws IOException{
        int i;
        String sInput;
        this.name = name;
        type = "Farmer";
        xp = 0;
        level = 1;
        moneyBonus = 0;
        myFarmCoins = 100.00;
        field = new ArrayList<>(50);
        tools = new ArrayList<>(4);
        seeds = new ArrayList<>(12);

        for (i = 0; i < 50; i++)
            field.add(new Tile(i));

        tools.add(new WateringCan());
        tools.add(new Plow());
        tools.add(new Pickaxe());
        tools.add(new Fertilizer());

        Scanner scanner = new Scanner (new File("src/MyFarm/Controller/seeds.csv"), "ISO-8859-1");
        scanner.nextLine();
        scanner.useDelimiter("[\\n,]");

        for (i = 0; scanner.hasNext(); i++) {
            sInput = scanner.next();
            if (sInput.equalsIgnoreCase("Vegetable"))
                seeds.add(new Vegetable());
            else if (sInput.equalsIgnoreCase("Flower"))
                seeds.add(new Flower());
            else
                seeds.add(new Tree());

            seeds.get(i).setName(scanner.next().trim());
            seeds.get(i).setHarvestTime((long)((Double.parseDouble(scanner.next().trim()) * 60)));
            seeds.get(i).setWaterNeeded(Integer.parseInt(scanner.next().trim()));
            seeds.get(i).setWaterLimit(Integer.parseInt(scanner.next().trim()));
            seeds.get(i).setFertilizerNeeded(Integer.parseInt(scanner.next().trim()));
            seeds.get(i).setFertilizerLimit(Integer.parseInt(scanner.next().trim()));
            seeds.get(i).setHarvestCost(Integer.parseInt(scanner.next().trim()));
            seeds.get(i).setProductsProduced(scanner.next().trim());
            seeds.get(i).setSeedCost(Integer.parseInt(scanner.next().trim()));
            seeds.get(i).setBaseSelling(Double.parseDouble(scanner.next().trim()));
        }
    }

    /**
     * This method is to check if a seed can be planted in a particular tile
     * @param tilenum the number or index of the tile to be checked
     * @param s is the seed to plant on the tile
     * @return whether a seed can be planted on a tile given the tile's index
     */

    public boolean isPlantable (int tilenum, Seed s) {
        int x1 = 1, x2 = 1, y1 = 0, y2 = 0;
        if (tilenum < 40)
            y2 = 10;
        if (tilenum > 9)
            y1 = 10;
        if (tilenum % 10 == 0)
            x1 = 0;
        if (tilenum % 10 == 9)
            x2 = 0;
        if (!field.get(tilenum).isPlowed())
            return false;
        if (s instanceof Tree) {
            if (field.get(tilenum - x1).getSeed() == null
                    && field.get(tilenum + x2).getSeed() == null
                    && field.get(tilenum - y1).getSeed() == null
                    && field.get(tilenum + y2).getSeed() == null
                    && field.get(tilenum - y1 - x1).getSeed() == null
                    && field.get(tilenum - y1 + x2).getSeed() == null
                    && field.get(tilenum + y2 - x1).getSeed() == null
                    && field.get(tilenum + y2 + x2).getSeed() == null)
                return true;
            else
                return false;
        }
        if (field.get(tilenum - x1).getSeed() instanceof Tree
                || field.get(tilenum + x2).getSeed() instanceof Tree
                || field.get(tilenum - y1).getSeed() instanceof Tree
                || field.get(tilenum + y2).getSeed() instanceof Tree
                || field.get(tilenum - y1 - x1).getSeed() instanceof Tree
                || field.get(tilenum - y1 + x2).getSeed() instanceof Tree
                || field.get(tilenum + y2 - x1).getSeed() instanceof Tree
                || field.get(tilenum + y2 + x2).getSeed() instanceof Tree)
            return false;
        return true;
    }

    /**
     * This method harvests the plant on the given tile and resets the tile.
     * It calls the method getTotalEarning() to compute the profit and add to
     * the farmer's money.
     * @param t is the tile that contains the plant to be harvested
     */

    public void harvest (Tile t) {
        myFarmCoins += t.getTotalEarning();
        t.resetTile();
    }

    /**
     * This method is used to add to the current value of myFarmCoins.
     * @param myFarmCoins the coins to be added to myFarmCoins
     */

    public void addCoins (double myFarmCoins) {
        this.myFarmCoins += myFarmCoins;
    }

    /**
     * This method is used to subtract from the current value of myFarmCoins.
     * @param myFarmCoins the coins to be subtracted from myFarmCoins
     */

    public void subtractCoins (double myFarmCoins) {
        this.myFarmCoins -= myFarmCoins;
    }

    /**
     * This method is used to buy a given item. The value of myFarmCoins
     * will be subtracted based on the cost of the item.
     * @param item the item to be bought by the farmer.
     */

    public void buyItem (Item item) {
        int index;
        if (item instanceof Seed) {
            index = seeds.indexOf(item);
            if (index > -1) {
                seeds.get(index).addAmount();
                myFarmCoins -= seeds.get(index).getSeedCost();
            }
        } else if (item.equals(tools.get(3))) {
            ((Fertilizer)tools.get(3)).addAmount();
            myFarmCoins -= 10;
        }
    }

    /**
     * This method increases the xp (experience points) of the farmer.
     * @param xp the int value to be added to the farmer's current xp.
     */

    public void increaseXP (int xp) {
        this.xp += xp;
    }

    /**
     * This method is used to update the farmer's level.
     */

    public void levelUp () {
        level++;
        xp %= 100;
    }

    /**
     * This method is used to increase the rank of the farmer if they fulfill any of the
     * rank requirements.
     * @return boolean value to indicate if the farmer has successfully ranked up.
     */

    public boolean upgradeType () {
        int i;
        if (type.contentEquals("Farmer") && myFarmCoins >= 200 && level >= 10) {
            type = "Registered Farmer";
            myFarmCoins -= 200;
            moneyBonus = 2;

            for (i = 0; i < 12 ; i++) {
                seeds.get(i).setHarvestTimeBonus((long)(seeds.get(i).getHarvestTime() * 0.05));
                seeds.get(i).setSeedCost(seeds.get(i).getSeedCost() - 2);
            }
        }
        else if (type.contentEquals("Registered Farmer") && myFarmCoins >= 250 && level >= 15) {
            type = "Distinguished Farmer";
            myFarmCoins -= 250;
            moneyBonus = 3;

            for (i = 0; i < 12 ; i++) {
                seeds.get(i).setHarvestTimeBonus((long)(seeds.get(i).getHarvestTime() * 0.10));
                seeds.get(i).setFertilizerLimit(seeds.get(i).getFertilizerLimit() + 1);
                seeds.get(i).setSeedCost(seeds.get(i).getSeedCost() - 1);
                seeds.get(i).setWaterLimit(seeds.get(i).getWaterLimit() + 1);
            }
        }

        else if (type.contentEquals("Distinguished Farmer") && myFarmCoins >= 250 && level >= 20) {
            type = "Honorable Farmer";
            myFarmCoins -= 350;
            moneyBonus = 5;

            for (i = 0; i < 12 ; i++) {
                seeds.get(i).setHarvestTimeBonus((long)(seeds.get(i).getHarvestTime() * 0.15));
                seeds.get(i).setFertilizerLimit(seeds.get(i).getFertilizerLimit() + 1);
                seeds.get(i).setSeedCost(seeds.get(i).getSeedCost() - 2);
                seeds.get(i).setWaterLimit(seeds.get(i).getWaterLimit() + 1);
            }
        }
        else
            return false;
        return true;
    }

    /**
     * This method is for checking if the farmer is ready to upgrade.
     * @return boolean if the farmer can upgrade their rank.
     */

    public boolean validUpgradeType () {
        if (type.contentEquals("Farmer") && myFarmCoins >= 200 && level >= 10) {
            return true;
        }
        else if (type.contentEquals("Registered Farmer") && myFarmCoins >= 250 && level >= 15) {
            return true;
        }

        else if (type.contentEquals("Distinguished Farmer") && myFarmCoins >= 250 && level >= 20) {
            return true;
        }
        return false;
    }

    /**
     * This method is the getter for moneyBonus
     * @return the buying/selling bonus for the farmer
     */

    public int getMoneyBonus() {
        return moneyBonus;
    }

    /**
     * This method is the getter for field.
     * @return the ArrayList field which contains tiles to be planted on.
     */

    public ArrayList<Tile> getField() {
        return field;
    }

    /**
     * This method is the getter for tools.
     * @return the ArrayList tools which contains the 4 tools used in the game
     * for farming
     */

    public ArrayList<Item> getTools() {
        return tools;
    }

    /**
     * This method is the getter for seeds.
     * @return the ArrayList seeds which represents the seeds that can be planted in-game
     */

    public ArrayList<Seed> getSeeds() {
        return seeds;
    }

    /**
     * This method is the getter for name
     * @return the name of the farmer
     */

    public String getName() {
        return name;
    }

    /**
     * This method is the getter for myFarmCoins
     * @return the current amount of myFarmCoins, the game currency
     */

    public double getMyFarmCoins() {
        return myFarmCoins;
    }

    /**
     * This method is the getter for the farmer's level
     * @return the level of the farmer
     */

    public int getLevel() {
        return level;
    }

    /**
     * This method is the getter for xp
     * @return the experience points of the farmer.
     */

    public int getXp() {
        return xp;
    }

    /**
     * This method is the getter for type
     * @return the type or rank of the farmer
     */

    public String getType() {
        return type;
    }
}
