package org.acme.product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Set;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductRepository productRepository;

    public ProductResource(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GET
    public Set<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") String id) {
        Product product = productRepository.getProductById(id);
        if (product != null) {
            return Response.ok(product).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response addProduct(Product product) {
        productRepository.addProduct(product);
        return Response.status(Response.Status.CREATED).entity(product).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") String id, Product product) {
        Product existingProduct = productRepository.getProductById(id);
        if (existingProduct != null) {
            product.setId(id);
            productRepository.updateProduct(product);
            return Response.ok(product).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") String id) {
        Product existingProduct = productRepository.getProductById(id);
        if (existingProduct != null) {
            productRepository.deleteProduct(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
