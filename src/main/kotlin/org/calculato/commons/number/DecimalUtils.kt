package org.calculato.commons.number

import java.math.BigDecimal
import java.math.RoundingMode
import java.time.Duration

fun zero(): BigDecimal = BigDecimal.ZERO

/**
 * Operator Div Standard to scale 6 and RoundingMode HALF_EVEN
 */
operator fun BigDecimal.div(other: BigDecimal): BigDecimal = this.divide(other, 6, RoundingMode.HALF_EVEN)

/**
 * Converts [this] clock time to decimal number
 */
fun Duration.toDecimal(): BigDecimal = (this.toMinutes().toBigDecimal() / 60.toBigDecimal())
