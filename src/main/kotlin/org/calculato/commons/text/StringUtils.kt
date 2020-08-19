@file:Suppress("unused")

package org.calculato.commons.text

import org.calculato.commons.time.hoursPart
import org.calculato.commons.time.secondsPart
import org.apache.commons.lang3.time.DurationFormatUtils
import java.time.Duration

/**
 * Concatenates an "s" to [this] text for plural when [number] is so
 */
fun String.plural(number: Number) = this + if (number != 1) "s" else emptyString()

/**
 * Concatenates an "s" to [this] text for plural when [Int] is so
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
