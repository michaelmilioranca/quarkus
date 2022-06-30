package org.acme.hibernate.orm.panache.usecase;

import java.time.LocalDate;
import java.time.Month;

public class EspecialDateCalculator {

  private static final int DEFAULT_YEAR_RANGE = 70;

  public LocalDate yearThing(final LocalDate date) {
    if (Month.FEBRUARY.equals(date.getMonth())) {
      return februaryMagic(date);
    }
    return addYearsLogic(date);
  }

  private LocalDate addYearsLogic(final LocalDate date) {
    return date.plusYears(DEFAULT_YEAR_RANGE).minusYears(DEFAULT_YEAR_RANGE);
  }

  private LocalDate februaryMagic(final LocalDate date) {
    boolean lastDayOfMonth = isLastDayOfMonth(date);
    LocalDate magicDate = date;
    if (lastDayOfMonth) {
      magicDate = LocalDate.of(date.getYear(), date.getMonth(), 1);
    }
    magicDate = addYearsLogic(magicDate);
    return lastDayOfMonth
        ? LocalDate.of(magicDate.getYear(), magicDate.getMonth(), magicDate.lengthOfMonth())
        : magicDate;
  }

  private boolean isLastDayOfMonth(final LocalDate date) {
    return date.lengthOfMonth() == date.getDayOfMonth();
  }

  public LocalDate addYearSameDay(final LocalDate date, final int year) {
    if (Month.FEBRUARY.equals(date.getMonth())) {
      return februaryEspecial(date, year);
    }
    return date.plusYears(year);
  }

  private LocalDate februaryEspecial(LocalDate date, int year) {
    if (isLastDayOfMonth(date)) {
      LocalDate customDate = LocalDate.of(date.getYear(), date.getMonth(), 1);
      customDate = customDate.plusYears(year);
      return LocalDate.of(customDate.getYear(), customDate.getMonth(), customDate.lengthOfMonth());
    }
    return date.plusYears(year);
  }
}
