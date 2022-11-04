package coding4world.services;

import coding4world.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
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

    public Optional<Product> getProductCheapestByCategory(Category category){
        return products.stream()
                .filter(product -> product.getCategory().equals(category))
                .min(Comparator.comparing(Product::getPrice));
    }


    /**TODO Move this method to OrderService class*/
    public Set<Order> findAllProductsFromOrderByCategory(Category category) {
        return orders.stream().filter(
                order -> order.getProducts()
                        .stream()
                        .anyMatch(product -> product.getCategory().equals(category))
                ).collect(Collectors.toSet());
    }

    public Set<Order> getMostRecentOrders(int limit) {
        return orders
                .stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(limit)
                .collect(Collectors.toSet());
    }

    public Set<Product> findAllProductsOrderedByCustomerTieInPeriod(Integer customerTie, DatePeriod period) {
        return orders.stream()
                .filter(order -> order.getCustomer().getTier().equals(customerTie))
                .filter(order -> order.getOrderDate().compareTo(period.getStartDate()) >= 0)
                .filter(order -> order.getOrderDate().compareTo(period.getEndDate()) <= 0)
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.toSet());
    }

    public Set<Product> getProductsFromLoggedOrdersByDate(LocalDate orderedDate) {
        return orders
                .stream()
                .filter(order -> order.getOrderDate().isEqual(orderedDate))
                .peek(order -> System.out.println(order.toString()))
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.toSet());
    }

    public double getSumUpOrdersByDate(LocalDate orderedDate){
        return orders
                .stream()
                .filter(order -> order.getOrderDate().compareTo(orderedDate) >= 0)
                .filter(order -> order.getOrderDate().compareTo(orderedDate) <= 0)
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public double getAvgOrdersByDate(LocalDate orderedDate) {
        return orders
                .stream()
                .filter(order -> order.getOrderDate().compareTo(orderedDate) >= 0)
                .filter(order -> order.getOrderDate().compareTo(orderedDate) <= 0)
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .average()
                .getAsDouble();
    }

    public DoubleSummaryStatistics getSummaryStatisticsByCategory(Category category) {
        return products
                .stream()
                .filter(product -> product.getCategory().equals(category))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();
    }

    public Map<Long, Integer> getMapOrderProductCount(){
        return orders
                .stream()
                .collect(
                        Collectors.toMap(order -> order.getId(), order -> order.getProducts().size())
                );
    }

    public Map<Customer, List<Order>> getAllOrdersGroupedByCustomer(){
        return orders
                .stream()
                .collect(Collectors.groupingBy(Order::getCustomer));
    }

    public Map<Order, Double> getAllOrderProductSum() {
        return orders
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum()
                ));
    }


    public Map<Category, List<String>> getAllProductNameByCategory() {
        return products
                .stream()
                .collect(
                        Collectors.groupingBy(Product::getCategory,
                                Collectors.mapping(Product::getName, Collectors.toList()))
                );
    }

    public Map<Category, Optional<Product>> getAllProductMostExpensiveByCategory() {
        return products
                .stream()
                .collect(
                        Collectors.groupingBy(Product::getCategory,
                                Collectors.maxBy(Comparator.comparing(Product::getPrice)))
                );
    }


}
