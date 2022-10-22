package coding4world.services;

import coding4world.model.Customer;
import coding4world.model.Order;
import coding4world.model.Product;
import org.junit.Before;

import java.time.LocalDate;
import java.util.Set;
import static coding4world.commons.RandomDateUtils.getDateUntilNow;
import static coding4world.model.Category.*;
import static com.google.common.collect.Sets.newHashSet;

import static org.apache.commons.lang3.RandomStringUtils.*;

public class ProductServiceTest {

    private final Customer customer1 =
            new Customer(
                    Long.valueOf(randomNumeric(5)),
                    randomAlphabetic(15),
                    Integer.valueOf(randomNumeric(2)));

    private final Product product1 = new Product(
            Long.valueOf(randomNumeric(5)),
            randomAlphabetic(10),
            BOOKS,
            Double.valueOf(randomNumeric(100,6000)));

    private final Order order1 = new Order(
            Long.valueOf(randomNumeric(5)),
            getDateUntilNow(),
            getDateUntilNow(),/*create a method to return this future date*/
            randomAlphabetic(4), customer1);

    /*To add more products*/

    private ProductService productService;
    @Before
    public void setUp() {
        order1.addToProducts(product1);;
        product1.addToOrders(order1);
        productService = new ProductService(newHashSet(product1));
    }
}
