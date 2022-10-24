package coding4world.services;

import coding4world.model.Customer;
import coding4world.model.Order;
import coding4world.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import static coding4world.commons.RandomDateUtils.getDateUntilNow;
import static coding4world.commons.RandomDateUtils.getFutureDateFrom;
import static coding4world.model.Category.*;
import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;

import static org.apache.commons.lang3.RandomStringUtils.*;

public class ProductServiceTest {

    private final Product product1 = new Product(
            Long.valueOf(randomNumeric(5)),
            randomAlphabetic(10),
            BOOKS,
            101.00);

    private final Product product2 = new Product(
            Long.valueOf(randomNumeric(5)),
            randomAlphabetic(10),
            BOOKS,
            150.00);

    private final Product product3 = new Product(
            Long.valueOf(randomNumeric(5)),
            randomAlphabetic(10),
            BOOKS,
            100.00);

    private final Product product4 = new Product(
            Long.valueOf(randomNumeric(5)),
            randomAlphabetic(10),
            TOYS,
            200.00);

    private final Product product5 = new Product(
            Long.valueOf(randomNumeric(5)),
            randomAlphabetic(10),
            BABY,
            300.00);

    private final Product product6 = new Product(
            Long.valueOf(randomNumeric(5)),
            randomAlphabetic(10),
            BOOKS,
            50.00);

    private final Product product7 = new Product(
            Long.valueOf(randomNumeric(5)),
            randomAlphabetic(10),
            BOOKS,
            200.00);

    private final Customer customer1 =
            new Customer(
                    Long.valueOf(randomNumeric(5)),
                    randomAlphabetic(15),
                    Integer.valueOf(randomNumeric(2)));

    private final LocalDate order1Date = getDateUntilNow();
    private final Order order1 = new Order(
            Long.valueOf(randomNumeric(5)),
            order1Date,
            getFutureDateFrom(order1Date),
            randomAlphabetic(4), customer1);



    private final Customer customer2 =
            new Customer(
                    Long.valueOf(randomNumeric(5)),
                    randomAlphabetic(15),
                    Integer.valueOf(randomNumeric(2)));

    private final LocalDate order2Date = getDateUntilNow();
    private final Order order2 = new Order(
            Long.valueOf(randomNumeric(5)),
            order2Date,
            getFutureDateFrom(order1Date),
            randomAlphabetic(4), customer2);
    private ProductService productService;
    @Before
    public void setUp() {
        order1.addToProducts(product1);;
        order1.addToProducts(product2);
        order2.addToProducts(product2);
        order1.addToProducts(product3);
        order2.addToProducts(product4);
        order2.addToProducts(product5);
        order2.addToProducts(product6);
        order1.addToProducts(product7);

        product1.addToOrders(order1);
        product2.addToOrders(order1);
        product2.addToOrders(order2);
        product3.addToOrders(order1);
        product4.addToOrders(order2);
        product5.addToOrders(order2);
        product6.addToOrders(order2);
        product7.addToOrders(order1);

        productService = new ProductService(newHashSet(product1, product2, product3, product4,
                product5, product6, product7));
    }

    @Test
    public void findByCategoryAndGreaterThanPrice(){
        assertThat(productService.findByCategoryAndGreaterThanPrice(BOOKS, 100).size()).isEqualTo(3);
    }
}
