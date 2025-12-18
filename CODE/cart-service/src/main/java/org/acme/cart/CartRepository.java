package org.acme.cart;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class CartRepository {

    // Map to store carts, where key is userId and value is Cart
    private final Map<String, Cart> carts = Collections.synchronizedMap(new LinkedHashMap<>());

    public CartRepository() {
        // Add some fake carts
        String userId1 = UUID.randomUUID().toString();
        Cart cart1 = new Cart(userId1);
        cart1.addItem(new CartItem("product-1", "Laptop", 1, new java.math.BigDecimal("1200.00")));
        cart1.addItem(new CartItem("product-2", "Mouse", 2, new java.math.BigDecimal("25.00")));
        carts.put(userId1, cart1);

        String userId2 = UUID.randomUUID().toString();
        Cart cart2 = new Cart(userId2);
        cart2.addItem(new CartItem("product-3", "Keyboard", 1, new java.math.BigDecimal("75.00")));
        carts.put(userId2, cart2);
    }

    public Cart getCartByUserId(String userId) {
        return carts.get(userId);
    }

    public void updateCart(Cart cart) {
        carts.put(cart.getUserId(), cart);
    }

    public void deleteCart(String userId) {
        carts.remove(userId);
    }

    public Cart createCart(String userId) {
        Cart newCart = new Cart(userId);
        carts.put(userId, newCart);
        return newCart;
    }
}
