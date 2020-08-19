package org.calculato.commons.time

import org.calculato.commons.text.emptyString
import org.apache.commons.lang3.time.DurationFormatUtils
import java.time.Duration
import kotlin.math.absoluteValue

/**
 *Converts [this] duration to time format [HH:mm:ss]
 */
fun Duration.format(): String =
    DurationFormatUtils.formatDuration(
        toMillis(), "HH:mm${if (secondsPart != 0.toLong()) {
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
fun Short.toMinutesOfDuration() = Duration.ofMinutes(this.toLong())
