package coding4world.services;

import coding4world.model.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductService {
    private final Set<Product> products;

    public ProductService(Set<Product> products) {
        this.products = products;
    }

    public Set<Product> findAll() {
        return new HashSet<>();
    }
}
