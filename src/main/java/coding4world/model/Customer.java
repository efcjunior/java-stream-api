package coding4world.model;

public class Customer {

    private Long id;
    private String name;
    private Integer tier;

    public Customer(Long id, String name, Integer tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;
    }

    public Integer getTier() {
        return tier;
    }
}
