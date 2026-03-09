package generics;

public class Animal extends  LivingCreature{

    private String name;
    private Integer legs;

    public Animal(String name, Integer legs) {
        this.name = name;
        this.legs = legs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLegs() {
        return legs;
    }

    public void setLegs(Integer legs) {
        this.legs = legs;
    }
}
