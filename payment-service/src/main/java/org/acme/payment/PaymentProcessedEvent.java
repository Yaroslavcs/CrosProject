package org.acme.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentProcessedEvent {

    private Long paymentId;
    private String orderId;
    private String userId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private Payment.PaymentStatus status;

    public PaymentProcessedEvent() {
    }

    public PaymentProcessedEvent(Long paymentId, String orderId, String userId, BigDecimal amount, LocalDateTime paymentDate, Payment.PaymentStatus status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Payment.PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(Payment.PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentProcessedEvent{" +
               "paymentId=" + paymentId +
               ", orderId='" + orderId + '\'' +
               ", userId='" + userId + '\'' +
               ", amount=" + amount +
               ", paymentDate=" + paymentDate +
               ", status=" + status +
               '}';
    }
}