package org.acme.cart;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CartResource {

    @Inject
    CartRepository cartRepository;

    @Inject
    CartItemRepository cartItemRepository;

    @GET
    @Path("/{userId}")
    public Response getCartByUserId(@PathParam("userId") String userId) {
        Optional<Cart> cartOptional = cartRepository.find("userId", userId).firstResultOptional();
        return cartOptional.map(cart -> Response.ok(cart).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response createCart(@QueryParam("userId") String userId) {
        if (userId == null || userId.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("User ID cannot be empty").build();
        }
        Optional<Cart> existingCart = cartRepository.find("userId", userId).firstResultOptional();
        if (existingCart.isPresent()) {
            return Response.status(Response.Status.CONFLICT).entity("Cart for this user already exists").build();
        }

        Cart cart = new Cart();
        cart.userId = userId;
        cartRepository.persist(cart);
        return Response.status(Response.Status.CREATED).entity(cart).build();
    }

    @PUT
    @Path("/{userId}/items")
    @Transactional
    public Response updateCartItems(@PathParam("userId") String userId, List<CartItem> newItems) {
        Optional<Cart> cartOptional = cartRepository.find("userId", userId).firstResultOptional();
        if (cartOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Cart cart = cartOptional.get();
        cart.items.forEach(cartItemRepository::delete);
        cart.items.clear();

        newItems.forEach(item -> {
            item.cart = cart;
            cart.items.add(item);
            cartItemRepository.persist(item);
        });

        cartRepository.persist(cart);
        return Response.ok(cart).build();
    }

    @DELETE
    @Path("/{userId}")
    @Transactional
    public Response deleteCart(@PathParam("userId") String userId) {
        Long deletedCount = cartRepository.delete("userId", userId);
        if (deletedCount > 0) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
