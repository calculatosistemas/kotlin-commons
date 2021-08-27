package org.calculato.commons.number

import org.calculato.commons.time.BRAZIL
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.time.Duration

fun zero(): BigDecimal = BigDecimal.ZERO

/**
 * Operator Div Standard to scale 6 and RoundingMode HALF_EVEN
 */
operator fun BigDecimal.div(other: BigDecimal): BigDecimal =
    if (this == zero()) this else this.divide(other, 6, RoundingMode.HALF_EVEN)

/**
 * Converts [this] clock time to decimal number
 */
fun Duration.toDecimal(): BigDecimal = (this.toMinutes().toBigDecimal() / 60.toBigDecimal())


/**
 * Creates a BigDecimal value from [this] Short
 */
fun Short.toBigDecimal(): BigDecimal = BigDecimal.valueOf(this.toLong())

/**
 * Returns [this] BigDecimal with brazilian monetary scale format
 */
fun BigDecimal.monetary() = setScale(2, RoundingMode.HALF_EVEN)

/**
 * Converts [this] decimal to Brazil's format monetary
 */
fun BigDecimal.formatMonetario(): String =
    NumberFormat.getInstance(BRAZIL).format(this.setScale(2, RoundingMode.HALF_EVEN))

/**
 * Converts [this] decimal to Brazil's format decimal
 */
fun BigDecimal.format(): String =
    NumberFormat.getInstance(BRAZIL).format(this)

