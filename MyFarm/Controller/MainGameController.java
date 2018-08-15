package MyFarm.Controller;

import MyFarm.Model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainGameController {

    @FXML
    public ImageView descBackground;
    @FXML
    public GridPane descBox;

    @FXML
    public ProgressBar xpBar;
    @FXML
    public Label itemDescription, xpLabel, moneyBonus,
            ht, wfBonus, uRankStat, descName,
            uMoneyBonus, uWfBonus, uHt;

    @FXML
    public VBox defaultButtons;
    @FXML
    public AnchorPane seedMenu, settingsMenu, statsMenu, upgradePane;

    @FXML
    public Label name, coins, farmerLevel, farmerType;

    @FXML
    public Button bWateringCan,  bPlow,  bPickaxe,  bFertilizer,
            turnipB, carrotB, tomatoB, potatoB,
            roseB, tulipB, stargazerB, sunflowerB,
            mangoB, appleB, bananaB, orangeB,
            itemButton, buyB, exitInventory,
            lastUsed, harvestResultButton, rankUpButton;
    @FXML
    private ArrayList<Button> tiles, seeds, tools;
    private Farmer farmer;
    private boolean selected;
    private Tile tileSelected;
    private String toolUsed, itemClicked;


    public void initialize () {
    }

    public void setFarmer(String username) throws IOException {
        farmer = new Farmer(username);
    }

    public void setAssets () {
        Random rand = new Random();
        int n, i = 0;
        harvestResultButton.setVisible(false);
        toolUsed = null;
        selected = false;
        farmerType.setText(farmer.getType());
        farmerLevel.setText("Lvl 1");
        name.setText(farmer.getName());
        itemButton.setDisable(true);
        coins.setText(String.format("%.2f", farmer.getMyFarmCoins()));

        for (Button b : tiles) {
            n = rand.nextInt(15);
            if (n == 0) {
                b.getStylesheets().add("MyFarm/View/Stylesheets/rockyTile.css");
                farmer.getField().get(i).spawnRock();
            } else
                b.getStylesheets().add("MyFarm/View/Stylesheets/unplowedTile.css");
            i++;
        }
        xpBar.setProgress(0.0F);
    }

    public void growPlant (Button b, Tile t) {
        long second = 1000;
        String path = "-fx-background-image: url('MyFarm/View/Images/" + t.getSeed().getName().toLowerCase();
        b.getStylesheets().add("MyFarm/View/Stylesheets/Growing.css");
        b.setStyle(path.concat("Growing.png');"));
        itemDescription.setText(t.getDescription());
        Thread thread = new Thread() {
            public void run () {
                boolean withered = false;
                long extraSeconds = 0;
                do {
                    try {
                        Thread.sleep(second);
                    } catch (InterruptedException e) {

                    }
                    t.addSecond();
                    if (itemDescription.isVisible() && t.equals(tileSelected)) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                itemDescription.setText(t.getDescription());
                            }
                        });
                    }
                } while ((t.getCurrentTime() < t.getSeed().getHarvestTimeWithBonus()));
                if (t.harvestSuccess()) {
                    b.getStylesheets().clear();
                    b.getStylesheets().add("MyFarm/View/Stylesheets/plant.css");
                    b.setStyle(path.concat("Harvest.png');"));
                    t.computeSP(farmer.getMoneyBonus());
                    do {
                        try {
                            Thread.sleep(second);
                        } catch (InterruptedException e) {

                        }
                        t.addSecond();
                        if (itemDescription.isVisible() && t.equals(tileSelected)) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    itemDescription.setText(t.getDescription());
                                }
                            });
                        }
                    }
                    while (b.getStylesheets().get(0).contains("plant.css") && t.getCurrentTime() < t.getSeed().getHarvestTimeWithBonus() + 60);
                    extraSeconds = 60;
                    if (b.getStylesheets().get(0).contains("plant.css"))
                        withered = true;
                } else {
                    withered = true;
                    b.getStylesheets().clear();
                    b.getStylesheets().add("MyFarm/View/Stylesheets/plant.css");
                }
                if (withered) {
                    b.setStyle(path.concat("Withered.png');"));
                    do {
                        try {
                            Thread.sleep(second);
                        } catch (InterruptedException e) {

                        }
                        t.addSecond();
                        if (itemDescription.isVisible() && t.equals(tileSelected)) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    itemDescription.setText(t.getDescription());
                                }
                            });
                        }
                    }
                    while (b.getStylesheets().get(0).contains("plant.css") && t.getCurrentTime() < (t.getSeed().getHarvestTimeWithBonus() * 3) + extraSeconds);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Random rand = new Random();
                            int n = rand.nextInt(15);
                            displayDescription(t);
                            harvestResultButton.setVisible(true);
                            harvestResultButton.setText("Harvest Failed. No Profit Earned.");
                            b.getStylesheets().clear();
                            b.setStyle(null);
                            farmer.harvest(t);
                            if (n == 0) {
                                b.getStylesheets().add("MyFarm/View/Stylesheets/rockyTile.css");
                                farmer.getField().get(t.getTileNum()).spawnRock();
                            }
                            else {
                                b.getStylesheets().add("MyFarm/View/Stylesheets/unplowedTile.css");
                            }
                            coins.setText(String.format("%.2f", farmer.getMyFarmCoins()));
                        }
                    });
                }

            }
        };
        thread.start();
    }

    public void clickTile (MouseEvent e) {
        Random rand = new Random();
        int i, n;
        Button b = (Button) e.getSource();
        int index = tiles.indexOf(b);
        Tile t = farmer.getField().get(index);
        tileSelected = t;

        if (toolUsed == null) {
            if (b.getStyle().contains("Harvest.png") && farmer.getMyFarmCoins() > farmer.getField().get(index).getSeed().getHarvestCost()) {
                displayDescription(t);
                harvestResultButton.setVisible(true);
                harvestResultButton.setText("Harvest Success! " + (t.getSellingPrice() - t.getSeed().getHarvestCost()) + "OC x " + t.getProductsproduced() + " products - " + t.getSeed().getHarvestCost() + " = " + t.getTotalEarning());
                n = rand.nextInt(15);
                b.getStylesheets().clear();
                b.setStyle(null);
                if (t.getSeed() instanceof Vegetable || t.getSeed() instanceof Flower)
                    farmer.increaseXP(10);
                else
                    farmer.increaseXP(20);
                farmer.subtractCoins(farmer.getField().get(index).getSeed().getHarvestCost());
                farmer.harvest(t);
                if (n == 0) {
                    b.getStylesheets().add("MyFarm/View/Stylesheets/rockyTile.css");
                    farmer.getField().get(index).spawnRock();
                }
                else {
                    b.getStylesheets().add("MyFarm/View/Stylesheets/unplowedTile.css");
                }
                coins.setText(String.format("%.2f", farmer.getMyFarmCoins()));
            } else {
                displayDescription(t);
            }
        } else {
            if (toolUsed.equals("Pickaxe")) {
                ((Pickaxe)farmer.getTools().get(2)).breakRock(t);
                b.getStylesheets().clear();
                b.getStylesheets().add("MyFarm/View/Stylesheets/unplowedTile.css");
                b.setDisable(true);
                farmer.increaseXP(5);
            } else if (toolUsed.equals("Watering Can")) {
                ((WateringCan)farmer.getTools().get(0)).waterTile(t);
                farmer.increaseXP(5);
            } else if (toolUsed.equals("Plow")) {
                if (tiles.get(t.getTileNum()).getStyle().contains("Withered.png")) {
                    farmer.subtractCoins(0.10 * t.getSeed().getSeedCost());
                    ((Plow) farmer.getTools().get(1)).removeWithered(t);
                    farmer.increaseXP(10);
                } else {
                    ((Plow) farmer.getTools().get(1)).plowTile(t);
                    b.getStylesheets().clear();
                    b.getStylesheets().add("MyFarm/View/Stylesheets/plowedTile.css");
                    b.setDisable(true);
                    farmer.increaseXP(2);
                }

            } else if (toolUsed.equals("Fertilizer")) {
                ((Fertilizer)farmer.getTools().get(3)).fertilizeTile(t);
                itemButton.setText("Finish Fertilizing (" + ((Fertilizer)farmer.getTools().get(3)).getAmount() + ")");
                farmer.increaseXP(5);
            } else {
                for (i = 0; i < 12; i++) {
                    if (toolUsed.equals(farmer.getSeeds().get(i).getName())) {
                        n = i;
                        if (farmer.getSeeds().get(n).getAmount() > 0) {
                            if (farmer.getSeeds().get(n).getAmount() > 0) {
                                b.getStylesheets().clear();
                                t.plant(farmer.getSeeds().get(n));
                                itemButton.setText("Finish Planting (" + farmer.getSeeds().get(n).getAmount() + ")");
                                growPlant(b, t);
                                if (farmer.getSeeds().get(n).getAmount() == 0)
                                    disableAllTiles();
                            } else {
                                disableAllTiles();
                            }
                        }
                        i = 12;
                        for (i = 0; i < 50; i++) {
                            if (!farmer.isPlantable(i, farmer.getSeeds().get(n)))
                                tiles.get(i).setDisable(true);
                        }
                    }
                }
            }
        }
        if (farmer.getXp() >= 100) {
            farmer.levelUp();
            farmerLevel.setText("Lvl " + farmer.getLevel());
        }
        xpLabel.setText(farmer.getXp() + " / 100");
        xpBar.setProgress(farmer.getXp()/100F);
    }

    public void menuButtons(ActionEvent e) {
        Button b = (Button) e.getSource();
        if (b.getParent() == defaultButtons) {
            if (b.getText().contentEquals("Inventory")) {
                defaultButtons.setVisible(false);
                seedMenu.setVisible(true);
            } else if (b.getText().contentEquals("Farmer Stats")) {
                defaultButtons.setVisible(false);
                statsMenu.setVisible(true);
                rankUpButton.setDisable(!farmer.validUpgradeType());
            } else if (b.getText().contentEquals("Settings")) {
                defaultButtons.setVisible(false);
                settingsMenu.setVisible(true);
            }
        } else {
            if (b.getParent() == seedMenu) {
                defaultButtons.setVisible(true);
                seedMenu.setVisible(false);
            } else if (b.getParent() == statsMenu) {
                defaultButtons.setVisible(true);
                statsMenu.setVisible(false);
            } else if (b.getParent() == settingsMenu) {
                defaultButtons.setVisible(true);
                settingsMenu.setVisible(false);
            }
        }
    }

    public void rankUp (MouseEvent e) {
        if (farmer.upgradeType()) {
            farmerType.setText(farmer.getType());
            coins.setText(String.format("%.2f", farmer.getMyFarmCoins()));
            if (farmerType.getText().equalsIgnoreCase("Registered Farmer")) {
                moneyBonus.setText("+/-2");
                wfBonus.setText("+0");
                ht.setText("-5%");
                uRankStat.setText("Distinguished Farmer");
                uMoneyBonus.setText("+/-3");
                uWfBonus.setText("+1");
                uHt.setText("-10%");
            } else if (farmerType.getText().equalsIgnoreCase("Distinguished Farmer")) {
                moneyBonus.setText("+/-3");
                wfBonus.setText("+1");
                ht.setText("-10%");
                uRankStat.setText("Honorable Farmer");
                uMoneyBonus.setText("+/-5");
                uWfBonus.setText("+2");
                uHt.setText("-15%");
            } else {
                moneyBonus.setText("+/-5");
                wfBonus.setText("+2");
                ht.setText("-15%");
                upgradePane.setVisible(false);
                ((Button)e.getSource()).setVisible(false);
            }
        }
    }

    public void toolButtons(MouseEvent e) {
        Button b = (Button) e.getSource();
        int index;
        selected = true;
        Item item;
        itemButton.setVisible(true);

        buyB.setVisible(true);
        if (seeds.contains(b)) {
            index = seeds.indexOf(b);
            item = farmer.getSeeds().get(index);
            if (toolUsed == null)
                itemButton.setText("Plant " + item.getName() + " x " + ((Seed) item).getAmount());
            else
                itemButton.setText("Finish Planting (" + ((Seed) item).getAmount() + ")");
            buyB.setText(" Buy OC" + ((Seed) item).getSeedCost() * 1.00);
            if (((Seed) item).getAmount() < 1)
                itemButton.setDisable(true);
            else
                itemButton.setDisable(false);
            displayDescription(item);
        } else if (tools.contains(b)) {
            index = tools.indexOf(b);
            item = farmer.getTools().get(index);
            displayDescription(item);
            toolBuyVisibility(index);
        }
        lastUsed = b;
        if (seeds.contains(b) || b.equals(bFertilizer))
            itemClicked = b.getId();
    }

    public void displayItem(MouseEvent e) {
        if (!selected) {
            Button b = (Button) e.getSource();
            int index;
            itemButton.setVisible(true);
            buyB.setVisible(true);
            if (seeds.contains(b)) {
                index = seeds.indexOf(b);
                Seed seed = farmer.getSeeds().get(index);
                itemButton.setText(seed.getName() + " x " + seed.getAmount());
                buyB.setText(" Buy OC" + String.format("%.2f", seed.getSeedCost() * 1.00));
                if (seed.getAmount() < 1)
                    itemButton.setDisable(true);
                else
                    itemButton.setDisable(false);
                if (farmer.getMyFarmCoins() < seed.getSeedCost())
                    buyB.setDisable(true);
                else
                    buyB.setDisable(false);
            } else if (tools.contains(b)) {
                toolBuyVisibility(tools.indexOf(b));
            }
        }

    }

    public void toolBuyVisibility (int index) {
        if (index < 3) {
            itemButton.setText("Use " + farmer.getTools().get(index).getName());
            buyB.setVisible(false);
            itemButton.setDisable(false);
        } else {
            itemButton.setText(farmer.getTools().get(index).getName() + " x "
                    + ((Fertilizer) farmer.getTools().get(index)).getAmount());
            buyB.setText(" Buy OC10.00");
            if (((Fertilizer) farmer.getTools().get(index)).getAmount() == 0)
                itemButton.setDisable(true);
            if (farmer.getMyFarmCoins() < 10)
                buyB.setDisable(true);
            else
                buyB.setDisable(false);
        }
    }

    public void toolMode() {
        Button b = lastUsed;
        int index, i;
        Tile t;
        itemButton.setText("Done");
        if (toolUsed == null) {
            if (seeds.contains(b)) {
                index = seeds.indexOf(b);
                for (Button button : tools) {
                    button.setDisable(true);
                }
                for (Button button : seeds) {
                    button.setDisable(true);
                }
                for (i = 0; i < 50; i++) {
                    if (!farmer.isPlantable(i, farmer.getSeeds().get(index)))
                        tiles.get(i).setDisable(true);
                }
                index = seeds.indexOf(b);
                toolUsed = farmer.getSeeds().get(index).getName();
                itemButton.setText(farmer.getSeeds().get(index).getName() + " x " + farmer.getSeeds().get(index).getAmount());
            } else if (tools.contains(b)) {
                index = tools.indexOf(b);
                toolUsed = farmer.getTools().get(index).getName();
                for (Button button : tools) {
                    button.setDisable(true);
                }
                for (Button button : seeds) {
                    button.setDisable(true);
                }
                if (toolUsed.equals("Pickaxe")) {
                    for (i = 0; i < 50; i++) {
                        t = farmer.getField().get(i);
                        if (!t.isObstructed())
                            tiles.get(i).setDisable(true);
                    }
                } else if (toolUsed.equals("Watering Can")) {
                    for (i = 0; i < 50; i++) {
                        t = farmer.getField().get(i);
                        if (t.isObstructed() || !t.isPlowed() || (t.getSeed() != null && t.getWater() == t.getSeed().getWaterLimit()))
                            tiles.get(i).setDisable(true);

                    }
                } else if (toolUsed.equals("Fertilizer")) {
                    for (i = 0; i < 50; i++) {
                        t = farmer.getField().get(i);
                        if (t.getSeed() != null || !t.isPlowed())
                            tiles.get(i).setDisable(true);
                    }
                    itemButton.setText("Finish Fertilizing (" + ((Fertilizer)farmer.getTools().get(3)).getAmount() + ")");
                } else if (toolUsed.equals("Plow")) {
                    for (i = 0; i < 50; i++) {
                        t = farmer.getField().get(i);
                        if (t.getSeed() == null) {
                            if (t.isObstructed() || t.isPlowed())
                                tiles.get(i).setDisable(true);
                        }
                        else {
                            if (tiles.get(t.getTileNum()).getStyle().contains("Withered.png") && farmer.getMyFarmCoins() > 0.10 * t.getSeed().getSeedCost())
                                tiles.get(i).setDisable(false);
                            else
                                tiles.get(i).setDisable(true);
                        }
                    }
                }
            }
            exitInventory.setVisible(false);
        }
        else {
            selected = false;
            toolUsed = null;
            closeDescription();
            exitInventory.setVisible(true);
            for (i = 0; i < 50; i++) {
                tiles.get(i).setDisable(false);
            }
            for (Button button : tools) {
                button.setDisable(false);
            }
            for (Button button : seeds) {
                button.setDisable(false);
            }
        }
    }

    public void buy(MouseEvent e) {
        Button b = null;
        int i, index;
        for (i = 0; i < 12; i++) {
            if (itemClicked.equals(seeds.get(i).getId()))
                b = seeds.get(i);
        }
        if (itemClicked.equals(bFertilizer.getId())) {
            farmer.buyItem(farmer.getTools().get(3));
            coins.setText(String.format("%.2f", farmer.getMyFarmCoins()));
            if (toolUsed == null)
                itemButton.setText("Use Fertilizer x "
                    + ((Fertilizer) farmer.getTools().get(3)).getAmount());
            else
                itemButton.setText("Finish Fertilizing (" +
                        ((Fertilizer)farmer.getTools().get(3)).getAmount() + ")");
            itemButton.setDisable(false);
            if (farmer.getMyFarmCoins() < 10)
                buyB.setDisable(true);
            else
                buyB.setDisable(false);
        } else if (b != null) {
            index = seeds.indexOf(b);
            Seed s = farmer.getSeeds().get(index);
            farmer.buyItem(s);
            for (i = 0; i < 50; i++) {
                if (farmer.isPlantable(i, farmer.getSeeds().get(index)))
                    tiles.get(i).setDisable(false);
            }
            coins.setText(String.format("%.2f", farmer.getMyFarmCoins()));
            if (toolUsed == null)
                itemButton.setText(s.getName() + " x " + s.getAmount());
            else
                itemButton.setText("Finish Planting (" + s.getAmount() + ")");
            itemButton.setDisable(false);
            if (farmer.getMyFarmCoins() < s.getSeedCost())
                buyB.setDisable(true);
            else
                buyB.setDisable(false);
        }
        if (farmer.getXp() >= 100) {
            farmer.levelUp();
            farmerLevel.setText("Lvl " + farmer.getLevel());
        }
        xpLabel.setText(farmer.getXp() + " / 100");
        xpBar.setProgress(farmer.getXp()/100F);
    }

    public void hideHarvestResult() {
        harvestResultButton.setVisible(false);
    }

    public void disableAllTiles () {
        for (int i = 0; i < 50; i++) {
            tiles.get(i).setDisable(true);
        }
    }

    public void displayDescription (Item item) {
        descBackground.setVisible(true);
        descBox.setVisible(true);
        descName.setText(item.getName());
        itemDescription.setVisible(true);
        itemDescription.setText(item.getDescription());
    }

    public void displayDescription (Tile tile) {
        descBackground.setVisible(true);
        descBox.setVisible(true);
        descName.setText("Tile");
        itemDescription.setVisible(true);
        itemDescription.setText(tile.getDescription());
    }

    public void closeDescription () {
        if (toolUsed == null) {
            descBackground.setVisible(false);
            descBox.setVisible(false);
            selected = false;
            hideDisplayItem();
        }
    }

    public void hideDisplayItem() {
        if (!selected) {
            itemButton.setVisible(false);
            buyB.setVisible(false);
        }
    }
}
