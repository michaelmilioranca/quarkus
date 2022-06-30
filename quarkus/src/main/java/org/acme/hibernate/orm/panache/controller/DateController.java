package org.acme.hibernate.orm.panache.controller;

import java.time.LocalDate;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.acme.hibernate.orm.panache.dto.DateDto;
import org.acme.hibernate.orm.panache.usecase.EspecialDateCalculator;

@Path("/date")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class DateController {

  private final EspecialDateCalculator especialDateCalculator;

  public DateController() {
    this.especialDateCalculator = new EspecialDateCalculator();
  }

  @GET
  @Path("/{date}/sum")
  public Response sumDate(final LocalDate date) {
    return Response.ok(new DateDto(especialDateCalculator.yearThing(date))).build();
  }
}
