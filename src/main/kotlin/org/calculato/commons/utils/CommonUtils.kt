package org.calculato.commons.utils

import java.util.Locale
import java.util.UUID

fun randomUuid(): UUID = UUID.randomUUID()

/**
 * Returns the Brazil Locale
 */
val BRAZIL: Locale = Locale("pt", "BR")
