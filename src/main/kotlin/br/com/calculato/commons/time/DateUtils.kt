package br.com.calculato.commons.time

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters

/**
 * Creates a new LocalDateTime with Brazil's timezone
 */
fun dateTimeinBrazil(): LocalDateTime = LocalDateTime.now(ZoneId.of("Brazil/East"))

fun LocalDate.withLastDayOfMonth() = with(TemporalAdjusters.lastDayOfMonth())

fun dateOf(year: Int, month: Int, day: Int) = LocalDate.of(year, month, day)

fun dateInLastDayOf(year: Int, month: Int) = LocalDate.of(year, month, 1).withLastDayOfMonth()

fun timeOf(hour: Int, minute: Int, seconds: Int = 0) = LocalTime.of(hour, minute, seconds)
