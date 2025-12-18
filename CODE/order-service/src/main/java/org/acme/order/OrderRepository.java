package org.acme.order;

import jakarta.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@ApplicationScoped
public class OrderRepository {

    private final Map<String, Order> orders = Collections.synchronizedMap(new LinkedHashMap<>());

    public OrderRepository() {
        // Add some fake orders
        String userId1 = UUID.randomUUID().toString();
        String orderId1 = UUID.randomUUID().toString();
        List<OrderItem> items1 = new ArrayList<>();
        items1.add(new OrderItem("product-1", "Laptop", 1, new BigDecimal("1200.00")));
        items1.add(new OrderItem("product-2", "Mouse", 2, new BigDecimal("25.00")));
        orders.put(orderId1, new Order(orderId1, userId1, items1, LocalDateTime.now(), new BigDecimal("1250.00"), Order.OrderStatus.DELIVERED));

        String userId2 = UUID.randomUUID().toString();
        String orderId2 = UUID.randomUUID().toString();
        List<OrderItem> items2 = new ArrayList<>();
        items2.add(new OrderItem("product-3", "Keyboard", 1, new BigDecimal("75.00")));
        orders.put(orderId2, new Order(orderId2, userId2, items2, LocalDateTime.now(), new BigDecimal("75.00"), Order.OrderStatus.PENDING));
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public Order getOrderById(String id) {
        return orders.get(id);
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orders.values().stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void updateOrder(Order updatedOrder) {
        orders.put(updatedOrder.getId(), updatedOrder);
    }

    public void deleteOrder(String id) {
        orders.remove(id);
    }
}
