package cat.itacademy.s04.s02.n01.fruit.model;

public class Fruit {
    private final Long id;
    private final String name;
    private final int weightInKilos;

    public Fruit(Long id, String name, int weightInKilos) {
        this.id = id;
        this.name = name;
        this.weightInKilos = weightInKilos;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeightInKilos() {
        return weightInKilos;
    }
}
