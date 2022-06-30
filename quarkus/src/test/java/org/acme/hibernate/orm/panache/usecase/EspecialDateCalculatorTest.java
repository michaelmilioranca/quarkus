package org.acme.hibernate.orm.panache.usecase;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class EspecialDateCalculatorTest {

  public EspecialDateCalculator especialDateCalculator = new EspecialDateCalculator();

  @Test
  void testLeapYearDate() {
    LocalDate dateExpected = LocalDate.of(2028, 2, 29);
    // should still be the same when we add 70 years and subctract 70
    assertEquals(especialDateCalculator.yearThing(dateExpected), dateExpected);
  }

  @Test
  void testLeapYearResultDate() {
    LocalDate dateExpected = LocalDate.of(2026, 2, 28);
    // should still be the same when we add 70 years and subctract 70
    assertEquals(especialDateCalculator.yearThing(dateExpected), dateExpected);
  }

  @Test
  void testNormalDays() {
    LocalDate dateExpected = LocalDate.of(2024, 3, 31);
    // should still be the same when we add 70 years and subctract 70
    assertEquals(especialDateCalculator.yearThing(dateExpected), dateExpected);
  }

  @Test
  void testFebruaryDays() {
    LocalDate dateExpected = LocalDate.of(2024, 2, 28);
    // should still be the same when we add 70 years and subctract 70
    assertEquals(especialDateCalculator.yearThing(dateExpected), dateExpected);
  }

  @Test
  void testSumDateSameDayLeapYear() {
    LocalDate dateExpected = LocalDate.of(2096, 2, 29);
    // should still be the same when we add 70 years and subctract 70
    assertEquals(
        especialDateCalculator.addYearSameDay(LocalDate.of(2021, 2, 28), 75), dateExpected);
  }
}
