package coding4world.services;

import coding4world.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final List<Product> products;

    public ProductService(List<Product> products) {
        this.products = products;
    }

    public List<Product> findAll() {
        return new ArrayList<>();
    }
}
