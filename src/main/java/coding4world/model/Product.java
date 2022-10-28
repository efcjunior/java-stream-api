package coding4world.model;

import java.util.Objects;
import java.util.Set;
import com.google.common.collect.Sets;

public class Product {

    private Long id;
    private String name;
    private Category category;
    private double price;
    private Set<Order> orders = Sets.newHashSet();

    public Product(Long id, String name, Category category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public Product getProductWithDiscount(double discount) {
        price = price * discount;
        return this;
    }
    public void addToOrders(Order newOrder) {orders.add(newOrder);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
