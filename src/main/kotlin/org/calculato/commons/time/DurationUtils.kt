package org.calculato.commons.time

import org.apache.commons.lang3.time.DurationFormatUtils
import org.calculato.commons.text.emptyString
import java.time.Duration
import kotlin.math.absoluteValue

/**
 *Converts [this] duration to time format [HH:mm:ss]
 */
fun Duration.format(): String =
    DurationFormatUtils.formatDuration(
        toMillis(), "HH:mm${
            if (secondsPart != 0.toLong()) {
                ":ss"
            } else emptyString()
        }"
    )

/**
 * Returns seconds part of the duration
 */
val Duration.secondsPart: Long
    get() = this.seconds.absoluteValue % 60

/**
 * Returns minutes part of the duration
 */
val Duration.minutesPart: Long
    get() = (this.seconds.absoluteValue % 3600) / 60

/**
 * Returns hours part of the duration
 */
val Duration.hoursPart: Long
    get() = this.seconds.absoluteValue / 3600

/**
 * Helper method to converts [this] in duration as minutes unites
 */
fun Short.toMinutesOfDuration(): Duration = Duration.ofMinutes(this.toLong())

/**
 * Divide [this] duration by [i]
 */
operator fun Duration.div(i: Long): Duration = this.dividedBy(i)

/**
 * Multiplies [this] duration by [i]
 */
operator fun Duration.times(i: Long): Duration = this.multipliedBy(i)

/**
 * Gets a Duration instance with [this] number of hours
 */
fun Long.hours(): Duration = Duration.ofHours(this)

/**
 * Gets a Duration instance with [this] number of hours with [andMinutes] number of minutes
 */
fun Long.hours(andMinutes: Long): Duration = Duration.ofHours(this).plusMinutes(andMinutes)

/**
 * Gets a Duration instance with [this] number of minutes
 */
fun Long.minutes(): Duration = Duration.ofMinutes(this)
