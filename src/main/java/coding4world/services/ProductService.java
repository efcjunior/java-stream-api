package coding4world.services;

import coding4world.model.Category;
import coding4world.model.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductService {
    private final Set<Product> products;

    public ProductService(Set<Product> products) {
        this.products = products;
    }

    public Set<Product> findByCategoryAndGreaterThanPrice(Category category, double price) {
        return products.stream()
                .filter(product -> product.getCategory().equals(category))
                .filter(product -> product.getPrice() > price)
                .collect(Collectors.toSet());
    }
}
