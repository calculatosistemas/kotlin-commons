@file:Suppress("unused")

package br.com.calculato.commons.text

import br.com.calculato.commons.time.hoursPart
import br.com.calculato.commons.time.secondsPart
import org.apache.commons.lang3.time.DurationFormatUtils
import java.time.Duration

/**
 * Concatenates an "s" to [this] text for plural when [number] is so
 */
fun String.plural(number: Number) = this + if (number != 1) "s" else emptyString()

/**
 * Concatenates an "s" to [this] text for plural when [int] is so
 */
fun String.plural(numero: Int) = this + if (numero != 1) "s" else emptyString()

/**
 * Concatenates an "s" to [this] text for plural when [duration] is so
 */
fun String.plural(duration: Duration) = this + if (duration.hoursPart != 1.toLong()) "s" else emptyString()

/**
 * Typesafe creation of an empty string
 */
fun emptyString() = ""

/**
 *Converts [this] duration to time format [HH:mm:ss]
 */
fun Duration.format(): String =
    DurationFormatUtils.formatDuration(
        toMillis(), "HH:mm${if (secondsPart != 0.toLong()) {
            ":ss"
        } else emptyString()}"
    )
