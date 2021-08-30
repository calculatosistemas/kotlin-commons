package org.calculato.commons.number

import org.calculato.commons.time.BRAZIL
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.time.Duration
import kotlin.math.abs
import kotlin.math.roundToInt

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

fun BigDecimal.formatExtenso(): String {
    val vlr = setScale(2, RoundingMode.HALF_EVEN).toDouble()
    if (vlr == 0.0) return "zero"
    val inteiro = abs(vlr).toLong() // parte inteira do valor
    val resto = vlr - inteiro // parte fracionária do valor
    var vlrS = inteiro.toString()
    if (vlrS.length > 15) return "Erro: valor superior a 999 trilhões."
    var s = ""
    var saux: String
    var vlrP: String
    val centavos = (resto * 100).roundToInt().toString()
    val unidade = arrayOf(
        "", "um", "dois", "três", "quatro", "cinco",
        "seis", "sete", "oito", "nove", "dez", "onze",
        "doze", "treze", "quatorze", "quinze", "dezesseis",
        "dezessete", "dezoito", "dezenove"
    )
    val centena = arrayOf(
        "", "cento", "duzentos", "trezentos",
        "quatrocentos", "quinhentos", "seiscentos",
        "setecentos", "oitocentos", "novecentos"
    )
    val dezena = arrayOf(
        "", "", "vinte", "trinta", "quarenta", "cinquenta",
        "sessenta", "setenta", "oitenta", "noventa"
    )
    val qualificaS = arrayOf("", "mil", "milhão", "bilhão", "trilhão")
    val qualificaP = arrayOf("", "mil", "milhões", "bilhões", "trilhões")

    // definindo o extenso da parte inteira do valor
    var n: Int
    var unid: Int
    var dez: Int
    var cent: Int
    var tam: Int
    var i = 0
    var umReal = false
    var tem = false
    while (vlrS != "0") {
        tam = vlrS.length
        // retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
        // 1a. parte = 789 (centena)
        // 2a. parte = 456 (mil)
        // 3a. parte = 123 (milhões)
        if (tam > 3) {
            vlrP = vlrS.substring(tam - 3, tam)
            vlrS = vlrS.substring(0, tam - 3)
        } else { // última parte do valor
            vlrP = vlrS
            vlrS = "0"
        }
        if (vlrP != "000") {
            saux = ""
            if (vlrP == "100") saux = "cem" else {
                n = vlrP.toInt(10) // para n = 371, tem-se:
                cent = n / 100 // cent = 3 (centena trezentos)
                dez = n % 100 / 10 // dez  = 7 (dezena setenta)
                unid = n % 100 % 10 // unid = 1 (unidade um)
                if (cent != 0) saux = centena[cent]
                if (n % 100 in 1..19) {
                    saux = if (saux.isNotEmpty()) saux + " e " + unidade[n % 100] else unidade[n % 100]
                } else {
                    saux = if (saux.isNotEmpty() && dez != 0) saux + " e " + dezena[dez]
                    else if (dez > 0) dezena[dez] else saux
                    if (unid != 0) {
                        saux = if (saux.isNotEmpty()) saux + " e " + unidade[unid] else unidade[unid]
                    }
                }
            }
            if (vlrP == "1" || vlrP == "001") {
                if (i == 0) // 1a. parte do valor (um real)
                    umReal = true else saux = saux + " " + qualificaS[i]
            } else if (i != 0) saux = saux + " " + qualificaP[i]
            s = if (s.isNotEmpty()) "$saux, $s" else saux
        }
        if ((i == 0 || i == 1) && s.isNotEmpty()) tem = true // tem centena ou mil no valor
        i += 1 // próximo qualificador: 1- mil, 2- milhão, 3- bilhão, ...
    }
    if (s.isNotEmpty()) {
        s = if (umReal) "$s real" else if (tem) "$s reais" else "$s de reais"
    }

    // definindo o extenso dos centavos do valor
    if (centavos != "0") { // valor com centavos
        if (s.isNotEmpty()) // se não é valor somente com centavos
            s = "$s e "
        if (centavos == "1") s += "um centavo" else {
            n = centavos.toInt(10)
            if (n <= 19) s += unidade[n] else {             // para n = 37, tem-se:
                unid = n % 10 // unid = 37 % 10 = 7 (unidade sete)
                dez = n / 10 // dez  = 37 / 10 = 3 (dezena trinta)
                s += dezena[dez]
                if (unid != 0) s = s + " e " + unidade[unid]
            }
            s = "$s centavos"
        }
    }
    return s
}
