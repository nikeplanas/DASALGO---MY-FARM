package MyFarm.Model;

public abstract class Item {
    private String name;
    private String description;

    public Item () {

    }

    public void displayInfo () {

    }

    public String getDescription () {
        return description;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public void setDescription (String description) {
        this.description = description;
    }
}