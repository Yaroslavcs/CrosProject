package org.acme.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    public enum OrderStatus {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }

    private String id;
    private String userId;
    private List<OrderItem> items;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private OrderStatus status;

    public Order() {
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    public Order(String id, String userId, List<OrderItem> items, LocalDateTime orderDate, BigDecimal totalAmount, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(userId, order.userId) && Objects.equals(items, order.items) && Objects.equals(orderDate, order.orderDate) && Objects.equals(totalAmount, order.totalAmount) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, items, orderDate, totalAmount, status);
    }

    @Override
    public String toString() {
        return "Order{" +
               "id='" + id + ''' +
               ", userId='" + userId + ''' +
               ", items=" + items +
               ", orderDate=" + orderDate +
               ", totalAmount=" + totalAmount +
               ", status=" + status +
               '}';
    }
}
