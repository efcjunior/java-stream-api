package coding4world.model;

import java.util.Set;

public class Product {

    private Long id;
    private String name;
    private String category;
    private Double price;
    private Set<Order> orders;

    public Product(Long id, String name, String category, Double price, Set<Order> orders) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.orders = orders;
    }
}