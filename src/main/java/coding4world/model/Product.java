package coding4world.model;

import java.util.Set;
import com.google.common.collect.Sets;

public class Product {

    private Long id;
    private String name;
    private Category category;
    private Double price;
    private Set<Order> orders = Sets.newHashSet();

    public Product(Long id, String name, Category category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }
    public void addToOrders(Order newOrder) {orders.add(newOrder);}
}
