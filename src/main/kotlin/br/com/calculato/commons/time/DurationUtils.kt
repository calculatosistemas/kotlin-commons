package br.com.calculato.commons.time

import br.com.calculato.commons.text.emptyString
import org.apache.commons.lang3.time.DurationFormatUtils
import java.time.Duration
import kotlin.math.absoluteValue

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
