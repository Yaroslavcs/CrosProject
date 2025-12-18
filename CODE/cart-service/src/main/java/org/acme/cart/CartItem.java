package org.acme.cart;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {
    private String productId;
    private String productName;
    private int quantity;
    private BigDecimal price;

    public CartItem() {
    }

    public CartItem(String productId, String productName, int quantity, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return quantity == cartItem.quantity && Objects.equals(productId, cartItem.productId) && Objects.equals(productName, cartItem.productName) && Objects.equals(price, cartItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, quantity, price);
    }

    @Override
    public String toString() {
        return "CartItem{" +
               "productId='" + productId + ''' +
               ", productName='" + productName + ''' +
               ", quantity=" + quantity +
               ", price=" + price +
               '}';
    }
}
