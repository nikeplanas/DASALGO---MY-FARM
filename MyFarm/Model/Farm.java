package MyFarm.Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm  {
    private ArrayList<Tile> field;
    private ArrayList<Item> tools;
    private ArrayList<Seed> seeds;

    public Farm () throws IOException {
        int i;
        String sInput;
        field = new ArrayList<>(50);
        tools = new ArrayList<>(4);
        seeds = new ArrayList<>(12);

        for (i = 0; i < 50; i++)
            field.add(new Tile(i));

        tools.add(new WateringCan());
        tools.add(new Plow());
        tools.add(new Pickaxe());
        tools.add(new Fertilizer());

        System.out.println(new File(".").getAbsolutePath());


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

            System.out.println(seeds.get(i).toString());
        }
    }

    public ArrayList<Tile> getField() {
        return field;
    }

    public ArrayList<Item> getTools() {
        return tools;
    }

    public ArrayList<Seed> getSeeds() {
        return seeds;
    }


    public boolean isPlantable (int tilenum, Seed s) {
        int x1 = 1, x2 = 1, y1 = 0, y2 = 0;
        if (!field.get(tilenum).isPlowed())
            return false;
        if (s instanceof Tree) {
            if (tilenum < 40)
                y2 = 10;
            if (tilenum > 9)
                y1 = 10;
            if (tilenum % 10 == 0)
                x1 = 0;
            if (tilenum % 10 == 9)
                x2 = 0;

            if (field.get(tilenum - x1).getSeed() == null
                    && field.get(tilenum + x2).getSeed() == null
                    && field.get(tilenum - y1).getSeed() == null
                    && field.get(tilenum + y2).getSeed() == null
                    && field.get(tilenum - y1 - x1).getSeed() == null
                    && field.get(tilenum - y1 + x2).getSeed() == null
                    && field.get(tilenum + y2 - x1).getSeed() == null
                    && field.get(tilenum + y2 + x2).getSeed() == null) {
                return true;
            }
            else
                return false;
        }
        return true;
    }


    public boolean isInteger (String sInput) {
        int i = 0;
        if (sInput == null)
            return false;

        if (sInput.isEmpty())
            return false;

        if (sInput.charAt(0) == '-') {
            if (sInput.length() == 1)
                return false;
            i = 1;
        }

        for (; i < sInput.length(); i++){
            char c = sInput.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }
    
    //BASED ON UML

	public boolean ifPlantable (Tile t, Seed s) {
		if (t.isPlowed )
			return true;
		else
			return false;
	}

	public ArrayList<Tile> getField () {
		return field;
	}

	public Tile getTile (int tilenum) {
		return field.get(tilenum);
	}

	//gets the list of items to the inventory
	public ArrayList<Item> getTools () {
		return tools;
	}

	public Item getItem (int index) {
		switch(index) {
			case 1:
				return inventory.get(index); break;
			case 2:
				return inventory.get(index); break;
			case 3:
				return inventory.get(index); break;
			case 4:
				return inventory.get(index); break;
		}
	}
}
