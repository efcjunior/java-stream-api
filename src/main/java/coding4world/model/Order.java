package coding4world.model;

import java.time.LocalDate;
import java.util.Set;
import com.google.common.collect.Sets;
public class Order {

    private Long id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String status;
    private Customer customer;

    private Set<Product> products = Sets.newHashSet();

    public Order(Long id, LocalDate orderDate, LocalDate deliveryDate, String status, Customer customer) {
        this.id = id;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.customer = customer;
    }

    public void addToProducts(Product newProduct) {products.add(newProduct);}

}
