package MyFarm.Model;

public class Flower extends Seed {
    private String name;
    private int hello;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getHello() {
        return hello;
    }

    public void setHello(int hello) {
        this.hello = hello;
    }
}
