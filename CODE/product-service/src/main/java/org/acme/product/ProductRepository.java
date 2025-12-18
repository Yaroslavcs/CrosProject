package org.acme.product;

import jakarta.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class ProductRepository {

    private final Set<Product> products = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public ProductRepository() {
        // Add some fake products
        products.add(new Product(UUID.randomUUID().toString(), "Laptop", "Powerful laptop for work and gaming", new BigDecimal("1200.00"), 10));
        products.add(new Product(UUID.randomUUID().toString(), "Mouse", "Wireless mouse with ergonomic design", new BigDecimal("25.00"), 50));
        products.add(new Product(UUID.randomUUID().toString(), "Keyboard", "Mechanical keyboard with RGB lighting", new BigDecimal("75.00"), 30));
        products.add(new Product(UUID.randomUUID().toString(), "Monitor", "4K UHD monitor for stunning visuals", new BigDecimal("300.00"), 15));
    }

    public Set<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(Product updatedProduct) {
        products.removeIf(product -> product.getId().equals(updatedProduct.getId()));
        products.add(updatedProduct);
    }

    public void deleteProduct(String id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
