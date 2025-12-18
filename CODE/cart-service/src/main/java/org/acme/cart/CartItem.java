package org.acme.cart;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem extends PanacheEntity {
    public String productId;
    public int quantity;

    @ManyToOne
    public Cart cart;
}
