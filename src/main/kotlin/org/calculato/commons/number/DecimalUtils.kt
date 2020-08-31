package org.calculato.commons.number

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.time.Duration
import java.util.Locale

fun zero(): BigDecimal = BigDecimal.ZERO

/**
 * Operator Div Standard to scale 6 and RoundingMode HALF_EVEN
 */
operator fun BigDecimal.div(other: BigDecimal): BigDecimal = this.divide(other, 6, RoundingMode.HALF_EVEN)

/**
 * Converts [this] clock time to decimal number
 */
fun Duration.toDecimal(): BigDecimal = (this.toMinutes().toBigDecimal() / 60.toBigDecimal())

/**
 * Converts [this] decimal to Brazil's format monetary
 */
fun BigDecimal.formatMonetario(): String =
    NumberFormat.getInstance(Locale("pt", "BR")).format(this.setScale(2, RoundingMode.HALF_EVEN))

/**
 * Converts [this] decimal to Brazil's format decimal
 */
fun BigDecimal.format(): String =
    NumberFormat.getInstance(Locale("pt", "BR")).format(this)
