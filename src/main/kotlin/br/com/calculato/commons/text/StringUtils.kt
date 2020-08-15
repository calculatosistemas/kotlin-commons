@file:Suppress("unused")

package br.com.calculato.commons.text

/**
 * Concatenates an "s" to [this] text for plural when [number] is so
 */
fun String.plural(number: Number) = this + if (number != 1) "s" else emptyString()

/**
 * Typesafe creation of an empty string
 */
fun emptyString() = ""




