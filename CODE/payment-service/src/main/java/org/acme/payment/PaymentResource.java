package org.acme.payment;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.UUID;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

    private final PaymentRepository paymentRepository;

    public PaymentResource(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GET
    @Path("/{id}")
    public Response getPaymentById(@PathParam("id") String id) {
        Payment payment = paymentRepository.getPaymentById(id);
        if (payment != null) {
            return Response.ok(payment).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createPayment(Payment payment) {
        if (payment.getId() == null) {
            payment.setId(UUID.randomUUID().toString());
        }
        paymentRepository.addPayment(payment);
        return Response.status(Response.Status.CREATED).entity(payment).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePayment(@PathParam("id") String id, Payment payment) {
        Payment existingPayment = paymentRepository.getPaymentById(id);
        if (existingPayment != null) {
            payment.setId(id);
            paymentRepository.updatePayment(payment);
            return Response.ok(payment).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePayment(@PathParam("id") String id) {
        Payment existingPayment = paymentRepository.getPaymentById(id);
        if (existingPayment != null) {
            paymentRepository.deletePayment(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
