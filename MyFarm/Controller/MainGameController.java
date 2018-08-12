package MyFarm.Controller;

import MyFarm.Model.Farmer;
import MyFarm.Model.Fertilizer;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
            lastUsed;
    @FXML
    private ArrayList<Button> tiles, seeds, tools;
    private Farmer farmer;
    private boolean selected;
    private String toolUsed;
    private File unplowedTile = new File("MyFarm/View/Stylesheets/unplowedTile.css");
    private File mangoTree = new File("MyFarm/View/Stylesheets/mangoTree.css");
    private File plowedTile = new File("MyFarm/View/Stylesheets/plowedTile.css");


    public void initialize () {
    }

    public void setFarmer(String username) throws IOException {
        farmer = new Farmer(username);
    }

    public void setAssets () {
        toolUsed = null;
        selected = false;
        farmerType.setText(farmer.getType());
        farmerLevel.setText("Lvl 1");
        name.setText(farmer.getName());
        coins.setText(String.format("%.2f", farmer.getMyFarmCoins()));
        for (Button b : tiles) {
            b.getStylesheets().add("MyFarm/View/Stylesheets/unplowedTile.css");
        }
        xpBar.setProgress(0.0F);
        System.out.println("DAHECK");
    }


    public void change(ActionEvent e) {
        Timer timer = new Timer();
        Button b = (Button) e.getSource();
        int index = tiles.indexOf(b);
        File current = new File(b.getStylesheets().get(0));
        System.out.println(e.getEventType());
        if (current.getName().compareTo(unplowedTile.getName()) == 0) {
            b.getStylesheets().clear();
            b.getStylesheets().add("MyFarm/View/Stylesheets/plowedTile.css");
            farm.getField().get(index).setPlowed();
        }

        else if (current.getName().compareTo(plowedTile.getName()) == 0) {
            if ((farm.isPlantable(index, farm.getSeeds().get(8)))) {
                farm.getField().get(index).plant(farm.getSeeds().get(8));
                TimerTask task = new TimerTask()
                {
                    public void run()
                    {
                        b.getStylesheets().clear();
                        b.getStylesheets().add("MyFarm/View/Stylesheets/mangoTree.css");
                    }

                };
                timer.schedule(task, 5000);
            }
            else {
                System.out.println("cannot be planted");
            }
        }

        else if (current.getName().compareTo(mangoTree.getName()) == 0) {
            b.getStylesheets().clear();
            b.getStylesheets().add("MyFarm/View/Stylesheets/unplowedTile.css");
            farm.getField().get(index).setPlowed();
            farm.getField().get(index).resetTile();
        }
        farmer.increaseXP(20);
        if (farmer.getXp() >= 100) {
            farmer.levelUp();
            farmerLevel.setText("Lvl " + farmer.getLevel());
        }
        xpLabel.setText(farmer.getXp() + " / 100");
        xpBar.setProgress(farmer.getXp()/100F);
        farmer.addCoins(20);
        coins.setText(String.format("%.2f", farmer.getMyFarmCoins()));
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
            } else if (b.getText().contentEquals("Settings")) {
                defaultButtons.setVisible(false);
                settingsMenu.setVisible(true);
            } else if (b.getText().equalsIgnoreCase("Exit Game"))
                System.exit(0);
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

    public void rankUp (ActionEvent e) {
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
        itemButton.setVisible(true);
        buyB.setVisible(true);
        if (toolUsed == null) {
            if (seeds.contains(b)) {
                index = seeds.indexOf(b);
                itemButton.setText(farm.getSeeds().get(index).getName() + " x " + farm.getSeeds().get(index).getAmount());
                buyB.setText(" Buy OC" + (farm.getSeeds().get(index).getSeedCost() * 1.00));
                descBackground.setVisible(true);
                descBox.setVisible(true);
                descName.setText(farm.getSeeds().get(index).getName());
                itemDescription.setText(farm.getSeeds().get(index).getDescription());
            } else if (tools.contains(b)) {
                index = tools.indexOf(b);
                descBackground.setVisible(true);
                descBox.setVisible(true);
                descName.setText(farm.getTools().get(index).getName());
                itemDescription.setText(farm.getTools().get(index).getDescription());
                if (index < 3) {
                    itemButton.setText(farm.getTools().get(index).getName());
                    buyB.setVisible(false);
                } else {
                    itemButton.setText(farm.getTools().get(index).getName() + " x "
                            + ((Fertilizer) farm.getTools().get(index)).getAmount());
                    buyB.setText(" Buy OC10.00");
                }
            }
            lastUsed = b;
        }
    }

    public void closeDescription () {
        if (toolUsed != null) {
            descBackground.setVisible(false);
            descBox.setVisible(false);
            selected = false;
            hideDisplayItem();
        }
    }

    public void displayItem(MouseEvent e) {
        if (!selected) {
            Button b = (Button) e.getSource();
            int index;
            itemButton.setVisible(true);
            buyB.setVisible(true);
            if (seeds.contains(b)) {
                index = seeds.indexOf(b);
                itemButton.setText(farm.getSeeds().get(index).getName() + " x " + farm.getSeeds().get(index).getAmount());
                buyB.setText(" Buy OC" + String.format("%.2f", farm.getSeeds().get(index).getSeedCost() * 1.00));
            } else if (tools.contains(b)) {
                index = tools.indexOf(b);
                if (index < 3) {
                    itemButton.setText(farm.getTools().get(index).getName());
                    buyB.setVisible(false);
                } else {
                    itemButton.setText(farm.getTools().get(index).getName() + " x "
                            + ((Fertilizer) farm.getTools().get(index)).getAmount());
                    buyB.setText(" Buy OC10.00");
                }
            }
        }

    }

    public void hideDisplayItem() {
        if (!selected) {
            itemButton.setVisible(false);
            buyB.setVisible(false);
        }
    }

    public void toolMode() {
        System.out.println("toolMode");
        Button b = lastUsed;
        int index;
        if (toolUsed == null) {
            if (seeds.contains(b)) {
                System.out.println("seeds");
                index = seeds.indexOf(b);
                toolUsed = farm.getSeeds().get(index).getName();

            } else if (tools.contains(b)) {
                index = tools.indexOf(b);
                toolUsed = farm.getTools().get(index).getName();
            }
            itemButton.setText("Done");
            exitInventory.setVisible(false);
        }
        else {
            selected = false;
            toolUsed = null;
            closeDescription();
            exitInventory.setVisible(true);
        }
    }
}
