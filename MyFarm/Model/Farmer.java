package MyFarm.Model;

public class Farmer {
    private String name;
    private double myFarmCoins;
    private int level;
    private int xp;
    private String type;

    public Farmer (String name) {
        this.name = name;
        type = "Farmer";
        xp = 0;
        level = 1;
        myFarmCoins = 100.00;
    }

    public void addCoins (double myFarmCoins) {
        this.myFarmCoins += myFarmCoins;
    }

    public void increaseXP (int xp) {
        this.xp += xp;
    }

    public void levelUp () {
        level++;
        xp %= 100;
    }


    public boolean upgradeType () {
        if (type.contentEquals("Farmer") && myFarmCoins >= 200 && level >= 10) {
            type = "Registered Farmer";
            myFarmCoins -= 200;
        }
        else if (type.contentEquals("Registered Farmer") && myFarmCoins >= 250 && level >= 15) {
            type = "Distinguished Farmer";
            myFarmCoins -= 250;
        }

        else if (type.contentEquals("Distinguished Farmer") && myFarmCoins >= 250 && level >= 20) {
            type = "Honorable Farmer";
            myFarmCoins -= 350;
        }
        else
            return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public double getMyFarmCoins() {
        return myFarmCoins;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public String getType() {
        return type;
    }
}

