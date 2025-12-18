package org.acme.payment;

import jakarta.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class PaymentRepository {

    private final Map<String, Payment> payments = Collections.synchronizedMap(new LinkedHashMap<>());

    public PaymentRepository() {
        // Add some fake payments
        String paymentId1 = UUID.randomUUID().toString();
        payments.put(paymentId1, new Payment(paymentId1, UUID.randomUUID().toString(), UUID.randomUUID().toString(), new BigDecimal("1250.00"), "Credit Card", LocalDateTime.now(), Payment.PaymentStatus.COMPLETED));

        String paymentId2 = UUID.randomUUID().toString();
        payments.put(paymentId2, new Payment(paymentId2, UUID.randomUUID().toString(), UUID.randomUUID().toString(), new BigDecimal("75.00"), "PayPal", LocalDateTime.now(), Payment.PaymentStatus.PENDING));
    }

    public Payment getPaymentById(String id) {
        return payments.get(id);
    }

    public void addPayment(Payment payment) {
        payments.put(payment.getId(), payment);
    }

    public void updatePayment(Payment updatedPayment) {
        payments.put(updatedPayment.getId(), updatedPayment);
    }

    public void deletePayment(String id) {
        payments.remove(id);
    }
}
