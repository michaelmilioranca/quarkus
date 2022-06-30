package org.acme.hibernate.orm.panache.dto;

import java.time.LocalDate;

public class DateDto {
  private final LocalDate date;

  public DateDto(LocalDate date) {
    this.date = date;
  }

  public LocalDate getDate() {
    return date;
  }
}
