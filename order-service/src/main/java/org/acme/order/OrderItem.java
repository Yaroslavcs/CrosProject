package org.acme.order;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem extends PanacheEntity {
    public String productId;
    public int quantity;
    public double price;

    @ManyToOne
    public Order order;
}
