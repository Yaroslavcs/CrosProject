package org.acme.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {
    private String userId;
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(String userId) {
        this.userId = userId;
        this.items = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        this.items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(userId, cart.userId) && Objects.equals(items, cart.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, items);
    }

    @Override
    public String toString() {
        return "Cart{" +
               "userId='" + userId + ''' +
               ", items=" + items +
               '}';
    }
}
