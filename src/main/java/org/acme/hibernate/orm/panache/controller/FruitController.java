package org.acme.hibernate.orm.panache.controller;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.acme.hibernate.orm.panache.entity.Fruit;

@Path("/fruits")
@ApplicationScoped
public class FruitController {

  @GET
  public Uni<List<Fruit>> get() {
    return Fruit.listAll(Sort.by("name"));
  }

  @GET
  @Path("/{id}")
  public Uni<Fruit> getSingle(final Long id) {
    return Fruit.findById(id);
  }

  @POST
  public Uni<Response> create(final Fruit fruit) {
    if (fruit == null || fruit.id != null) {
      throw new WebApplicationException("Id was invalidly set on request.", 422);
    }
    return Panache.withTransaction(fruit::persist)
        .replaceWith(Response.ok(fruit).status(CREATED)::build);
  }

  @PUT
  @Path("/{id}")
  public Uni<Response> update(Long id, Fruit fruit) {
    if (fruit == null || fruit.name == null) {
      throw new WebApplicationException("Fruit name was not set on request.", 422);
    }
    return Panache.withTransaction(
            () ->
                Fruit.<Fruit>findById(id)
                    .onItem()
                    .ifNotNull()
                    .invoke(entity -> entity.name = fruit.name))
        .onItem()
        .ifNotNull()
        .transform(entity -> Response.ok(entity).build())
        .onItem()
        .ifNull()
        .continueWith(Response.ok().status(NOT_FOUND)::build);
  }

  @DELETE
  @Path("/{id}")
  public Uni<Response> delete(final Long id) {
    return Panache.withTransaction(() -> Fruit.deleteById(id))
        .map(
            deleted ->
                deleted
                    ? Response.ok().status(NO_CONTENT).build()
                    : Response.ok().status(NOT_FOUND).build());
  }
}
