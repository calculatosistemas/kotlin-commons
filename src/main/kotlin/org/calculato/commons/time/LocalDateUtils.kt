package org.calculato.commons.time

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.time.temporal.TemporalAmount

/**
 * Creates a new LocalDateTime with Brazil's timezone
 */
fun dateTimeinBrazil(): LocalDateTime = LocalDateTime.now(ZoneId.of("Brazil/East"))

/**
 * Sets this date with the last day of this month
 */
fun LocalDate.withLastDayOfMonth(): LocalDate = with(TemporalAdjusters.lastDayOfMonth())

/**
 * Helper to create a new date with the given [year], [month] and [day]
 */
fun dateOf(year: Int, month: Int, day: Int): LocalDate = LocalDate.of(year, month, day)

/**
 * Helper to create a date at given [year] and [month] ate the last day os this month
 */
fun dateInLastDayOf(year: Int, month: Int): LocalDate = LocalDate.of(year, month, 1).withLastDayOfMonth()


/**
 * Iterator for the LocalDate class to be used within collections and ranges
 */
class LocalDateIterator(
    startDate: LocalDate,
    private val endDate: LocalDate,
    private val step: TemporalAmount
) : Iterator<LocalDate> {
    private var currentDate = startDate

    override fun hasNext(): Boolean = currentDate <= endDate
    override fun next(): LocalDate = currentDate.also { currentDate = it.plus(step) }
}

/**
 * Custom ClosedRange to be used with LocalDate
 */
class LocalDateRange(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
    private val step: TemporalAmount = Period.ofDays(1)
) : Iterable<LocalDate>, ClosedRange<LocalDate> {
    override fun iterator(): Iterator<LocalDate> = LocalDateIterator(start, endInclusive, step)

    infix fun step(period: TemporalAmount) = LocalDateRange(start, endInclusive, period)
}

/**
 * Custom operator for the LocalDate range
 */
operator fun LocalDate.rangeTo(other: LocalDate) = LocalDateRange(this, other)