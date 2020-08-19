package org.calculato.commons.time

import java.time.Period

/**
 * Gets a Period instance with [this] number of weeks
 */
fun Int.weeks(): Period = Period.ofWeeks(this)

/**
 * Gets a Period instance with [this] number of days
 */
fun Int.days(): Period = Period.ofDays(this)

/**
 * Gets a Period instance with [this] number of months
 */
fun Int.months(): Period = Period.ofMonths(this)

/**
 * Gets a Period instance with [this] number of years
 */
fun Int.years(): Period = Period.ofYears(this)