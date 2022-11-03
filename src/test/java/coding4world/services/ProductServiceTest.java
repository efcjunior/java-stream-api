package coding4world.services;

import coding4world.model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.Set;

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

    private final Product product8 = new Product(
            Long.valueOf(randomNumeric(5)),
            randomAlphabetic(10),
            BABY,
            350.00);

    private final Product product9 = new Product(
            Long.valueOf(randomNumeric(5)),
            randomAlphabetic(10),
            BABY,
            400.50);

    private final Customer customer1 =
            new Customer(
                    Long.valueOf(randomNumeric(5)),
                    randomAlphabetic(15),
                    1);

    private final LocalDate order1Date = LocalDate.of(2022,8,27);
    private final Order order1 = new Order(
            Long.valueOf(randomNumeric(5)),
            order1Date,
            getFutureDateFrom(order1Date),
            randomAlphabetic(4), customer1);



    private final Customer customer2 =
            new Customer(
                    Long.valueOf(randomNumeric(5)),
                    randomAlphabetic(15),
                    2);

    private final LocalDate order2Date = LocalDate.of(2022,9,1);
    private final Order order2 = new Order(
            Long.valueOf(randomNumeric(5)),
            order2Date,
            getFutureDateFrom(order1Date),
            randomAlphabetic(4), customer2);

    private final LocalDate order3Date = LocalDate.of(2022,10,1);
    private final Order order3 = new Order(
            Long.valueOf(randomNumeric(5)),
            order3Date,
            getFutureDateFrom(order3Date),
            randomAlphabetic(4), customer2);
    private ProductService productService;
    @Before
    public void setUp() {
        order1.addToProducts(product1);;
        order1.addToProducts(product2);
        order1.addToProducts(product3);
        order2.addToProducts(product4);
        order2.addToProducts(product5);
        order2.addToProducts(product6);
        order1.addToProducts(product7);
        order1.addToProducts(product8);
        order3.addToProducts(product9);

        product1.addToOrders(order1);
        product2.addToOrders(order1);
        product3.addToOrders(order1);
        product4.addToOrders(order2);
        product5.addToOrders(order2);
        product6.addToOrders(order2);
        product7.addToOrders(order1);
        product8.addToOrders(order1);
        product9.addToOrders(order3);

        productService = new ProductService(newHashSet(product1, product2, product3, product4,
                product5, product6, product7), newHashSet(order1, order2, order3));
    }

    @Test
    public void findByCategoryAndGreaterThanPrice(){
        assertThat(productService.findAllProductsByCategoryAndGreaterThanPrice(BOOKS, 100)
                .size()).isEqualTo(3);
    }

    @Test
    public void findAllProductsByCategoryAndApplyDiscount() {
        assertThat(productService.findAllProductsByCategoryAndApplyDiscount(TOYS, 0.9)
                .stream().mapToDouble(Product::getPrice).sum()).isEqualTo(180.00);
    }

    @Test
    public void findProductFromOrderByCategory() {
        assertThat(productService.findAllProductsFromOrderByCategory(BABY).size()).isEqualTo(3);
    }

    @Test
    public void findAllProductsOrderedByCustomerTieInPeriod() {
        assertThat(productService.findAllProductsOrderedByCustomerTieInPeriod(2,
                new DatePeriod(LocalDate.of(2022,8,30),
                        LocalDate.of(2022,9,2))).size()).isEqualTo(4);
    }

    @Test
    public void getProductCheapestByCategory() {
        assertThat(productService.getProductCheapestByCategory(BOOKS).orElseThrow()).isEqualTo(product6);
    }

    @Test
    public void getMostRecentOrders() {
        assertThat(productService.getMostRecentOrders(2)).isEqualTo(newHashSet(order3, order2));
    }

    @Test
    public void getProductsFromLoggedOrdersByDate() {
        assertThat(productService.getProductsFromLoggedOrdersByDate(LocalDate.of(2022,10,1)))
                .isEqualTo(order3.getProducts());
    }

    @Test
    public void getSumUpOrdersByDate() {
        assertThat(productService.getSumUpOrdersByDate(order1Date)).isEqualTo(901.00);
    }

    @Test
    public void getAvgOrdersByDate() {
        assertThat(productService.getAvgOrdersByDate(order1Date)).isEqualTo(180.20);
    }

    @Test
    public void getSummaryStatisticsByCategory() {
        DoubleSummaryStatistics statistics = productService.getSummaryStatisticsByCategory(BOOKS);
        assertThat(statistics.getCount()).isEqualTo(5);
        assertThat(statistics.getAverage()).isEqualTo(120.20);
        assertThat(statistics.getMax()).isEqualTo(200.00);
        assertThat(statistics.getMin()).isEqualTo(50.00);
        assertThat(statistics.getSum()).isEqualTo(601);
    }

}
