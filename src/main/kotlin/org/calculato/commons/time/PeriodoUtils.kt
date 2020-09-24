package org.calculato.commons.time
import org.calculato.commons.utils.BRAZIL
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
/**
 * Returns the final date of Periodo
 */
fun Int.lastPeriodoDate() = firstPeriodoDate().withLastDayOfMonth()
/**
 * Returns the first date of Periodo
 */
fun Int.firstPeriodoDate(): LocalDate = LocalDate.of(periodoYear(), actualPeriodoMonth(), 1)
/**
 * Returns the month of Periodo not includes 13th
 */
fun Int.actualPeriodoMonth(): Int =
        with(this % 100) { if (this in 1..12) this else error("$this is not an actual periodo month") }
/**
 * Returns the month of Periodo includes 13th
 */
fun Int.periodoMonth(): Int =
        with(this % 100) { if (this in 1..13) this else error("Cannot convert $this to month") }
/**
 * Returns the year of Periodo
 */
fun Int.periodoYear() = div(100)
/**
 * Check if [this] date is in [periodo]
 */
fun LocalDate.isInPeriodo(periodo: Int): Boolean = this in periodo.firstPeriodoDate()..periodo.lastPeriodoDate()
/**
 * Returns the periodo form of [this] date
 */
fun LocalDate.getPeriodo(): Int = year * 100 + month.value
/**
 * Returns the periodo normalizes of [this]
 */
fun Int.formatPeriodoBR() = "${Month.of(periodoMonth()).getDisplayName(TextStyle.FULL, BRAZIL)}/${periodoYear()}"