package org.calculato.commons.time

import java.time.LocalTime

/**
 * Helper to create a new time with the given [hour] and [minute]
 */
fun timeOf(hour: Int, minute: Int, seconds: Int = 0): LocalTime = LocalTime.of(hour, minute, seconds)
