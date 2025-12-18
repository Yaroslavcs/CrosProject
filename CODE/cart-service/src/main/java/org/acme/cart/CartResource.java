package org.acme.cart;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    private final CartRepository cartRepository;

    public CartResource(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GET
    @Path("/{userId}")
    public Response getCartByUserId(@PathParam("userId") String userId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        if (cart != null) {
            return Response.ok(cart).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{userId}")
    public Response createCart(@PathParam("userId") String userId) {
        Cart cart = cartRepository.createCart(userId);
        return Response.status(Response.Status.CREATED).entity(cart).build();
    }

    @PUT
    @Path("/{userId}/items")
    public Response updateCartItems(@PathParam("userId") String userId, List<CartItem> items) {
        Cart cart = cartRepository.getCartByUserId(userId);
        if (cart != null) {
            cart.setItems(items);
            cartRepository.updateCart(cart);
            return Response.ok(cart).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteCart(@PathParam("userId") String userId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        if (cart != null) {
            cartRepository.deleteCart(userId);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
