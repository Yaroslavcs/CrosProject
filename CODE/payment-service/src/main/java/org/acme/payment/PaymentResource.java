package org.acme.payment;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @GET
    public List<Payment> getAllPayments() {
        return Payment.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getPaymentById(@PathParam("id") Long id) {
        return Payment.findByIdOptional(id)
                .map(payment -> Response.ok(payment).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response createPayment(Payment payment) {
        payment.persist();
        return Response.status(Response.Status.CREATED).entity(payment).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updatePayment(@PathParam("id") Long id, Payment payment) {
        return Payment.findByIdOptional(id)
                .map(entity -> {
                    Payment paymentEntity = (Payment) entity;
                    paymentEntity.setOrderId(payment.getOrderId());
                    paymentEntity.setUserId(payment.getUserId());
                    paymentEntity.setAmount(payment.getAmount());
                    paymentEntity.setPaymentMethod(payment.getPaymentMethod());
                    paymentEntity.setPaymentDate(payment.getPaymentDate());
                    paymentEntity.setStatus(payment.getStatus());
                    return Response.ok(paymentEntity).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePayment(@PathParam("id") Long id) {
        if (Payment.deleteById(id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
