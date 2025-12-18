package org.acme.order;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final OrderRepository orderRepository;

    public OrderResource(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GET
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") String id) {
        Order order = orderRepository.getOrderById(id);
        if (order != null) {
            return Response.ok(order).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathParam("userId") String userId) {
        return orderRepository.getOrdersByUserId(userId);
    }

    @POST
    public Response createOrder(Order order) {
        if (order.getId() == null) {
            order.setId(UUID.randomUUID().toString());
        }
        orderRepository.addOrder(order);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateOrder(@PathParam("id") String id, Order order) {
        Order existingOrder = orderRepository.getOrderById(id);
        if (existingOrder != null) {
            order.setId(id);
            orderRepository.updateOrder(order);
            return Response.ok(order).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") String id) {
        Order existingOrder = orderRepository.getOrderById(id);
        if (existingOrder != null) {
            orderRepository.deleteOrder(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
