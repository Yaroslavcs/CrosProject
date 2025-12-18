package org.acme.order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OrderResource {

    @Inject
    OrderRepository orderRepository;

    @Inject
    OrderItemRepository orderItemRepository;

    @GET
    public List<Order> getAllOrders() {
        return orderRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") Long id) {
        return orderRepository.findByIdOptional(id)
                .map(order -> Response.ok(order).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathParam("userId") String userId) {
        return orderRepository.list("userId", userId);
    }

    @POST
    @Transactional
    public Response createOrder(Order order) {
        order.orderDate = LocalDateTime.now();
        order.status = "PENDING";
        orderRepository.persist(order);
        order.items.forEach(item -> {
            item.order = order;
            orderItemRepository.persist(item);
        });
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateOrder(@PathParam("id") Long id, Order order) {
        Optional<Order> orderOptional = orderRepository.findByIdOptional(id);
        if (orderOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Order entity = orderOptional.get();
        entity.userId = order.userId;
        entity.status = order.status;
        // Update items
        entity.items.forEach(orderItemRepository::delete);
        entity.items.clear();
        order.items.forEach(item -> {
            item.order = entity;
            entity.items.add(item);
            orderItemRepository.persist(item);
        });
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteOrder(@PathParam("id") Long id) {
        if (orderRepository.deleteById(id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
