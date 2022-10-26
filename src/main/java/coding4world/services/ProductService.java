package coding4world.services;

import coding4world.model.Category;
import coding4world.model.Order;
import coding4world.model.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductService {
    private final Set<Product> products;
    private final Set<Order> orders;

    public ProductService(Set<Product> products, Set<Order> orders) {
        this.products = products;
        this.orders = orders;
    }

    public Set<Product> findAllProductsByCategoryAndGreaterThanPrice(Category category, double price) {
        return products.stream()
                .filter(product -> product.getCategory().equals(category))
                .filter(product -> product.getPrice() > price)
                .collect(Collectors.toSet());
    }

    public Set<Product> findAllProductsByCategoryAndApplyDiscount(Category category, double discount) {
        return products.stream()
                .filter(product -> product.getCategory().equals(category))
                .map(product -> product.getProductWithDiscount(discount) )
                .collect(Collectors.toSet());
    }


    /**TODO Move this method to OrderService class*/
    public Set<Order> findAllProductsFromOrderByCategory(Category category) {
        return orders.stream().filter(
                order -> order.getProducts()
                        .stream()
                        .anyMatch(product -> product.getCategory().equals(category))
                ).collect(Collectors.toSet());
    }
}
