package org.acme.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED, REFUNDED
    }

    private String id;
    private String orderId;
    private String userId;
    private BigDecimal amount;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private PaymentStatus status;

    public Payment() {
        this.paymentDate = LocalDateTime.now();
        this.status = PaymentStatus.PENDING;
    }

    public Payment(String id, String orderId, String userId, BigDecimal amount, String paymentMethod, LocalDateTime paymentDate, PaymentStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(orderId, payment.orderId) && Objects.equals(userId, payment.userId) && Objects.equals(amount, payment.amount) && Objects.equals(paymentMethod, payment.paymentMethod) && Objects.equals(paymentDate, payment.paymentDate) && status == payment.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, userId, amount, paymentMethod, paymentDate, status);
    }

    @Override
    public String toString() {
        return "Payment{" +
               "id='" + id + ''' +
               ", orderId='" + orderId + ''' +
               ", userId='" + userId + ''' +
               ", amount=" + amount +
               ", paymentMethod='" + paymentMethod + ''' +
               ", paymentDate=" + paymentDate +
               ", status=" + status +
               '}';
    }
}
